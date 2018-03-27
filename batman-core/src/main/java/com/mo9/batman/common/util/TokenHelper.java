package com.mo9.batman.common.util;

import java.util.UUID;

/**
 * @author : xjding
 * @date :   2017-12-25 11:46
 */
public class TokenHelper {

    public static String getGuid() {
        return UUID.randomUUID().toString().replace("-", "");
    }

}
