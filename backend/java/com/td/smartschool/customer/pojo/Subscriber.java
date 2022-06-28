package com.td.smartschool.customer.pojo;

import lombok.*;

/**
 * @author K8lyN
 * @version v1.0
 * @date 2022/2/21 23:12
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
public class Subscriber {

    private int id;
    @NonNull
    private String studentID;
}
