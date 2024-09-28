package com.the3dsandwich.haileyandweiweibackend.service.bean;

import com.the3dsandwich.haileyandweiweibackend.utils.HWStringUtils;
import lombok.Getter;

/*
 * Copyright (c) 2024. the3dsandwich (Shann Wei Yeh)
 */
@Getter
public enum GuestTransportationEnum {

    DRIVE("drive", "\uD83D\uDE97 開車車（免費消磁，請直接停進地下室，婚宴結束後，將磁卡拿給酒店工作人員消磁即可）"),
    MOTORCYCLE("motorcycle",
               "\uD83C\uDFCD\uFE0F 騎車車（免費消磁，請直接停進地下室，婚宴結束後，將磁卡拿給酒店工作人員消磁即可）"),
    RIDE("ride", "\uD83D\uDE95 別人開車/大眾運輸/腳踏車/走路"),
    NONE("none", "\uD83E\uDD37 待確認");

    private final String code;
    private final String name;

    GuestTransportationEnum(String code, String name) {
        this.code = code;
        this.name = name;
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
