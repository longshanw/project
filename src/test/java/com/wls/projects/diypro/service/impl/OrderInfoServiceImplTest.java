package com.wls.projects.diypro.service.impl;

import com.wls.integrateplugs.jpa.primary.model.OrderInfo;
import com.wls.integrateplugs.jpa.primary.repository.IOrderInfoRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class OrderInfoServiceImplTest {
    @Autowired
    private IOrderInfoRepository iOrderInfoRepository;
    @Test
    public void getOrders() throws Exception {
        List<OrderInfo> list = iOrderInfoRepository.findAll();
        System.out.println(list);
    }

}