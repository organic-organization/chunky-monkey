package com.lms.eureka.user.domain.model;

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

    public static UserRoleEnum fromString(String authority) {
        for (UserRoleEnum role : UserRoleEnum.values()) {
            if (role.getAuthority().equalsIgnoreCase(authority)) {
                return role;
            }
        }

        throw new IllegalArgumentException("No enum constant for authority: " + authority);
    }
}
