package com.the3dsandwich.haileyandweiweibackend.service.data;

import com.the3dsandwich.haileyandweiweibackend.service.data.entity.GuestEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/*
 * Copyright (c) 2024. the3dsandwich (Shann Wei Yeh)
 */
@Repository
public interface GuestRepository extends JpaRepository<GuestEntity, Long>, JpaSpecificationExecutor<GuestEntity> {

    boolean existsByEmail(String email);

}
