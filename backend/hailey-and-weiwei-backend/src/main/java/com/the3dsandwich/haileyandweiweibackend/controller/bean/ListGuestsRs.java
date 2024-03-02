package com.the3dsandwich.haileyandweiweibackend.controller.bean;

import lombok.Builder;
import lombok.Data;

import java.util.List;

/*
 * Copyright (c) 2024. the3dsandwich (Shann Wei Yeh)
 */
@Data
@Builder
public class ListGuestsRs {

    private List<ListGuestsRsGuest> guestList;

}
