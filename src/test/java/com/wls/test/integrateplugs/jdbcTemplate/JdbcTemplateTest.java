package com.wls.test.integrateplugs.jdbcTemplate;

import com.wls.integrateplugs.jpa.primary.model.OrderInfo;
import com.wls.integrateplugs.mvc.service.IOrderInfoService;
import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class JdbcTemplateTest {

    @Autowired
    @Qualifier("primaryJdbcTemplate")
    protected JdbcTemplate primaryJdbcTemplate;

    @Autowired
    @Qualifier("secondaryJdbcTemplate")
    protected JdbcTemplate secondaryJdbcTemplate;

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



    @Before
    public void setUp() {
        primaryJdbcTemplate.update("DELETE  FROM  order_info ");
        secondaryJdbcTemplate.update("DELETE  FROM  order_info ");
    }

    @Test
    public void test() throws Exception {

        // 往第一个数据源中插入两条数据
        primaryJdbcTemplate.update("insert into order_info(order_flag,order_number,order_status,street) values(?, ?, ?, ?)", "test", "10001", "S01","广平大街");
        primaryJdbcTemplate.update("insert into order_info(order_flag,order_number,order_status,street) values(?, ?, ?, ?)", "test", "10001", "S01","广平大街");

        // 往第二个数据源中插入一条数据，若插入的是第一个数据源，则会主键冲突报错
        secondaryJdbcTemplate.update("insert into order_info(order_flag,order_number,order_status,street) values(?, ?, ?, ?)", "test", "10003", "S02","广平大街");

        // 查一下第一个数据源中是否有两条数据，验证插入是否成功
        Assert.assertEquals("2", primaryJdbcTemplate.queryForObject("select count(1) from order_info", String.class));

        // 查一下第一个数据源中是否有两条数据，验证插入是否成功
        Assert.assertEquals("1", secondaryJdbcTemplate.queryForObject("select count(1) from order_info", String.class));

    }
}