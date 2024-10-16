package com.the3dsandwich.haileyandweiweibackend.service.data.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

/*
 * Copyright (c) 2024. the3dsandwich (Shann Wei Yeh)
 */
@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "GUEST", schema = "PUBLIC")
public class GuestEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "nickname")
    private String nickname;

    @Column(name = "email")
    private String email;

    @Column(name = "comments")
    private String comments;

    @Column(name = "friend_of", nullable = false)
    private String friendOf;

    @Column(name = "transportation")
    private String transportation;

    @Column(name = "isPhysicalInvitation")
    private Boolean isPhysicalInvitation;

    @Column(name = "physicalAddress")
    private String physicalAddress;

    @Column(name = "tags")
    private String tags;

    @Column(name = "created_at")
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    @UpdateTimestamp
    private LocalDateTime updatedAt;

}
