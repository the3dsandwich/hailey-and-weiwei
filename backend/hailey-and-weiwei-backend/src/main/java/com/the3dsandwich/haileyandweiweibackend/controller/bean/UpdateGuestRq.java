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
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class UpdateGuestRq extends GuestBo {

}
