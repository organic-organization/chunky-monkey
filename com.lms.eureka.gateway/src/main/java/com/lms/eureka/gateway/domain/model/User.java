package com.lms.eureka.gateway.domain.model;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class User {

    private String deletedBy;
    private int userId;
    private String userName;
    private String password;
    private UserRoleEnum role;

    private LocalDateTime createdAt;
    private String createdBy;
    private LocalDateTime updatedAt;
    private String updatedBy;
    private LocalDateTime deletedAt;

}
