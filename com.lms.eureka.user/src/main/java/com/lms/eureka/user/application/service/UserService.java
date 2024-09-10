package com.lms.eureka.user.application.service;

import com.lms.eureka.user.application.dto.UserDto;
import com.lms.eureka.user.presentation.request.LoginRequestDto;
import com.lms.eureka.user.domain.model.User;
import com.lms.eureka.user.infra.jwt.JwtUtil;
import com.lms.eureka.user.domain.repository.UserRepository;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    // TODO : 에러 처리 해야합니다.

    // 회원 가입
    public void signUp(UserDto dto) {
        checkDuplicateUser(dto.username());
        String encodePassword = passwordEncoder.encode(dto.password());
        userRepository.save(dto.toEntity(encodePassword));
    }

    private void checkDuplicateUser(String userName){
        if(userRepository.findByUsername(userName).isPresent()){
            throw new RuntimeException();
        }
    }

    public void login(HttpServletResponse response, LoginRequestDto dto) {
        String password = dto.password();
        User user = userRepository.findByUsername(dto.username()).orElseThrow();

        if(!passwordEncoder.matches(password, user.getPassword())){
            throw new RuntimeException();
        }

        String token = jwtUtil.createToken(user.getUsername(), user.getRole());
        response.setHeader("Authorization", token);
    }

    @Transactional(readOnly = true)
    public UserDto getUserInfoByUserName(String username) {
        User user = userRepository.findByUsername(username).orElseThrow();
        return UserDto.from(user);
    }

    @Transactional(readOnly = true)
    public Page<UserDto> getUserInfos(Pageable pageable) {
        Page<User> users = userRepository.findAll(pageable);

        return users.map(UserDto::from);
    }

    @Transactional(readOnly = true)
    public UserDto getUserInfoByUserId(Long userId) {
        return UserDto.from(userRepository.findById(userId).orElseThrow());
    }

    public UserDto updateUserInfo(Long userId, UserDto dto) {
        User user = userRepository.findById(userId).orElseThrow();
        user.update(dto.role(), dto.slackId());

        return UserDto.from(userRepository.findById(userId).orElseThrow());
    }

    public void deleteUserInfo(Long userId) {
        User user = userRepository.findById(userId).orElseThrow();
        user.delete();
    }
}
