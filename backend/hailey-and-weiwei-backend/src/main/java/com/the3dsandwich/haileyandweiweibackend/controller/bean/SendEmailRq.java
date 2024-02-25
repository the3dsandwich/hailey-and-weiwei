package com.the3dsandwich.haileyandweiweibackend.controller.bean;

import lombok.Data;

/*
 * Copyright (c) 2024. the3dsandwich (Shann Wei Yeh)
 */
@Data
public class SendEmailRq {

    private String resendApiKey;
    private String emailFromName;
    private String emailFromAddress;
    private String emailToAddress;
    private String emailSubject;
    private String emailContentHtml;

}
