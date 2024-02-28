package com.the3dsandwich.haileyandweiweibackend.service.bean;

import lombok.Builder;
import lombok.Data;

/*
 * Copyright (c) 2024. the3dsandwich (Shann Wei Yeh)
 */
@Data
@Builder
public class SendEmailOutput {

    private String errorMessage;
    private String status;

}
