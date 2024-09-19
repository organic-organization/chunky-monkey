package com.lms.eureka.delivery.domain.entity.type;

public enum OrderStatus {

    ORDER_IN_PROGRESS("주문 진행 중"),
    PREPARING_PRODUCTS("물품 준비중"),
    COMPANY_TO_HUB("업체에서 허브로 이동"),
    HUB_TO_HUB("허브에서 허브로 이동"),
    HUB_TO_DESTINATION("허브에서 목적지 이동"),
    DELIVERY_COMPLETED("배송 완료"),
    ORDER_CANCELED("주문 취소")
    ;

    OrderStatus(String name) {
        this.name = name;
    }

    private final String name;

    public String getName() {
        return this.name;
    }
}
