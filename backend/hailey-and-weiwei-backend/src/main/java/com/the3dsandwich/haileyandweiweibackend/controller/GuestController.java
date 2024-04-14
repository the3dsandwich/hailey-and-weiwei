package com.the3dsandwich.haileyandweiweibackend.controller;

import com.the3dsandwich.haileyandweiweibackend.controller.bean.ListGuestsRs;
import com.the3dsandwich.haileyandweiweibackend.controller.bean.ListGuestsRsGuest;
import com.the3dsandwich.haileyandweiweibackend.controller.bean.UpdateGuestRq;
import com.the3dsandwich.haileyandweiweibackend.controller.bean.UpdateGuestRs;
import com.the3dsandwich.haileyandweiweibackend.service.GuestService;
import com.the3dsandwich.haileyandweiweibackend.service.bean.GuestBo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
        List<GuestBo> guestList = guestService.listGuests()
                                              .getGuestList();
        return ListGuestsRs.builder()
                           .guestList(guestList.stream()
                                               .map(this::toListGuestsRsGuest)
                                               .toList())
                           .build();
    }

    private ListGuestsRsGuest toListGuestsRsGuest(GuestBo bo) {
        return ListGuestsRsGuest.builder()
                                .id(bo.getId())
                                .name(bo.getName())
                                .phone(bo.getPhone())
                                .email(bo.getEmail())
                                .comments(bo.getComments())
                                .tags(bo.getTags())
                                .build();
    }

    @PostMapping("/updateGuest")
    public UpdateGuestRs updateGuest(@RequestBody UpdateGuestRq request) {
        if (!guestService.existsGuest(request.getId())) {
            log.debug("cannot find guest by id = {}", request.getId());
            return null;
        }
        GuestBo updated = guestService.updateGuest(toGuestBo(request));
        return toUpdateGuestRs(updated);
    }

    private UpdateGuestRs toUpdateGuestRs(GuestBo bo) {
        return UpdateGuestRs.builder()
                            .id(bo.getId())
                            .name(bo.getName())
                            .phone(bo.getPhone())
                            .email(bo.getEmail())
                            .comments(bo.getComments())
                            .tags(bo.getTags())
                            .build();
    }

    private GuestBo toGuestBo(UpdateGuestRq request) {
        return GuestBo.builder()
                      .id(request.getId())
                      .id(request.getId())
                      .name(request.getName())
                      .phone(request.getPhone())
                      .email(request.getEmail())
                      .comments(request.getComments())
                      .tags(request.getTags())
                      .build();
    }

}
