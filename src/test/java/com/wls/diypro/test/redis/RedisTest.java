package com.wls.diypro.test.redis;

import com.wls.diypro.model.MPOrderInfo;
import com.wls.diypro.service.IOrderInfoService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by wls on 2017/8/7.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisTest {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Test
    public void findOneTest() throws Exception {
        stringRedisTemplate.opsForValue().set("stringKey", "测试redis");
        System.out.println("获取redis值："+stringRedisTemplate.opsForValue().get("stringKey"));
        System.out.println("获取redis值："+stringRedisTemplate.opsForValue().get("key"));
    }
}
