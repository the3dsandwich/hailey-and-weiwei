package com.the3dsandwich.haileyandweiweibackend.service;

import com.the3dsandwich.haileyandweiweibackend.service.bean.GuestBo;
import com.the3dsandwich.haileyandweiweibackend.service.bean.GuestTransportationEnum;
import com.the3dsandwich.haileyandweiweibackend.service.bean.ListGuestsOutput;
import com.the3dsandwich.haileyandweiweibackend.service.data.GuestRepository;
import com.the3dsandwich.haileyandweiweibackend.service.data.entity.GuestEntity;
import com.the3dsandwich.haileyandweiweibackend.utils.HWJsonUtils;
import com.the3dsandwich.haileyandweiweibackend.utils.HWStringUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.BooleanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/*
 * Copyright (c) 2024. the3dsandwich (Shann Wei Yeh)
 */
@Slf4j
@Service
public class GuestService {

    public static final String VEGETARIAN = "Vegan";
    public static final String GUEST = "Guest";
    public static final String PHYSICAL = "Physical Invitation";

    @Autowired
    private GuestRepository guestRepository;

    public ListGuestsOutput listGuests() {
        List<GuestEntity> guestEntityList = guestRepository.findAll(Sort.by(Sort.Order.asc("friendOf"),
                                                                            Sort.Order.desc("id")));
        return ListGuestsOutput.builder()
                               .guestList(guestEntityList.stream()
                                                         .map(this::toBo)
                                                         .filter(guest -> guest != null && (CollectionUtils.isEmpty(
                                                                 guest.getTags()) || !guest.getTags()
                                                                                           .contains("hidden")))
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
        GuestBo.GuestBoBuilder<?, ?> builder = GuestBo.builder()
                                                      .id(entity.getId())
                                                      .name(entity.getName())
                                                      .nickname(entity.getNickname())
                                                      .email(entity.getEmail())
                                                      .comments(entity.getComments())
                                                      .friendOf(entity.getFriendOf())
                                                      .transportation(GuestTransportationEnum.of(entity.getTransportation()))
                                                      .isPhysicalInvitation(entity.getIsPhysicalInvitation())
                                                      .physicalAddress(entity.getPhysicalAddress());

        List<String> tags = HWStringUtils.commaSplit(entity.getTags());
        builder.tags(tags);
        builder.vegetarian(CollectionUtils.contains(tags.iterator(), VEGETARIAN));

        GuestBo bo = builder.build();

        if (log.isDebugEnabled()) {
            log.debug("toBo:\n{}", HWJsonUtils.toPrettyJson(bo));
        }

        return bo;
    }

    private GuestEntity toEntity(GuestBo bo) {
        List<String> tags = new ArrayList<>(CollectionUtils.isEmpty(bo.getTags())
                                                    ? new ArrayList<>()
                                                    : bo.getTags());
        if (bo.isVegetarian()) {
            tags.add(VEGETARIAN);
        }
        if (BooleanUtils.isTrue(bo.getIsPhysicalInvitation())) {
            tags.add(PHYSICAL);
        }

        GuestEntity entity = GuestEntity.builder()
                                        .id(bo.getId())
                                        .name(HWStringUtils.trimToEmpty(bo.getName()))
                                        .nickname(HWStringUtils.trimToEmpty(bo.getNickname()))
                                        .email(HWStringUtils.trimToEmpty(bo.getEmail()))
                                        .comments(HWStringUtils.trimToEmpty(bo.getComments()))
                                        .friendOf(bo.getFriendOf())
                                        .transportation(bo.getTransportation())
                                        .isPhysicalInvitation(BooleanUtils.isTrue(bo.getIsPhysicalInvitation()))
                                        .physicalAddress(HWStringUtils.trimToEmpty(bo.getPhysicalAddress()))
                                        .tags(HWStringUtils.commaJoin(tags))
                                        .build();

        if (log.isDebugEnabled()) {
            log.debug("toEntity:\n{}", HWJsonUtils.toPrettyJson(entity));
        }

        return entity;
    }

}
