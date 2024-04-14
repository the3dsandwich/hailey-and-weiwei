package com.the3dsandwich.haileyandweiweibackend.configuration;

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
                                            .email("bride@gmail.com")
                                            .phone("0987654321")
                                            .comments("新娘唷")
                                            .friendOf("Groom")
                                            .tags("bride")
                                            .build());
            guestRepository.save(GuestEntity.builder()
                                            .name("Groom")
                                            .email("groom@gmail.com")
                                            .phone("0912345678")
                                            .comments("新郎唷")
                                            .friendOf("Bride")
                                            .tags("groom")
                                            .build());

        };
    }

}
