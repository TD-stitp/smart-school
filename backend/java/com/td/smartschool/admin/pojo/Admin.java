package com.td.smartschool.admin.pojo;

import lombok.*;

/**
 * @author K8lyN
 * @version v1.0
 * @date 2022/4/15 20:26
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class Admin {


    private int id;
    @NonNull
    private String nickname;
    @NonNull
    private String account;
    @NonNull
    private String password;
    private AdminPermission permission;

}
