package com.wls.shopmall.service.impl;

import com.wls.shopmall.enums.RespEnum;
import com.wls.shopmall.exception.MyException;
import com.wls.shopmall.mapper.jpa.IOrderInfoMapper;
import com.wls.shopmall.model.OrderInfo;
import com.wls.shopmall.service.IOrderInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by wls on 2017/8/5.
 */
@Service
public class OrderInfoServiceImpl implements IOrderInfoService {

    Logger logger = LoggerFactory.getLogger(OrderInfoServiceImpl.class);

    @Autowired
    private IOrderInfoMapper iOrderInfoMapper;

    @Override
    public List<OrderInfo> getOrders() throws Exception {
        return iOrderInfoMapper.findAll();
    }

    @Override
    public OrderInfo addOrder(OrderInfo orderInfo) throws Exception {
        return iOrderInfoMapper.save(orderInfo);
    }

    @Override
    public OrderInfo updateOrder(Integer id,OrderInfo orderInfo) throws Exception {
        orderInfo.setId(id);
        return iOrderInfoMapper.save(orderInfo);
    }

    @Override
    public void deleteOrder(Integer id) throws Exception {
        //iOrderInfoMapper.delete(2);
        iOrderInfoMapper.delete(id);
    }

    @Transactional
    @Override
    public void addOrderTrans() throws Exception {
        OrderInfo orderInfo1 = new OrderInfo();
        orderInfo1.setOrderNumber("1001");
        iOrderInfoMapper.save(orderInfo1);
        OrderInfo orderInfo2 = new OrderInfo();
        orderInfo1.setOrderNumber("100200000");
        iOrderInfoMapper.save(orderInfo2);
    }


    @Override
    public void getStatus(Integer id) throws Exception {
        OrderInfo orderInfo = iOrderInfoMapper.findOne(id);
        String orderStatus = orderInfo.getOrderStatus();
        switch (orderStatus){
            case "1":
                throw new MyException(RespEnum.JD_CODE);
            case "2":
                throw new MyException(RespEnum.FH_CODE);
            case "3":
                throw new MyException(RespEnum.WD_CODE);
            default:
                throw new MyException(RespEnum.UNKNOW_CODE);
        }
    }

    @Override
    public OrderInfo findOne(Integer id) throws Exception {
        return iOrderInfoMapper.findOne(id);
    }
}
