package com.mo9.batman.common.util;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

/**
 * @author : xjding
 * @date :   2018-01-02 20:55
 */
public class DateUtils {

    /**
     * 获取到秒的时间戳
     * @param dateTime
     * @return
     */
    public static Long toTimestamp(LocalDateTime dateTime) {
        if (dateTime == null) {
            return null;
        }
        ZonedDateTime zonedDateTime = ZonedDateTime.of(dateTime, ZoneId.systemDefault());
        return Instant.from(zonedDateTime).getEpochSecond();
    }
    /**
     * 获取到两个时间之差是否大于该值
     * @param
     * @return
     */

    public static boolean timeExpire(LocalDateTime preTime, LocalDateTime afterTime, Long duration){

        boolean ret = false;
        long timeDiffer = toTimestamp(afterTime) - toTimestamp(preTime) ;
        if (timeDiffer >= duration) ret = true;

        return ret;
    }



}
