package com.td.smartschool.admin.pojo;

import lombok.*;

/**
 * @author K8lyN
 * @version v1.0
 * @date 2022/2/10 22:17
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
public class User {

    private static final String ROLE = "anchor";
    private static final String PERMISSION = "admin";

    private int id;
    @NonNull
    private String account;
    private String name;
    private String department;
    private String reason;

    public String getRole() {
        return ROLE;
    }

    public String getPermission() {
        return ROLE + ":" + PERMISSION;
    }
}
