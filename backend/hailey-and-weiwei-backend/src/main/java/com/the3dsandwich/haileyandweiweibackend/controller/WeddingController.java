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
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

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
        String responseMessage = HWStringUtils.format("You, {}, will be called, BECAUSE I KNOW YOUR PHONE IS {} AND EMAIL {} AND YOU SAID \"{}\"", request.getName(), request.getPhone(), request.getEmail(), request.getComments());
        SendEmailInput sendEmailInput = SendEmailInput.builder()
                                                      .emailSubject("Hailey and Wei-Wei's Wedding")
                                                      .emailFromName("Wei-Wei")
                                                      .emailFromAddress("poctest@haileyandweiweiwedding.the3dsandwich.com")
                                                      .emailToAddress(request.getEmail())
                                                      .emailContentHtml(HWStringUtils.format("""
                                                                <body>
                                                                  <h1>Thanks for signing up to our wedding, {}!</h1>
                                                                  <p>Here's a copy of your comment for reference: {}</p>
                                                                </body>
                                                              """, request.getName(), request.getComments()))
                                                      .build();
        log.debug("email content:\n{}", sendEmailInput.getEmailContentHtml());
        guestService.addGuest(GuestBo.builder()
                                     .name(request.getName())
                                     .email(request.getEmail())
                                     .phone(request.getPhone())
                                     .comments(request.getComments())
                                     .tags(toTags(request))
                                     .build());
        hwEmailService.sendEmail(sendEmailInput);
        return SignupWeddingRs.builder()
                              .message(responseMessage)
                              .build();
    }

    private List<String> toTags(SignupWeddingRq request) {
        if (request == null) {
            return null;
        }
        List<String> tags = new ArrayList<>();
        if (request.isVegetarian()) {
            tags.add("Vegetarian");
        }
        if (!CollectionUtils.isEmpty(request.getTags())) {
            tags.addAll(request.getTags());
        }
        return tags;
    }

}
