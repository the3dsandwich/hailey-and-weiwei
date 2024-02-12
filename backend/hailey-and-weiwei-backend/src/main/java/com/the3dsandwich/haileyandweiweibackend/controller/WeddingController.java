package com.the3dsandwich.haileyandweiweibackend.controller;

import com.the3dsandwich.haileyandweiweibackend.controller.bean.SignupWeddingRq;
import com.the3dsandwich.haileyandweiweibackend.controller.bean.SignupWeddingRs;
import com.the3dsandwich.haileyandweiweibackend.utils.HWStringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
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

    @PostMapping("/signup")
    public SignupWeddingRs signupWedding(@RequestBody SignupWeddingRq request) {
        String responseMessage = HWStringUtils.format("You, {}, will be called, BECAUSE I KNOW YOUR PHONE IS {} AND EMAIL {} AND YOU SAID \"{}\"", request.getName(), request.getPhone(), request.getEmail(), request.getComments());
        return SignupWeddingRs.builder()
                              .message(responseMessage)
                              .build();
    }

    @GetMapping("testList")
    public List<SignupWeddingRs> testList() {
        List<SignupWeddingRs> response = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            response.add(SignupWeddingRs.builder()
                                        .message(HWStringUtils.format("{}", i))
                                        .build());
        }
        return response;
    }

}
