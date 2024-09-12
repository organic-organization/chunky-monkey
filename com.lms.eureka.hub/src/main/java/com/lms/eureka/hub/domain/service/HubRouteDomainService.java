package com.lms.eureka.hub.domain.service;

import com.lms.eureka.hub.domain.entity.hub.Hub;
import com.lms.eureka.hub.domain.entity.hubRoute.HubRoute;
import com.lms.eureka.hub.domain.repository.HubRouteRepository;
import com.lms.eureka.hub.presentation.request.hubRoute.SearchHubRouteRequest;
import java.time.Duration;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class HubRouteDomainService {

    private final HubRouteRepository hubRouteRepository;

    @Transactional
    public HubRoute createHubRoute(Hub departureHub, Hub arrivalHub, String username) {
        String displayName = generateDisplayName(departureHub, arrivalHub);
        long distance = calculateDistance(departureHub, arrivalHub);
        Duration transitTime = calculateTransitTime(distance);
        return saveHubRoute(departureHub, arrivalHub, displayName, distance, transitTime, username);
    }

    private String generateDisplayName(Hub departureHub, Hub arrivalHub) {
        return String.format("%s To %s", departureHub.getName(), arrivalHub.getName());
    }

    private long calculateDistance(Hub departureHub, Hub arrivalHub) {
        double lat1 = departureHub.getLatitude();
        double lon1 = departureHub.getLongitude();
        double lat2 = arrivalHub.getLatitude();
        double lon2 = arrivalHub.getLongitude();

        final int R = 6371;
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) *
                        Math.sin(dLon / 2) * Math.sin(dLon / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return (long) (R * c);
    }

    private Duration calculateTransitTime(long distance) {
        final double speed = 60.0;
        long minutes = (long) (distance / speed * 60);
        return Duration.ofMinutes(minutes);
    }

    private HubRoute saveHubRoute(Hub departureHub, Hub arrivalHub, String displayName, long distance, Duration transitTime, String username) {
        HubRoute hubRoute = HubRoute.create(departureHub, arrivalHub, displayName, distance, transitTime, username);
        return hubRouteRepository.save(hubRoute);
    }

    @Transactional(readOnly = true)
    public Page<HubRoute> searchHubRoute(SearchHubRouteRequest requestParam, Pageable pageable) {
        return hubRouteRepository.searchHubRoute(requestParam, pageable);
    }

}
