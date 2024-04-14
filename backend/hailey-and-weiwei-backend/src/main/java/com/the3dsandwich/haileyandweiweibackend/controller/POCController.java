package com.the3dsandwich.haileyandweiweibackend.controller;

import com.resend.Resend;
import com.resend.core.exception.ResendException;
import com.resend.services.emails.model.SendEmailRequest;
import com.resend.services.emails.model.SendEmailResponse;
import com.the3dsandwich.haileyandweiweibackend.controller.bean.SendEmailRq;
import com.the3dsandwich.haileyandweiweibackend.controller.bean.SendEmailRs;
import com.the3dsandwich.haileyandweiweibackend.controller.bean.SignupWeddingRs;
import com.the3dsandwich.haileyandweiweibackend.service.GuestService;
import com.the3dsandwich.haileyandweiweibackend.service.HWEmailService;
import com.the3dsandwich.haileyandweiweibackend.service.bean.GuestBo;
import com.the3dsandwich.haileyandweiweibackend.service.bean.ListGuestsOutput;
import com.the3dsandwich.haileyandweiweibackend.utils.HWJsonUtils;
import com.the3dsandwich.haileyandweiweibackend.utils.HWStringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/*
 * Copyright (c) 2024. the3dsandwich (Shann Wei Yeh)
 */
@Slf4j
@RestController
public class POCController {

    @Autowired
    private HWEmailService hwEmailService;
    @Autowired
    private GuestService guestService;

    @PostMapping("/poc/debugInjectedResendApiKey")
    public void debugInjectedResendApiKey() {
        hwEmailService.debugInjectedResendApiKey();
    }

    @PostMapping("/poc/pocDatabaseConnection")
    public void pocDatabaseConnection() {
        guestService.pocGetEntities();
    }

    @GetMapping("/poc/pocListGuests")
    public ListGuestsOutput pocListGuests() {
        return guestService.listGuests();
    }

    @PostMapping("/poc/pocInsertDb")
    public void pocInsertDb() {
        UUID uuid = UUID.randomUUID();
        for (int i = 0; i < 5; i++) {
            guestService.addGuest(GuestBo.builder()
                                         .name(HWStringUtils.format("Test Name {} {}", uuid, i))
                                         .email(HWStringUtils.format("{}.{}@gmail.com", uuid, i))
                                         .phone(HWStringUtils.format("{}{}", uuid, i))
                                         .comments(HWStringUtils.format("Test comment\n{}\n{}", uuid, i))
                                         .build());
        }
    }

    @GetMapping("/poc/pocList")
    public List<SignupWeddingRs> pocList() {
        List<SignupWeddingRs> response = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            response.add(SignupWeddingRs.builder()
                                        .message(HWStringUtils.format("{}", i))
                                        .build());
        }
        return response;
    }

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
