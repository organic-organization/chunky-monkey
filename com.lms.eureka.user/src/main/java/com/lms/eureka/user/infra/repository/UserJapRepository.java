package com.lms.eureka.user.infra.repository;

import com.lms.eureka.user.domain.model.User;
import com.lms.eureka.user.domain.repository.UserRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserJapRepository extends JpaRepository<User, Long>, UserRepository {
}
