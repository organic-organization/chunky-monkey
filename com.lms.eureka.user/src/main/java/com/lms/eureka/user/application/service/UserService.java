package com.lms.eureka.user.application.service;

import com.lms.eureka.user.application.dto.UserDto;
import com.lms.eureka.user.presentation.request.LoginRequestDto;
import com.lms.eureka.user.domain.model.User;
import com.lms.eureka.user.infra.jwt.JwtUtil;
import com.lms.eureka.user.domain.repository.UserRepository;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public void signUp(UserDto dto) {
        checkDuplicateUser(dto.userName());
        String encodePassword = passwordEncoder.encode(dto.password());
        userRepository.save(dto.toEntity(encodePassword));
    }

    private void checkDuplicateUser(String userName){
        if(userRepository.findByUserName(userName).isPresent()){
            throw new RuntimeException();
        }
    }

    public void login(HttpServletResponse response, LoginRequestDto dto) {
        String password = dto.password();
        User user = userRepository.findByUserName(dto.userName()).orElseThrow();

        if(!passwordEncoder.matches(password, user.getPassword())){
            throw new RuntimeException();
        }

        String token = jwtUtil.createToken(user.getUserName(), user.getRole());
        response.setHeader("Authorization", token);
    }
}
