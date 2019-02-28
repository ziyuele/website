package com.ziyue.website.common;

import org.junit.Test;

public class CommonsTests {

    @Test
    public void testGetVersion() throws Exception {
       System.out.println(Commons.VERSION());
    }

    @Test
    public void testGetHost() {
        System.out.println(Commons.HOST_ADDRESS());
    }
}
