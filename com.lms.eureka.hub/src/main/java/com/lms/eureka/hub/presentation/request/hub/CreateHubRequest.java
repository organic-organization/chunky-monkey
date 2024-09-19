package com.lms.eureka.hub.presentation.request.hub;

public record CreateHubRequest(String name, String address, double latitude, double longitude, long routeIndex) {
}
