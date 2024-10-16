package com.the3dsandwich.haileyandweiweibackend.controller.bean;

import com.the3dsandwich.haileyandweiweibackend.service.bean.GuestBo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

/*
 * Copyright (c) 2024. the3dsandwich (Shann Wei Yeh)
 */
@Data
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
public class SignupWeddingRq extends GuestBo {

    private boolean isBringCompanion;
    private String companionName;
    private boolean companionVegetarian;

}
