package com.hms.model;

import java.io.Serializable;

public enum UserProfileType implements Serializable {
    USER("USER"),
    MANAGER("MANAGER"),
    ADMIN("ADMIN");

    String userProfileType;

    private UserProfileType(String userProfileType) {
        this.userProfileType = userProfileType;
    }

    public String getUserProfileType() {
        return userProfileType;
    }

}
