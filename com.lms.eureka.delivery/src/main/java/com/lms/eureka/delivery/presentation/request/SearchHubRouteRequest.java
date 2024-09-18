package com.lms.eureka.delivery.presentation.request;

import java.util.UUID;

public record SearchHubRouteRequest(
        UUID arrivalHubId,
        UUID departureHubId
) {
}
