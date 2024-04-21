package com.the3dsandwich.haileyandweiweibackend.controller;

import com.the3dsandwich.haileyandweiweibackend.controller.bean.ListGuestsRs;
import com.the3dsandwich.haileyandweiweibackend.controller.bean.ListGuestsRsGuest;
import com.the3dsandwich.haileyandweiweibackend.controller.bean.UpdateGuestRq;
import com.the3dsandwich.haileyandweiweibackend.controller.bean.UpdateGuestRs;
import com.the3dsandwich.haileyandweiweibackend.service.GuestService;
import com.the3dsandwich.haileyandweiweibackend.service.bean.GuestBo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
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
        ListGuestsRsGuest rsGuest = new ListGuestsRsGuest();
        BeanUtils.copyProperties(bo, rsGuest); // TODO don't use BeanUtils
        return rsGuest;
    }

    @PostMapping("/updateGuest")
    public UpdateGuestRs updateGuest(@RequestBody UpdateGuestRq request) {
        if (!guestService.existsGuest(request.getId())) {
            log.debug("cannot find guest by id = {}", request.getId());
            return null;
        }
        GuestBo updated = guestService.updateGuest(request);
        return toUpdateGuestRs(updated);
    }

    private UpdateGuestRs toUpdateGuestRs(GuestBo bo) {
        UpdateGuestRs rs = new UpdateGuestRs();
        BeanUtils.copyProperties(bo, rs); // TODO don't use BeanUtils
        return rs;
    }

}
