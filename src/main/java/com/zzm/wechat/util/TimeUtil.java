package com.zzm.wechat.util;

public class TimeUtil {

    public static long currentSeconds() {
        long timeMillis = System.currentTimeMillis();
        return timeMillis / 1000;
    }
}
