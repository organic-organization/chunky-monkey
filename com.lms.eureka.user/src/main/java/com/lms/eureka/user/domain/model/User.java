package com.lms.eureka.user.domain.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Entity
@Builder(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
@Table(name = "p_user")
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", nullable = false, updatable = false)
    private Long userId;

    @Column(name = "username", nullable = false)
    private String username;

    @Setter
    @Column(name = "password", nullable = false)
    private String password;

    @Setter
    @Column(name = "role", nullable = false)
    @Enumerated(EnumType.STRING)
    private UserRoleEnum role;

    @Setter
    @Column(name = "slack_id", nullable = false)
    private String slackId;

    @Setter
    @Column(name = "is_deleted", nullable = false)
    private Boolean isDeleted = false;

    public static User create(String username, String password, UserRoleEnum role, String slackId) {
        return User.builder()
                .username(username)
                .password(password)
                .role(role)
                .isDeleted(false)
                .slackId(slackId)
                .build();
    }

    public void update(UserRoleEnum role, String slackId) {
        if (role != null) {
            this.role = role;
        }

        if (slackId != null) {
            this.slackId = slackId;
        }
    }

    public void updatePassword(String password) {
        this.password = password;
    }

    public void delete() {
        this.isDeleted = true;
        setDeleted(this.username);
    }
}
