package com.wls.shopmall.controller;

import com.alibaba.fastjson.JSONObject;
import com.wls.shopmall.model.OrderInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by wls on 2017/8/4.
 */
@RestController
public class PropTestController {

    @Value("${name}")
    private String name;
    @Value("${age}")
    private Integer age;
    @Value("${content}")
    private String content;


    @Autowired
    private OrderInfo orderInfo;

    @GetMapping(value = "/prop")
    public String getProp() {
        return "name=" + name+";age="+age+";content="+content;
    }

    @GetMapping(value = "/orderInfos")
    public String getOrderInfos() {
        return "model=" + JSONObject.toJSONString(orderInfo);
    }
}
