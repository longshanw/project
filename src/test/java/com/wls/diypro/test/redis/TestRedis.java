package com.wls.diypro.test.redis;


import com.sun.glass.ui.Application;
import com.wls.diypro.model.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.concurrent.TimeUnit;

/**
 * Created by wls on 2017/8/7.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class TestRedis {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 缓存读取测试
     * @throws Exception
     */
    @Test
    public void test() throws Exception {
        stringRedisTemplate.opsForValue().set("aaa", "111");
        boolean exists = redisTemplate.hasKey("aaa");
        System.out.println(stringRedisTemplate.opsForValue().get("aaa"));
        Assert.assertEquals("111", stringRedisTemplate.opsForValue().get("aaa"));
        System.out.println(exists);
    }

    /**
     * 缓存有效期测试
     * @throws Exception
     */
    @Test
    public void testObj() throws Exception {
        User user=new User("aa@126.com", "aa", "aa123456", "aa","123");
        ValueOperations<String, User> operations=redisTemplate.opsForValue();
        operations.set("com.wls.diypro.model", user);
        operations.set("com.wls.diypro.model", user,1,TimeUnit.MINUTES);
        Thread.sleep(1000);
        //redisTemplate.delete("com.wls.diypro.model");
        boolean exists=redisTemplate.hasKey("com.wls.diypro.model");
        if(exists){
            System.out.println("exists is true");
        }else{
            System.out.println("exists is false");
        }
        System.out.println(operations.get("com.wls.diypro.model").getUserName());
         Assert.assertEquals("aa", operations.get("com.wls.diypro.model").getUserName());
    }

}
