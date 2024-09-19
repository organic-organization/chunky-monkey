package com.lms.eureka.slack.infra.repository;

import com.lms.eureka.slack.domain.model.Slack;
import com.lms.eureka.slack.domain.repository.SlackRepository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SlackJpaRepository extends JpaRepository<Slack, UUID>, SlackRepository {
}
