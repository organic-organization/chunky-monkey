package com.lms.eureka.user.application.dataInitializer;

import com.lms.eureka.user.application.dto.UserDto;
import com.lms.eureka.user.application.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class UserDataInitializer implements CommandLineRunner {

    private final UserService userService;

    @Override
    public void run(String... args) throws Exception {
        //saveUsers();
    }

    private void saveUsers() {
        List<UserDto> userDtoList = new ArrayList<>();
        userDtoList.add(UserDto.signUpOf("master", "Master1234!", "MASTER", "master@slack.com"));

        userDtoList.add(UserDto.signUpOf("hubmanager1", "hubManager1234!", "HUB_MANAGER", "hubmanager1@slack.com"));
        userDtoList.add(UserDto.signUpOf("hubmanager2", "hubManager1234!", "HUB_MANAGER", "hubmanager2@slack.com"));

        userDtoList.add(UserDto.signUpOf("deliveryagent1", "deliveryAgent1234!", "DELIVERY_AGENT", "deliveryagent1@slack.com"));
        userDtoList.add(UserDto.signUpOf("deliveryagent2", "deliveryAgent1234!", "DELIVERY_AGENT", "deliveryagent2@slack.com"));
        userDtoList.add(UserDto.signUpOf("deliveryagent3", "deliveryAgent1234!", "DELIVERY_AGENT", "deliveryagent3@slack.com"));
        userDtoList.add(UserDto.signUpOf("deliveryagent4", "deliveryAgent1234!", "DELIVERY_AGENT", "deliveryagent4@slack.com"));
        userDtoList.add(UserDto.signUpOf("deliveryagent5", "deliveryAgent1234!", "DELIVERY_AGENT", "deliveryagent5@slack.com"));
        userDtoList.add(UserDto.signUpOf("deliveryagent6", "deliveryAgent1234!", "DELIVERY_AGENT", "deliveryagent6@slack.com"));
        userDtoList.add(UserDto.signUpOf("deliveryagent7", "deliveryAgent1234!", "DELIVERY_AGENT", "deliveryagent7@slack.com"));
        userDtoList.add(UserDto.signUpOf("deliveryagent8", "deliveryAgent1234!", "DELIVERY_AGENT", "deliveryagent8@slack.com"));
        userDtoList.add(UserDto.signUpOf("deliveryagent9", "deliveryAgent1234!", "DELIVERY_AGENT", "deliveryagent9@slack.com"));
        userDtoList.add(UserDto.signUpOf("deliveryagent10", "deliveryAgent1234!", "DELIVERY_AGENT", "deliveryagent10@slack.com"));

        userDtoList.add(UserDto.signUpOf("companymanager1", "companyManager1234!", "COMPANY_MANAGER", "companymanager1@slack.com"));
        userDtoList.add(UserDto.signUpOf("companymanager2", "companyManager1234!", "COMPANY_MANAGER", "companymanager2@slack.com"));
        userDtoList.add(UserDto.signUpOf("companymanager3", "companyManager1234!", "COMPANY_MANAGER", "companymanager3@slack.com"));
        userDtoList.add(UserDto.signUpOf("companymanager4", "companyManager1234!", "COMPANY_MANAGER", "companymanager4@slack.com"));
        userDtoList.add(UserDto.signUpOf("companymanager5", "companyManager1234!", "COMPANY_MANAGER", "companymanager5@slack.com"));
        userDtoList.add(UserDto.signUpOf("companymanager6", "companyManager1234!", "COMPANY_MANAGER", "companymanager6@slack.com"));

        for(UserDto userDto : userDtoList){
            userService.signUp(userDto);
        }
    }
}
