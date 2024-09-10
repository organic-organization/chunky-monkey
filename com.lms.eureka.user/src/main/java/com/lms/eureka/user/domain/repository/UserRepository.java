package com.lms.eureka.user.domain.repository;

import com.lms.eureka.user.domain.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface UserRepository{
    User save(User user);
    Optional<User> findById(Long id);
    Optional<User> findByUsername(String username);
    Page<User> findAll(Pageable pageable);
}
