package com.lms.eureka.slack.domain.repository;

import com.lms.eureka.slack.domain.model.Slack;

public interface SlackRepository {
    Slack save(Slack slack);
}
