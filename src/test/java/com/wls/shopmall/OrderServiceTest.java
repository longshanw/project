package com.wls.shopmall;

import com.wls.shopmall.model.MPOrderInfo;
import com.wls.shopmall.model.OrderInfo;
import com.wls.shopmall.service.IOrderInfoService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Assert;

/**
 * Created by wls on 2017/8/7.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderServiceTest {

    @Autowired
    private IOrderInfoService iOrderInfoService;

    @Test
    public void findOneTest() throws Exception {
        MPOrderInfo orderInfo = iOrderInfoService.findOne(7);
        org.junit.Assert.assertEquals(1+"",orderInfo.getOrderStatus());

    }
}
