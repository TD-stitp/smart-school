package com.td.smartschool.admin.pojo;

/**
 * @author K8lyN
 * @version 1.0
 * @date 2022年4月16日 21:23:10
 */

public enum AdminPermission {

    /**
     * 超级
     */
    ALL("all"),

    /**
     * 横幅
     */
    BANNER("banner"),

    /**
     * 频道
     */
    CHANNEL("channel");

    private final String type;

    AdminPermission(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return this.type;
    }
}
