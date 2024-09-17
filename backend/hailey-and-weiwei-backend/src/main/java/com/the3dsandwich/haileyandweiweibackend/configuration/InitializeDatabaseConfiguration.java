package com.the3dsandwich.haileyandweiweibackend.configuration;

import com.the3dsandwich.haileyandweiweibackend.service.bean.GuestTransportationEnum;
import com.the3dsandwich.haileyandweiweibackend.service.data.GuestRepository;
import com.the3dsandwich.haileyandweiweibackend.service.data.entity.GuestEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/*
 * Copyright (c) 2024. the3dsandwich (Shann Wei Yeh)
 */
@Slf4j
@Configuration
public class InitializeDatabaseConfiguration {

    @Bean
    CommandLineRunner initDatabase(GuestRepository guestRepository) {

        return args -> {
            // initialize database entries
            guestRepository.save(GuestEntity.builder()
                                            .name("Bride")
                                            .nickname("Hailey")
                                            .email("bride@gmail.com")
                                            .comments("新娘唷")
                                            .friendOf("Groom")
                                            .tags("bride")
                                            .transportation(GuestTransportationEnum.RIDE.getCode())
                                            .isPhysicalInvitation(true)
                                            .build());
            guestRepository.save(GuestEntity.builder()
                                            .name("Groom")
                                            .nickname("Wei-Wei")
                                            .email("groom@gmail.com")
                                            .comments("新郎唷")
                                            .friendOf("Bride")
                                            .transportation(GuestTransportationEnum.DRIVE.getCode())
                                            .isPhysicalInvitation(true)
                                            .tags("groom")
                                            .build());

        };
    }

}
