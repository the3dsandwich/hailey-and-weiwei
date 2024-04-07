package com.the3dsandwich.haileyandweiweibackend.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import lombok.extern.slf4j.Slf4j;

/*
 * Copyright (c) 2024. the3dsandwich (Shann Wei Yeh)
 */
@Slf4j
public class HWJsonUtils {

    public static String toPrettyJson(Object target) {

        try {
            ObjectWriter ow = new ObjectMapper().writer()
                                                .withDefaultPrettyPrinter();
            return ow.writeValueAsString(target);
        } catch (JsonProcessingException exception) {
            log.error("json pretty print error: {}", exception.getMessage());
            exception.printStackTrace();
        }

        return HWStringUtils.EMPTY;
    }

}
