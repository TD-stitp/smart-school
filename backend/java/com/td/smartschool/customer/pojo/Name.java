package com.td.smartschool.customer.pojo;

import lombok.*;

/**
 * @author K8lyN
 * @version v1.0
 * @date 2022/3/12 18:34
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
public class Name {

    private int id;
    @NonNull
    private String code;
    @NonNull
    private String name;
}
