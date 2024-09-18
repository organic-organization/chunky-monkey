package com.lms.eureka.delivery.presentation.request;

public record SearchHubRouteRequest(
        String departureHubName,
        String arrivalHubName
) {
}
