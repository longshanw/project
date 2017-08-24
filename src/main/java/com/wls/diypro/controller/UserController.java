package com.wls.diypro.controller;

/**
 * Created by wls on 2017/8/24.
 */
import java.util.List;

import com.wls.diypro.mapper.jpa.IUserRepository;
import com.wls.diypro.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class UserController {

    @Autowired
    private IUserRepository iUserRepository;

    @RequestMapping("/getUser")
    @Cacheable(value="user-key")
    public User getUser() {
        User user=iUserRepository.findByUserName("aa");
        System.out.println("若下面没出现“无缓存的时候调用”字样且能打印出数据表示测试成功");
        return user;
    }

    @RequestMapping("/getUsers")
    @Cacheable(value="key-Users")
    public List<User> getUsers() {
        List<User> users=iUserRepository.findAll();
        System.out.println("若下面没出现“无缓存的时候调用”字样且能打印出数据表示测试成功");
        return users;
    }
}