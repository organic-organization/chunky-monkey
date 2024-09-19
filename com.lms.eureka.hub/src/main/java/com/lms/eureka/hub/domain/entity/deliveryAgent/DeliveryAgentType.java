package com.lms.eureka.hub.domain.entity.deliveryAgent;

import com.lms.eureka.hub.domain.exception.HubException;
import com.lms.eureka.hub.domain.exception.HubExceptionCase;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum DeliveryAgentType {

    HUB_TRANSFER("hub_transfer"),
    COMPANY_DELIVERY("company_delivery");

    private final String value;

    public static DeliveryAgentType fromValue(String value) {
        for (DeliveryAgentType type : DeliveryAgentType.values()) {
            if (type.getValue().equalsIgnoreCase(value)) {
                return type;
            }
        }
        throw new HubException(HubExceptionCase.UNKNOWN_DELIVERY_AGENT_TYPE);
    }

}

