package com.lms.eureka.user.application.service;

import com.lms.eureka.user.application.client.SlackClient;
import com.lms.eureka.user.application.dto.UserDto;
import com.lms.eureka.user.domain.exception.UserException;
import com.lms.eureka.user.domain.exception.UserExceptionCase;
import com.lms.eureka.user.domain.service.RedisService;
import com.lms.eureka.user.presentation.request.FindPasswordRequestDto;
import com.lms.eureka.user.presentation.request.LoginRequestDto;
import com.lms.eureka.user.domain.model.User;
import com.lms.eureka.user.infra.jwt.JwtUtil;
import com.lms.eureka.user.domain.repository.UserRepository;
import com.lms.eureka.user.presentation.request.SlackSendRequestDto;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {
    private final UserRepository userRepository;
    private final RedisService redisService;
    private final SlackClient slackClient;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    private final static String USER_REDIS_KEY_PREFIX = "user:";
    private final static Long REDIS_CODE_EXPIRE_TIME = 5L;

    public void signUp(UserDto dto) {
        checkDuplicateUser(dto.username());
        String encodePassword = passwordEncoder.encode(dto.password());
        userRepository.save(dto.toEntity(encodePassword));
    }

    private void checkDuplicateUser(String username){
        if(userRepository.findByUsername(username).isPresent()){
            throw new UserException(UserExceptionCase.PASSWORD_NOT_MATCH);
        }
    }

    public void login(HttpServletResponse response, LoginRequestDto dto) {
        String password = dto.password();
        User user = userRepository.findByUsername(dto.username())
                .orElseThrow(() -> new UserException(UserExceptionCase.USER_NOT_FOUND));

        if(!passwordEncoder.matches(password, user.getPassword())){
            throw new UserException(UserExceptionCase.USER_NOT_FOUND);
        }

        String token = jwtUtil.createToken(user.getUsername(), user.getRole());
        response.setHeader("Authorization", token);
    }

    @Transactional(readOnly = true)
    public UserDto getUserInfoByUserName(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UserException(UserExceptionCase.USER_NOT_FOUND));
        return UserDto.from(user);
    }

    @Transactional(readOnly = true)
    public Page<UserDto> getUserInfos(Pageable pageable) {
        Page<User> users = userRepository.findAll(pageable);

        return users.map(UserDto::from);
    }

    @Transactional(readOnly = true)
    public UserDto getUserInfoByUserId(Long userId) {
        return UserDto.from(userRepository.findById(userId)
                .orElseThrow(() -> new UserException(UserExceptionCase.USER_NOT_FOUND)));
    }

    public UserDto updateUserInfo(Long userId, UserDto dto) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserException(UserExceptionCase.USER_NOT_FOUND));
        user.update(dto.role(), dto.slackId());

        return UserDto.from(userRepository.findById(userId)
                .orElseThrow(() -> new UserException(UserExceptionCase.USER_NOT_FOUND)));
    }

    public void deleteUserInfo(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserException(UserExceptionCase.USER_NOT_FOUND));
        user.delete();
    }

    public void findPassword(FindPasswordRequestDto dto) {
        User user = userRepository.findByUsername(dto.username())
                .orElseThrow(() -> new UserException(UserExceptionCase.USER_NOT_FOUND));

        // 6자리 난수 생성
        Random random = new Random();
        String randomCode = String.valueOf(100000 + random.nextInt(900000));

        String code = redisService.getValueAsClass(USER_REDIS_KEY_PREFIX + user.getUsername(), String.class);
        if (code != null) {
            redisService.deleteValue(USER_REDIS_KEY_PREFIX + user.getUsername());
        }

        redisService.setValueWithExpiry(USER_REDIS_KEY_PREFIX + user.getUsername(), randomCode, REDIS_CODE_EXPIRE_TIME, TimeUnit.MINUTES);

        // slack api 호출
        if (!slackClient.sendPasswordCodeMessage(
                SlackSendRequestDto.create(user.getSlackId(), randomCode)).getStatus().equals(HttpStatus.OK)
        ) {
            redisService.deleteValue(USER_REDIS_KEY_PREFIX + user.getUsername());
            throw new UserException(UserExceptionCase.FIND_PASSWORD_FAIL);
        }
    }

    public void updatePassword(String requestCode, UserDto dto) {
        User user = userRepository.findByUsername(dto.username())
                .orElseThrow(() -> new UserException(UserExceptionCase.USER_NOT_FOUND));

        String code = Optional.ofNullable(
                redisService.getValueAsClass(USER_REDIS_KEY_PREFIX + dto.username(), String.class)
        ).orElseThrow(() -> new UserException(UserExceptionCase.FIND_PASSWORD_FAIL));

        if (!code.equals(requestCode)) {
            throw new UserException(UserExceptionCase.FIND_PASSWORD_FAIL);
        }

        user.updatePassword(passwordEncoder.encode(dto.password()));
    }
}
