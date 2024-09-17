package com.the3dsandwich.haileyandweiweibackend.controller;

import com.the3dsandwich.haileyandweiweibackend.controller.bean.SignupWeddingRq;
import com.the3dsandwich.haileyandweiweibackend.controller.bean.SignupWeddingRs;
import com.the3dsandwich.haileyandweiweibackend.service.GuestService;
import com.the3dsandwich.haileyandweiweibackend.service.HWEmailService;
import com.the3dsandwich.haileyandweiweibackend.service.bean.GuestBo;
import com.the3dsandwich.haileyandweiweibackend.service.bean.SendEmailInput;
import com.the3dsandwich.haileyandweiweibackend.utils.HWStringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/*
 * Copyright (c) 2024. the3dsandwich (Shann Wei Yeh)
 */
@Slf4j
@RestController
public class WeddingController {

    @Autowired
    private HWEmailService hwEmailService;
    @Autowired
    private GuestService guestService;

    @PostMapping("/signup")
    public SignupWeddingRs signupWedding(@RequestBody SignupWeddingRq request) {
        guestService.addGuest(request);
        GuestBo companion = GuestBo.builder()
                                   .name(request.getCompanionName())
                                   .vegetarian(request.isCompanionVegetarian())
                                   .friendOf(request.getName())
                                   .email(request.getEmail())
                                   .nickname(HWStringUtils.format("{}'s companion", request.getName()))
                                   .isPhysicalInvitation(false)
                                   .comments(HWStringUtils.format("{}'s companion", request.getName()))
                                   .build();
        companion.setTransportation(request.getTransportation());
        guestService.addGuest(companion);
        String responseMessage = HWStringUtils.format("You, {}, will be called, BECAUSE I KNOW YOUR EMAIL IS {} AND YOU SAID \"{}\"", request.getName(), request.getEmail(), request.getComments());
        SendEmailInput sendEmailInput = SendEmailInput.builder()
                                                      .emailSubject("Hailey and Wei-Wei's Wedding")
                                                      .emailFromName(switch (request.getFriendOf()) {
                                                          case "Wei-Wei" -> "葉善維 Wei-Wei";
                                                          case "Hailey" -> "許肇倫 Hailey";
                                                          case "BOL" -> "肇倫和善維 Hailey & Wei-Wei";
                                                          default ->
                                                                  throw new IllegalStateException("Unexpected value: " + request.getFriendOf());
                                                      })
                                                      .emailFromAddress(switch (request.getFriendOf()) {
                                                          case "Wei-Wei" -> "weiwei@wedding.haileyweiwei.com";
                                                          case "Hailey" -> "hailey@wedding.haileyweiwei.com";
                                                          case "BOL" -> "haileyandweiwei@wedding.haileyweiwei.com";
                                                          default ->
                                                                  throw new IllegalStateException("Unexpected value: " + request.getFriendOf());
                                                      })
                                                      .emailToAddress(request.getEmail())
                                                      .emailContentHtml(HWStringUtils.format("""
                                                                <body>
                                                                  <h1>Thanks for signing up to our wedding, {}!</h1>
                                                                  <p>Here's a copy of your comment for reference: {}</p>
                                                                </body>
                                                              """, request.getName(), request.getComments()))
                                                      .build();
        log.debug("email content:\n{}", sendEmailInput.getEmailContentHtml());
        hwEmailService.sendEmail(sendEmailInput);
        return SignupWeddingRs.builder()
                              .message(responseMessage)
                              .build();
    }

}
