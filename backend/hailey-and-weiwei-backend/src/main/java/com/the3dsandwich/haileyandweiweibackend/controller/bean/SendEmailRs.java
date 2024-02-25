package com.the3dsandwich.haileyandweiweibackend.controller.bean;

import lombok.Builder;
import lombok.Data;

/*
 * Copyright (c) 2024. the3dsandwich (Shann Wei Yeh)
 */
@Data
@Builder
public class SendEmailRs {

    private String errorMessage;
    private String status;

}
