package com.the3dsandwich.haileyandweiweibackend.controller;

import com.the3dsandwich.haileyandweiweibackend.controller.bean.SignupWeddingRq;
import com.the3dsandwich.haileyandweiweibackend.controller.bean.SignupWeddingRs;
import com.the3dsandwich.haileyandweiweibackend.service.GuestService;
import com.the3dsandwich.haileyandweiweibackend.service.HWEmailService;
import com.the3dsandwich.haileyandweiweibackend.service.bean.GuestBo;
import com.the3dsandwich.haileyandweiweibackend.service.bean.SendEmailInput;
import com.the3dsandwich.haileyandweiweibackend.service.bean.SendEmailOutput;
import com.the3dsandwich.haileyandweiweibackend.utils.HWStringUtils;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.BooleanUtils;
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

    private static final String EMAIL_SUBJECT = "葉許府囍宴｜Welcome to Hailey and Wei-Wei's Wedding";

    @Autowired
    private HWEmailService hwEmailService;
    @Autowired
    private GuestService guestService;

    @PostMapping("/signup")
    public SignupWeddingRs signupWedding(@RequestBody SignupWeddingRq request) {
        // sign up guest
        addGuest(request);

        // sign up companion if exist
        if (BooleanUtils.isTrue(request.isBringCompanion())) {
            log.debug("{} brings companion {}", request.getName(), request.getCompanionName());
            addCompanion(request);
        }

        // send email to guest
        SendEmailOutput sendEmailOutput = sendEmail(request);

        // build API response
        return SignupWeddingRs.builder()
                              .message(sendEmailOutput.getStatus())
                              .build();
    }

    private void addGuest(SignupWeddingRq request) {
        guestService.addGuest(request);
    }

    private void addCompanion(SignupWeddingRq request) {
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
    }

    private SendEmailOutput sendEmail(SignupWeddingRq request) {
        SendEmailInput sendEmailInput = SendEmailInput.builder()
                                                      .emailSubject(EMAIL_SUBJECT)
                                                      .emailFromName(From.of(request.getFriendOf())
                                                                         .getFromName())
                                                      .emailFromAddress(From.of(request.getFriendOf())
                                                                            .getFromEmailAddress())
                                                      .emailToAddress(HWStringUtils.trimToEmpty(request.getEmail()))
                                                      .emailContentHtml(buildContentHtml(request))
                                                      .build();
        return hwEmailService.sendEmail(sendEmailInput);
    }

    private String buildContentHtml(SignupWeddingRq request) {
        String contentHtml = HWStringUtils.format("""
                  <body>
                    <h1>Thanks for signing up to our wedding, {}!</h1>
                    <p>Here's a copy of your comment for reference: {}</p>
                    <iframe
                      title="le meridien"
                      src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d57843.81565744926!2d121.5193902!3d25.025979700000008!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x3442abba50d70b7b%3A0x313d65ce289c8267!2z5Y-w5YyX5a-S6IiN6Im-576O6YWS5bqX!5e0!3m2!1szh-TW!2stw!4v1714287626753!5m2!1szh-TW!2stw"
                      loading="lazy"
                    />
                  </body>
                """, request.getName(), request.getComments());

        log.debug("email content:\n{}", contentHtml);

        return contentHtml;
    }

    @Getter
    private enum From {

        WEIWEI("Wei-Wei", "葉善維 Wei-Wei", "weiwei@wedding.haileyweiwei.com"),
        HAILEY("Hailey", "許肇倫 Hailey", "hailey@wedding.haileyweiwei.com"),
        BOL("BOL", "肇倫和善維 Hailey and Wei-Wei", "haileyandweiwei@wedding.haileyweiwei.com"),
        ;

        private final String code;
        private final String fromName;
        private final String fromEmailAddress;

        From(String code, String name, String address) {
            this.code = code;
            this.fromName = name;
            this.fromEmailAddress = address;
        }

        public static From of(String friendOf) {
            return switch (friendOf) {
                case "Wei-Wei" -> WEIWEI;
                case "Hailey" -> HAILEY;
                case "BOL" -> BOL;
                default -> throw new IllegalStateException("Unexpected value: " + friendOf);
            };
        }

    }

}
