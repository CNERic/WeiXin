package com.chn.common;

import org.junit.Assert;
import org.junit.Test;

public class CastorsTest {

    @Test
    public void test() {
        
        Assert.assertEquals(Integer.valueOf(1), Castors.cast(int.class, "1"));
        Assert.assertEquals(Integer.valueOf(1), Castors.cast(Integer.class, "1"));
        Assert.assertEquals(Integer.valueOf(1), Castors.cast(Integer.class, 1));
        Assert.assertEquals(Integer.valueOf(1), Castors.cast(Integer.class, 1L));
        Assert.assertEquals(Integer.valueOf(1), Castors.cast(Integer.class, Long.valueOf(1)));
        
        Assert.assertArrayEquals(new String[]{"2", "1"}, Castors.cast(String[].class, "2, 1"));
        Assert.assertArrayEquals(new String[]{"2", "1"}, Castors.cast(String[].class, " 2, 1 "));
        Assert.assertArrayEquals(new int[]{2, 1}, Castors.cast(int[].class, " 2, 1 "));
        
    }

}
