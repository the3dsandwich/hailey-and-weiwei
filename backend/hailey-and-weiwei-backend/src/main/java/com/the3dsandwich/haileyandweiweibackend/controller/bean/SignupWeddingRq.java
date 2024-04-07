package com.the3dsandwich.haileyandweiweibackend.controller.bean;

import lombok.Data;

import java.util.List;

/*
 * Copyright (c) 2024. the3dsandwich (Shann Wei Yeh)
 */
@Data
public class SignupWeddingRq {

    private String name;
    private String email;
    private String phone;
    private String comments;
    private boolean vegetarian;
    private List<String> tags;

}
