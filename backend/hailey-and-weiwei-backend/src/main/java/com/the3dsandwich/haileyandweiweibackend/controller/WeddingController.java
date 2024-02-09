package com.the3dsandwich.haileyandweiweibackend.controller;

import com.the3dsandwich.haileyandweiweibackend.controller.bean.SignupWeddingRq;
import com.the3dsandwich.haileyandweiweibackend.controller.bean.SignupWeddingRs;
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
public class WeddingController {

    @PostMapping("/signup")
    public SignupWeddingRs signupWedding(@RequestBody SignupWeddingRq request) {
        String responseMessage = HWStringUtils.format("I WILL CALL YOU, {}, BECAUSE I KNOW YOUR PHONE IS {} AND EMAIL {} AND YOU SAID \"{}\"", request.getName(), request.getPhone(), request.getEmail(), request.getComments());
        log.info("received request:\n{}", request.getEmail());
        return SignupWeddingRs.builder()
                              .message(responseMessage)
                              .build();
    }

}
