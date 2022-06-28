package com.td.smartschool.common.modules.shiro.entity;

public enum LoginType {

    /**
     *
     */
    USER("User"),
    /**
     *
     */
    ADMIN("Admin");

    private final String type;

    LoginType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return this.type;
    }

}
