package com.the3dsandwich.haileyandweiweibackend.service.bean;/*
 * Copyright (c) 2024. the3dsandwich (Shann Wei Yeh)
 */

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

/*
 * Copyright (c) 2024. the3dsandwich (Shann Wei Yeh)
 */
@Data
@Builder
public class GuestBo {

    private long id;
    private String name;
    private String email;
    private String phone;
    private String comments;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
