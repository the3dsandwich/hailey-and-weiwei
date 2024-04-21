package com.the3dsandwich.haileyandweiweibackend.service.bean;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

/*
 * Copyright (c) 2024. the3dsandwich (Shann Wei Yeh)
 */
@Data
@SuperBuilder
@NoArgsConstructor
public class GuestBo {

    private long id;
    private String name;
    private String nickname;
    private String email;
    private String phone;
    private String comments;
    private boolean vegetarian;
    private String friendOf;
    private GuestTransportationEnum transportation;
    private Boolean isPhysicalInvitation;
    private String physicalAddress;
    private List<String> tags;

}
