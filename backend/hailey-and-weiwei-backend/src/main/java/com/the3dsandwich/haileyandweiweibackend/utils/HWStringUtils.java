package com.the3dsandwich.haileyandweiweibackend.utils;/*
 * Copyright (c) 2024. the3dsandwich (Shann Wei Yeh)
 */

import org.apache.commons.lang3.StringUtils;
import org.slf4j.helpers.MessageFormatter;

/*
 * Copyright (c) 2024. the3dsandwich (Shann Wei Yeh)
 */
public class HWStringUtils extends StringUtils {

    public static String format(String format, Object... params) {
        return MessageFormatter.arrayFormat(format, params)
                               .getMessage();
    }

}
