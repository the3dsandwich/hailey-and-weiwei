package com.the3dsandwich.haileyandweiweibackend.controller;

import com.resend.Resend;
import com.resend.core.exception.ResendException;
import com.resend.services.emails.model.SendEmailRequest;
import com.resend.services.emails.model.SendEmailResponse;
import com.the3dsandwich.haileyandweiweibackend.controller.bean.SendEmailRq;
import com.the3dsandwich.haileyandweiweibackend.controller.bean.SendEmailRs;
import com.the3dsandwich.haileyandweiweibackend.utils.HWJsonUtils;
import com.the3dsandwich.haileyandweiweibackend.utils.HWStringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/*
 * Copyright (c) 2024. the3dsandwich (Shann Wei Yeh)
 */
@Slf4j
@RestController
public class POCController {

    @PostMapping("/poc/sendEmail")
    public SendEmailRs sendEmail(@RequestBody SendEmailRq request) {
        Resend resend = new Resend(request.getResendApiKey());

        SendEmailRequest sendEmailRequest = SendEmailRequest.builder()
                                                            .from(HWStringUtils.join(request.getEmailFromName(), " <", request.getEmailFromAddress(), ">"))
                                                            .to(request.getEmailToAddress())
                                                            .subject(request.getEmailSubject())
                                                            .html(request.getEmailContentHtml())
                                                            .build();

        if (log.isDebugEnabled()) {
            log.debug("sending email:\n{}", HWJsonUtils.toPrettyJson(request));
        }

        try {
            SendEmailResponse data = resend.emails()
                                           .send(sendEmailRequest);
            if (log.isDebugEnabled()) {
                log.debug("send email success:\n{}", HWJsonUtils.toPrettyJson(data));
            }

            return SendEmailRs.builder()
                              .errorMessage(HWStringUtils.EMPTY)
                              .status("SUCCESS")
                              .build();
        } catch (ResendException e) {
            log.error("send email failed (resend): {}", e.getMessage());
            e.printStackTrace();
            return SendEmailRs.builder()
                              .errorMessage(e.getMessage())
                              .status("FAIL")
                              .build();
        } catch (RuntimeException e) {
            log.error("send email failed: {}", e.getMessage());
            e.printStackTrace();
            return SendEmailRs.builder()
                              .errorMessage(e.getMessage())
                              .status("FAIL")
                              .build();
        }
    }

}
