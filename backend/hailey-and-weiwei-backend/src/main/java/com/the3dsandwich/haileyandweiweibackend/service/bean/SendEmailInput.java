package com.the3dsandwich.haileyandweiweibackend.service.bean;

import lombok.Builder;
import lombok.Data;

/*
 * Copyright (c) 2024. the3dsandwich (Shann Wei Yeh)
 */
@Data
@Builder
public class SendEmailInput {

    private String emailFromName;
    private String emailFromAddress;
    private String emailToAddress;
    private String emailSubject;
    private String emailContentHtml;

}
