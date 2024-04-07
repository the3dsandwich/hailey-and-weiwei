package com.the3dsandwich.haileyandweiweibackend.controller.bean;

import lombok.Builder;
import lombok.Data;

import java.util.List;

/*
 * Copyright (c) 2024. the3dsandwich (Shann Wei Yeh)
 */
@Data
@Builder
public class UpdateGuestRq {

    private long id;
    private String name;
    private String email;
    private String phone;
    private String comments;
    private List<String> tags;

}
