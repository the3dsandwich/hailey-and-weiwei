package com.the3dsandwich.haileyandweiweibackend.service.bean;/*
 * Copyright (c) 2024. the3dsandwich (Shann Wei Yeh)
 */

import lombok.Builder;
import lombok.Data;

import java.util.List;

/*
 * Copyright (c) 2024. the3dsandwich (Shann Wei Yeh)
 */
@Data
@Builder
public class ListGuestsOutput {

    List<GuestBo> guestList;

}
