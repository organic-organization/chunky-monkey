package com.lms.eureka.hub.domain.service;

import com.lms.eureka.hub.domain.entity.hub.Hub;
import com.lms.eureka.hub.domain.entity.hubRoute.HubRoute;
import com.lms.eureka.hub.domain.repository.HubRouteRepository;
import com.lms.eureka.hub.presentation.request.hubRoute.SearchHubRouteRequest;
import java.time.Duration;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class HubRouteDomainService {

    private final HubRouteRepository hubRouteRepository;
    private final HubDomainService hubDomainService;

    @Transactional
    public HubRoute createHubRoute(Hub departureHub, Hub arrivalHub, String username) {
        long distance = calculateDistance(departureHub, arrivalHub);
        Duration transitTime = calculateTransitTime(distance);
        return saveHubRoute(departureHub, arrivalHub, distance, transitTime, username);
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

    private HubRoute saveHubRoute(Hub departureHub, Hub arrivalHub, long distance, Duration transitTime,
                                  String username) {
        HubRoute hubRoute = HubRoute.create(departureHub, arrivalHub, distance, transitTime, username);
        return hubRouteRepository.save(hubRoute);
    }

    @Transactional(readOnly = true)
    public Page<HubRoute> searchHubRoute(SearchHubRouteRequest requestParam, Pageable pageable) {
        return hubRouteRepository.searchHubRoute(requestParam, pageable);
    }

    @Transactional
    public HubRoute findHubRoute(Hub departureHub, Hub arrivalHub) {
        Optional<HubRoute> hubRoute = hubRouteRepository.findByDepartureHubAndArrivalHubAndIsDeleteFalse(departureHub,
                arrivalHub);
        if (hubRoute.isPresent()) {
            return hubRoute.get();
        }
        return makeNewHubRoute(departureHub, arrivalHub);
    }

    private HubRoute makeNewHubRoute(Hub departureHub, Hub arrivalHub) {
        long startIndex = Math.min(departureHub.getRouteIndex(), arrivalHub.getRouteIndex());
        long endIndex = Math.max(departureHub.getRouteIndex(), arrivalHub.getRouteIndex());

        if (endIndex - startIndex == 1) {
            // Direct route
            long distance = calculateDistance(departureHub, arrivalHub);
            Duration transitTime = calculateTransitTime(distance);
            return saveHubRoute(departureHub, arrivalHub, distance, transitTime, "system");
        } else {
            // Composite route
            long midIndex = (startIndex + endIndex) / 2;
            Hub midHub = hubDomainService.findHub(midIndex);

            HubRoute firstHalf = findHubRoute(departureHub, midHub);
            HubRoute secondHalf = findHubRoute(midHub, arrivalHub);

            long totalDistance = firstHalf.getDistance() + secondHalf.getDistance();
            Duration totalTransitTime = firstHalf.getTransitTime().plus(secondHalf.getTransitTime());

            HubRoute parentRoute = saveHubRoute(departureHub, arrivalHub, totalDistance, totalTransitTime, "system");

            parentRoute.getSubRoutes().add(firstHalf);
            parentRoute.getSubRoutes().add(secondHalf);
            firstHalf.setParentRoute(parentRoute);
            secondHalf.setParentRoute(parentRoute);

            hubRouteRepository.save(firstHalf);
            hubRouteRepository.save(secondHalf);
            return hubRouteRepository.save(parentRoute);
        }
    }

}