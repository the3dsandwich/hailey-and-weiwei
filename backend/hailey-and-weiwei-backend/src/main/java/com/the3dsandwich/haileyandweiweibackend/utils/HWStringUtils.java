package com.the3dsandwich.haileyandweiweibackend.utils;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.helpers.MessageFormatter;
import org.springframework.util.CollectionUtils;

import java.util.Arrays;
import java.util.List;

/*
 * Copyright (c) 2024. the3dsandwich (Shann Wei Yeh)
 */
public class HWStringUtils extends StringUtils {

    public static String format(String format, Object... params) {
        return MessageFormatter.arrayFormat(format, params)
                               .getMessage();
    }

    public static List<String> commaSplit(String commaSeparatedString) {
        return Arrays.stream(StringUtils.split(commaSeparatedString, ","))
                     .toList();
    }

    public static String commaJoin(List<String> targetList) {
        if (CollectionUtils.isEmpty(targetList)) {
            return StringUtils.EMPTY;
        }
        return String.join(",", targetList);
    }

}
