package com.the3dsandwich.haileyandweiweibackend.service;

import com.resend.Resend;
import com.resend.core.exception.ResendException;
import com.resend.services.emails.model.SendEmailRequest;
import com.resend.services.emails.model.SendEmailResponse;
import com.the3dsandwich.haileyandweiweibackend.service.bean.SendEmailInput;
import com.the3dsandwich.haileyandweiweibackend.service.bean.SendEmailOutput;
import com.the3dsandwich.haileyandweiweibackend.utils.HWJsonUtils;
import com.the3dsandwich.haileyandweiweibackend.utils.HWStringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/*
 * Copyright (c) 2024. the3dsandwich (Shann Wei Yeh)
 */
@Slf4j
@Service
public class HWEmailService {

    @Value("${email.resend_api_key}")
    private String resendApiKey;

    public void debugInjectedResendApiKey() {
        log.debug("injected resendApiKey is {}", resendApiKey);
    }

    public SendEmailOutput sendEmail(SendEmailInput input) {
        SendEmailRequest sendEmailRequest = buildSendEmailRequest(input);

        if (log.isDebugEnabled()) {
            log.debug("sending email:\n{}", HWJsonUtils.toPrettyJson(sendEmailRequest));
        }

        try {
            SendEmailResponse data = new Resend(resendApiKey).emails()
                                                             .send(sendEmailRequest);
            if (log.isDebugEnabled()) {
                log.debug("send email success:\n{}", HWJsonUtils.toPrettyJson(data));
            }

            return SendEmailOutput.builder()
                                  .errorMessage(HWStringUtils.EMPTY)
                                  .status("SUCCESS")
                                  .build();
        } catch (ResendException e) {
            log.error("send email failed (resend): {}", e.getMessage());
            return SendEmailOutput.builder()
                                  .errorMessage(e.getMessage())
                                  .status("FAIL")
                                  .build();
        } catch (RuntimeException e) {
            log.error("send email failed: {}", e.getMessage());
            return SendEmailOutput.builder()
                                  .errorMessage(e.getMessage())
                                  .status("FAIL")
                                  .build();
        }
    }

    private SendEmailRequest buildSendEmailRequest(SendEmailInput input) {
        return SendEmailRequest.builder()
                               .from(HWStringUtils.format("{} <{}>", input.getEmailFromName(), input.getEmailFromAddress()))
                               .to(input.getEmailToAddress())
                               .subject(input.getEmailSubject())
                               .html(input.getEmailContentHtml())
                               .build();
    }

}
