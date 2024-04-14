package com.the3dsandwich.haileyandweiweibackend.service.bean;

import lombok.Builder;
import lombok.Data;

import java.util.List;

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
    private boolean vegetarian;
    private String friendOf;
    private List<String> tags;

}
