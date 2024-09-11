package com.lms.eureka.slack.domain.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Getter
@Entity
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
@Table(name = "p_slack")
public class Slack extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "slack_id", nullable = false, updatable = false)
    private UUID id;

    @Column(name = "receiver_id", nullable = false, updatable = false)
    private String receiverId;

    @Column(name = "content", nullable = false, updatable = false)
    private String content;

    @Setter
    @Column(name = "is_deleted", nullable = false)
    private Boolean isDeleted = false;

    private Slack(String receiverId, String content) {
        this.receiverId = receiverId;
        this.content = content;
    }

    public static Slack create(String receiverId, String content) {
        return new Slack(receiverId, content);
    }

    public void delete() {
        this.isDeleted = true;
    }
}
