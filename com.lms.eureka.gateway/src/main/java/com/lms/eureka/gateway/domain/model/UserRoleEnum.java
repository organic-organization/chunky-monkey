package com.lms.eureka.gateway.domain.model;

public enum UserRoleEnum {
    MASTER("MASTER"),
    HUB_MANAGER("HUB_MANAGER"),
    DELIVERY_AGENT("DELIVERY_AGENT"),
    COMPANY_MANAGER("COMPANY_MANAGER"),
    ;

    UserRoleEnum(String authority) {
        this.authority = authority;
    }

    private final String authority;

    public String getAuthority() {
        return this.authority;
    }
}
