package com.td.smartschool.customer.pojo;

import lombok.*;

import java.util.Date;

/**
 * @author K8lyN
 * @version v1.0
 * @date 2022/2/26 11:58
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
public class Channel {

    private int id;
    @NonNull
    private String code;
    @NonNull
    private String name;
    @NonNull
    private Date createtime;

}
