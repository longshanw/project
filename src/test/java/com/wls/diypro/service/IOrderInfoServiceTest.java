package com.wls.diypro.service;

import com.wls.diypro.model.OrderInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class IOrderInfoServiceTest {
    @Autowired
    private IOrderInfoService iOrderInfoService;

    @Test
    public void addOrder() throws Exception {
        OrderInfo orderInfo = new OrderInfo();
        orderInfo.setAddressDetail("广平大街");
        orderInfo.setArea("大兴区");
        orderInfo.setCity("北京市");
        orderInfo.setOrderNumber("10000001");
        orderInfo.setOrderStatus("2");
        orderInfo.setOrderTime(new Date());
        orderInfo.setProvince("北京");
        orderInfo.setReceiver("王龙山");
        orderInfo.setStreet("ces");
        iOrderInfoService.addOrder(orderInfo);
    }

   /* @Test
    public void deleteOrder() throws Exception {
    }

    @Test
    public void addOrderTrans() throws Exception {
    }

    @Test
    public void findOne() throws Exception {
    }*/

}