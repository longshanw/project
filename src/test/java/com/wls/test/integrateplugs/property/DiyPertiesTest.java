package com.wls.test.integrateplugs.property;

import com.wls.integrateplugs.property.DiyProperties;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashMap;
import java.util.Map;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class DiyPertiesTest {


    @Autowired
    private DiyProperties diyProperties;


    @Test
    public void getHello() throws Exception {
        System.out.println(diyProperties.getTitle());
        Assert.assertEquals(diyProperties.getTitle(), "纯洁的微笑");
        Assert.assertEquals(diyProperties.getDescription(), "分享生活和技术");
    }


    @Test
    public void testMap() throws Exception {
        Map<String, Long> orderMinTime = new HashMap<String, Long>();
        orderMinTime.put("abc",123L);
        System.out.print(orderMinTime.get("abc"));
    }
}