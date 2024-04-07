package com.the3dsandwich.haileyandweiweibackend.controller;

import com.the3dsandwich.haileyandweiweibackend.controller.bean.ListGuestsRs;
import com.the3dsandwich.haileyandweiweibackend.controller.bean.ListGuestsRsGuest;
import com.the3dsandwich.haileyandweiweibackend.service.GuestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/*
 * Copyright (c) 2024. the3dsandwich (Shann Wei Yeh)
 */
@Slf4j
@RestController
public class GuestController {

    @Autowired
    private GuestService guestService;

    @GetMapping("/listGuests")
    public ListGuestsRs listGuests() {
        return ListGuestsRs.builder()
                           .guestList(guestService.listGuests()
                                                  .getGuestList()
                                                  .stream()
                                                  .map(bo -> ListGuestsRsGuest.builder()
                                                                              .id(bo.getId())
                                                                              .name(bo.getName())
                                                                              .phone(bo.getPhone())
                                                                              .email(bo.getEmail())
                                                                              .comments(bo.getComments())
                                                                              .tags(bo.getTags())
                                                                              .build())
                                                  .toList())
                           .build();
    }

}
