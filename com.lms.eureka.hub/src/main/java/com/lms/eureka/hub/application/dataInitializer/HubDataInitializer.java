package com.lms.eureka.hub.application.dataInitializer;

import com.lms.eureka.hub.domain.entity.hub.Hub;
import com.lms.eureka.hub.domain.service.HubDomainService;
import com.lms.eureka.hub.domain.service.HubRouteDomainService;
import com.lms.eureka.hub.presentation.request.hub.CreateHubRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class HubDataInitializer implements CommandLineRunner {

    private final HubDomainService hubDomainService;
    private final HubRouteDomainService hubRouteDomainService;

    @Override
    public void run(String... args) throws Exception {
        saveHubs();
        saveHubRoutes();
    }

    private void saveHubs() {
        String[][] hubData = {
                {"서울특별시 센터", "서울특별시 송파구 송파대로 55", "37.5005", "127.0687", "1", "system"},
                {"경기 북부 센터", "경기도 고양시 덕양구 권율대로 570", "37.6524", "126.8349", "2", "system"},
                {"경기 남부 센터", "경기도 이천시 덕평로 257-21", "37.2358", "127.3844", "3", "system"},
                {"부산광역시 센터", "부산 동구 중앙대로 206", "35.1140", "129.0420", "4", "system"},
                {"대구광역시 센터", "대구 북구 태평로 161", "35.8856", "128.5858", "5", "system"},
                {"인천광역시 센터", "인천 남동구 정각로 29", "37.4506", "126.7012", "6", "system"},
                {"광주광역시 센터", "광주 서구 내방로 111", "35.1513", "126.8880", "7", "system"},
                {"대전광역시 센터", "대전 서구 둔산로 100", "36.3504", "127.3845", "8", "system"},
                {"울산광역시 센터", "울산 남구 중앙로 201", "35.5384", "129.3114", "9", "system"},
                {"세종특별자치시 센터", "세종특별자치시 한누리대로 2130", "36.4800", "127.2890", "10", "system"},
                {"강원특별자치도 센터", "강원특별자치도 춘천시 중앙로 1", "37.8813", "127.7300", "11", "system"},
                {"충청북도 센터", "충북 청주시 상당구 상당로 82", "36.6360", "127.4914", "12", "system"},
                {"충청남도 센터", "충남 홍성군 홍북읍 충남대로 21", "36.6709", "126.6708", "13", "system"},
                {"전북특별자치도 센터", "전북특별자치도 전주시 완산구 효자로 225", "35.8242", "127.1480", "14", "system"},
                {"전라남도 센터", "전남 무안군 삼향읍 오룡길 1", "34.8131", "126.4661", "15", "system"},
                {"경상북도 센터", "경북 안동시 풍천면 도청대로 455", "36.5684", "128.7246", "16", "system"},
                {"경상남도 센터", "경남 창원시 의창구 중앙대로 300", "35.2322", "128.6811", "17", "system"}
        };

        for (String[] data : hubData) {
            CreateHubRequest request = new CreateHubRequest(data[0], data[1], Double.parseDouble(data[2]), Double.parseDouble(data[3]),
                    Integer.parseInt(data[4]));
            hubDomainService.createHub(request, "system");
        }
    }

    private void saveHubRoutes() {
        String[][] hubRouteData = {
                {"서울특별시 센터", "경기 북부 센터"},
                {"경기 북부 센터", "경기 남부 센터"},
                {"경기 남부 센터", "부산광역시 센터"},
                {"부산광역시 센터", "대구광역시 센터"},
                {"대구광역시 센터", "인천광역시 센터"},
                {"인천광역시 센터", "광주광역시 센터"},
                {"광주광역시 센터", "대전광역시 센터"},
                {"대전광역시 센터", "울산광역시 센터"},
                {"울산광역시 센터", "세종특별자치시 센터"},
                {"세종특별자치시 센터", "강원특별자치도 센터"},
                {"강원특별자치도 센터", "충청북도 센터"},
                {"충청북도 센터", "충청남도 센터"},
                {"충청남도 센터", "전북특별자치도 센터"},
                {"전북특별자치도 센터", "전라남도 센터"},
                {"전라남도 센터", "경상북도 센터"},
                {"경상북도 센터", "경상남도 센터"}
        };

        for (String[] data : hubRouteData) {
            Hub departureHub = hubDomainService.findHub(data[0]);
            Hub arrivalHub = hubDomainService.findHub(data[1]);
            hubRouteDomainService.createHubRoute(departureHub, arrivalHub, "system");
        }
    }


}
