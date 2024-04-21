package com.the3dsandwich.haileyandweiweibackend.service.bean;

import com.the3dsandwich.haileyandweiweibackend.utils.HWStringUtils;
import lombok.Getter;

/*
 * Copyright (c) 2024. the3dsandwich (Shann Wei Yeh)
 */
@Getter
public enum GuestTransportationEnum {

    DRIVE("drive"),
    MOTORCYCLE("motorcycle"),
    BIKE("bike"),
    PUBLIC_TRANSPORTATION("public"),
    RIDE("ride"),
    UBER("uber"),
    NONE(HWStringUtils.EMPTY);

    private final String code;

    GuestTransportationEnum(String code) {
        this.code = code;
    }

    public static GuestTransportationEnum of(String code) {
        String trimCode = HWStringUtils.trimToEmpty(code);
        for (GuestTransportationEnum e : GuestTransportationEnum.values()) {
            if (HWStringUtils.equals(e.code, trimCode)) {
                return e;
            }
        }
        return GuestTransportationEnum.NONE;
    }

    @Override
    public String toString() {
        return code;
    }

}
