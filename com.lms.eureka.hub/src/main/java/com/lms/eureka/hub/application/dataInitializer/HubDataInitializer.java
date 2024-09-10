package com.lms.eureka.hub.application.dataInitializer;

import com.lms.eureka.hub.domain.entity.hub.Hub;
import com.lms.eureka.hub.domain.repository.HubRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class HubDataInitializer implements CommandLineRunner {

    private final HubRepository hubRepository;

    @Override
    public void run(String... args) throws Exception {
        saveHubs();
    }

    private void saveHubs() {
        String[][] hubData = {
                {"서울특별시 센터", "서울특별시 송파구 송파대로 55", "0", "0", "1", "system"},
                {"경기 북부 센터", "경기도 고양시 덕양구 권율대로 570", "0", "0", "2", "system"},
                {"경기 남부 센터", "경기도 이천시 덕평로 257-21", "0", "0", "3", "system"},
                {"부산광역시 센터", "부산 동구 중앙대로 206", "0", "0", "4", "system"},
                {"대구광역시 센터", "대구 북구 태평로 161대구 북구 태평로 161", "0", "0", "5", "system"},
                {"인천광역시 센터", "인천 남동구 정각로 29", "0", "0", "6", "system"},
                {"광주광역시 센터", "광주 서구 내방로 111", "0", "0", "7", "system"},
                {"대전광역시 센터", "대전 서구 둔산로 100", "0", "0", "8", "system"},
                {"울산광역시 센터", "울산 남구 중앙로 201", "0", "0", "9", "system"},
                {"세종특별자치시 센터", "세종특별자치시 한누리대로 2130", "0", "0", "10", "system"},
                {"강원특별자치도 센터", "강원특별자치도 춘천시 중앙로 1", "0", "0", "11", "system"},
                {"충청북도 센터", "충북 청주시 상당구 상당로 82", "0", "0", "12", "system"},
                {"충청남도 센터", "충남 홍성군 홍북읍 충남대로 21", "0", "0", "13", "system"},
                {"전북특별자치도 센터", "전북특별자치도 전주시 완산구 효자로 225", "0", "0", "14", "system"},
                {"전라남도 센터", "전남 무안군 삼향읍 오룡길 1", "0", "0", "15", "system"},
                {"경상북도 센터", "경북 안동시 풍천면 도청대로 455", "0", "0", "16", "system"},
                {"경상남도 센터", "경남 창원시 의창구 중앙대로 300", "0", "0", "17", "system"}
        };

        for (String[] data : hubData) {
            Hub hub = Hub.create(data[0], data[1],
                    Integer.parseInt(data[2]), Integer.parseInt(data[3]),
                    Integer.parseInt(data[4]), data[5]);
            hubRepository.save(hub);
        }
    }

}
