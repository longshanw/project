package com.wls.integrateplugs.mvc.service.impl;

import com.wls.integrateplugs.mvc.enums.RespEnum;
import com.wls.integrateplugs.exception.MyException;
import com.wls.integrateplugs.JdbcTemplate.dao.IOrderInfoDao;
import com.wls.integrateplugs.jpa.primary.repository.IOrderInfoRepository;
import com.wls.integrateplugs.mybatis.mapper.OrderInfoMapper;
import com.wls.integrateplugs.jpa.primary.model.OrderInfo;
import com.wls.integrateplugs.mvc.service.IOrderInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
    private IOrderInfoRepository iOrderInfoRepository;

    @Autowired
    private OrderInfoMapper orderInfoMapper;

    @Qualifier("orderInfoDaoImpl")
    @Autowired
    private IOrderInfoDao iOrderInfoDao;

    @Override
    public List<OrderInfo> getOrders() throws Exception {
        return iOrderInfoRepository.findAll();
    }

    @Override
    public OrderInfo addOrder(OrderInfo orderInfo) throws Exception {
        return  iOrderInfoDao.insert(orderInfo);
//        return iOrderInfoMapper.save(orderInfo);
    }

    @Override
    public OrderInfo updateOrder(Integer id, OrderInfo orderInfo) throws Exception {
        orderInfo.setId(id);
        return iOrderInfoRepository.save(orderInfo);
    }

    @Override
    public void deleteOrder(Integer id) throws Exception {
        //iOrderInfoMapper.delete(2);
        iOrderInfoRepository.delete(id);
    }

    @Transactional
    @Override
    public void addOrderTrans() throws Exception {
        OrderInfo orderInfo1 = new OrderInfo();
        orderInfo1.setOrderNumber("1001");
        iOrderInfoRepository.save(orderInfo1);
        OrderInfo orderInfo2 = new OrderInfo();
        orderInfo1.setOrderNumber("100200000");
        iOrderInfoRepository.save(orderInfo2);
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
