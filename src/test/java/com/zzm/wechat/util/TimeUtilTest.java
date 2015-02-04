package com.zzm.wechat.util;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class TimeUtilTest {
    @Test
    public void should_get_current_seconds_correct() throws Exception {
        String seconds = Long.toString(TimeUtil.currentSeconds());
        assertThat(seconds.length(), is(10));
    }

}