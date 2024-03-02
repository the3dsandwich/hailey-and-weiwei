package com.the3dsandwich.haileyandweiweibackend.service;

import com.the3dsandwich.haileyandweiweibackend.service.bean.GuestBo;
import com.the3dsandwich.haileyandweiweibackend.service.bean.ListGuestsOutput;
import com.the3dsandwich.haileyandweiweibackend.service.data.GuestRepository;
import com.the3dsandwich.haileyandweiweibackend.service.data.entity.GuestEntity;
import com.the3dsandwich.haileyandweiweibackend.utils.HWJsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/*
 * Copyright (c) 2024. the3dsandwich (Shann Wei Yeh)
 */
@Slf4j
@Service
public class GuestService {

    @Autowired
    private GuestRepository guestRepository;

    public ListGuestsOutput listGuests() {
        return ListGuestsOutput.builder()
                               .guestList(guestRepository.findAll()
                                                         .stream()
                                                         .map(this::toBo)
                                                         .collect(Collectors.toList()))
                               .build();

    }

    public void addGuestEntry(GuestBo bo) {
        guestRepository.save(toEntity(bo));
    }

    public void pocGetEntities() {
        List<GuestEntity> guestEntityList = guestRepository.findAll();
        log.debug("guests:\n{}", HWJsonUtils.toPrettyJson(guestEntityList));
    }

    private GuestBo toBo(GuestEntity entity) {
        return GuestBo.builder()
                      .id(entity.getId())
                      .name(entity.getName())
                      .email(entity.getEmail())
                      .phone(entity.getPhone())
                      .comments(entity.getComments())
                      .createdAt(entity.getCreatedAt())
                      .updatedAt(entity.getUpdatedAt())
                      .build();
    }

    private GuestEntity toEntity(GuestBo bo) {
        return GuestEntity.builder()
                          .id(bo.getId())
                          .name(bo.getName())
                          .email(bo.getEmail())
                          .phone(bo.getPhone())
                          .comments(bo.getComments())
                          .createdAt(bo.getCreatedAt())
                          .updatedAt(bo.getUpdatedAt())
                          .build();
    }

}
