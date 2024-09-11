package com.lms.eureka.hub.presentation.request.hubRoute;

import java.util.UUID;

public record CreateHubRouteRequest(UUID departureHubId, UUID arrivalHubId, long duration) {
}
