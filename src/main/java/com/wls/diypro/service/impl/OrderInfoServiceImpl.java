package com.wls.diypro.service.impl;

import com.wls.diypro.enums.RespEnum;
import com.wls.diypro.exception.MyException;
import com.wls.diypro.JdbcTemplate.dao.IOrderInfoDao;
import com.wls.diypro.mapper.jpa.IOrderInfoMapper;
import com.wls.diypro.mapper.OrderInfoMapper;
import com.wls.diypro.model.OrderInfo;
import com.wls.diypro.service.IOrderInfoService;
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

    @Autowired
    private OrderInfoMapper orderInfoMapper;

    @Autowired
    private IOrderInfoDao iOrderInfoDao;

    @Override
    public List<OrderInfo> getOrders() throws Exception {
        return iOrderInfoMapper.findAll();
    }

    @Override
    public OrderInfo addOrder(OrderInfo orderInfo) throws Exception {
        return  iOrderInfoDao.insert(orderInfo);
//        return iOrderInfoMapper.save(orderInfo);
    }

    @Override
    public OrderInfo updateOrder(Integer id, OrderInfo orderInfo) throws Exception {
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
//        OrderInfo orderInfo = iOrderInfoMapper.findOne(id);
        OrderInfo orderInfo = orderInfoMapper.selectByPrimaryKey(new Long(id));
        String orderStatus = orderInfo.getOrderStatus();
        switch (orderStatus) {
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
//        MPOrderInfo orderInfo = mpOrderInfoMapper.selectByPrimaryKey(new Long(id));
        OrderInfo orderInfo = iOrderInfoDao.selectByPrimaryKey(new Long(id));
        return orderInfo;

    }
}
