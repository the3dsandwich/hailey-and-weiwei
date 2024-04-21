package com.the3dsandwich.haileyandweiweibackend.service;

import com.the3dsandwich.haileyandweiweibackend.service.bean.GuestBo;
import com.the3dsandwich.haileyandweiweibackend.service.bean.GuestTransportationEnum;
import com.the3dsandwich.haileyandweiweibackend.service.bean.ListGuestsOutput;
import com.the3dsandwich.haileyandweiweibackend.service.data.GuestRepository;
import com.the3dsandwich.haileyandweiweibackend.service.data.entity.GuestEntity;
import com.the3dsandwich.haileyandweiweibackend.utils.HWJsonUtils;
import com.the3dsandwich.haileyandweiweibackend.utils.HWStringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

/*
 * Copyright (c) 2024. the3dsandwich (Shann Wei Yeh)
 */
@Slf4j
@Service
public class GuestService {

    public static final String VEGETARIAN = "Vegan";

    @Autowired
    private GuestRepository guestRepository;

    public ListGuestsOutput listGuests() {
        List<GuestEntity> guestEntityList = guestRepository.findAll(Sort.by(Sort.Order.asc("name")));
        return ListGuestsOutput.builder()
                               .guestList(guestEntityList.stream()
                                                         .map(this::toBo)
                                                         .collect(Collectors.toList()))
                               .build();

    }

    public void addGuest(GuestBo bo) {
        guestRepository.save(toEntity(bo));
    }

    public boolean existsGuest(Long id) {
        return guestRepository.existsById(id);
    }

    public Optional<GuestBo> findGuest(Long id) {
        return guestRepository.findById(id)
                              .map(this::toBo);
    }

    public boolean existsGuestByEmail(String email) {
        return guestRepository.existsByEmail(HWStringUtils.trimToEmpty(email));
    }

    public GuestBo updateGuest(GuestBo bo) {
        if (!existsGuest(bo.getId())) {
            log.debug("update target with id {} not exist", bo.getId());
            return null;
        }
        return toBo(guestRepository.save(toEntity(bo)));
    }

    public void pocGetEntities() {
        List<GuestEntity> guestEntityList = guestRepository.findAll();
        log.info("guests:\n{}", HWJsonUtils.toPrettyJson(guestEntityList));
    }

    private GuestBo toBo(GuestEntity entity) {
        GuestBo.GuestBoBuilder builder = GuestBo.builder()
                                                .id(entity.getId())
                                                .name(entity.getName())
                                                .nickname(entity.getNickname())
                                                .email(entity.getEmail())
                                                .phone(entity.getPhone())
                                                .comments(entity.getComments())
                                                .friendOf(entity.getFriendOf())
                                                .transportation(GuestTransportationEnum.of(entity.getTransportation()))
                                                .isPhysicalInvitation(entity.getIsPhysicalInvitation())
                                                .physicalAddress(entity.getPhysicalAddress());

        List<String> tags = HWStringUtils.commaSplit(entity.getTags());
        builder.tags(tags);
        builder.vegetarian(CollectionUtils.contains(tags.iterator(), VEGETARIAN));

        return builder.build();
    }

    private GuestEntity toEntity(GuestBo bo) {
        List<String> tags = bo.getTags();
        if (bo.isVegetarian()) {
            tags.add(VEGETARIAN);
        }

        return GuestEntity.builder()
                          .id(bo.getId())
                          .name(HWStringUtils.trimToEmpty(bo.getName()))
                          .nickname(HWStringUtils.trimToEmpty(bo.getNickname()))
                          .email(HWStringUtils.trimToEmpty(bo.getEmail()))
                          .phone(HWStringUtils.trimToEmpty(bo.getPhone()))
                          .comments(HWStringUtils.trimToEmpty(bo.getComments()))
                          .friendOf(bo.getFriendOf())
                          .transportation(Objects.requireNonNullElse(bo.getTransportation(), GuestTransportationEnum.NONE)
                                                 .getCode())
                          .isPhysicalInvitation(Boolean.TRUE.equals(bo.getIsPhysicalInvitation()))
                          .physicalAddress(HWStringUtils.trimToEmpty(bo.getPhysicalAddress()))
                          .tags(HWStringUtils.commaJoin(tags))
                          .build();
    }

}
