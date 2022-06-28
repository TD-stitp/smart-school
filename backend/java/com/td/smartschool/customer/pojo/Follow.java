package com.td.smartschool.customer.pojo;

import lombok.*;

/**
 * @author K8lyN
 * @version v1.0
 * @date 2022/2/24 10:23
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
public class Follow {

    private int id;
    @NonNull
    private String code;
    @NonNull
    private String lastmessage;
}
