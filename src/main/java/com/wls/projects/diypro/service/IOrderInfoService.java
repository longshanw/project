package com.wls.projects.diypro.service;

import com.wls.integrateplugs.jpa.primary.model.OrderInfo;

import java.util.List;

/**
 * Created by wls on 2017/8/5.
 */
public interface IOrderInfoService {

    /**
     * 获取订单集合
     * @return
     * @throws Exception
     */
     public List<OrderInfo> getOrders() throws Exception;

     public OrderInfo addOrder(OrderInfo orderInfo) throws Exception;

     public OrderInfo updateOrder(Integer id,OrderInfo orderInfo) throws Exception;

     public void deleteOrder(Integer id) throws Exception;

     void addOrderTrans() throws Exception;

     public void getStatus(Integer id) throws Exception;

     OrderInfo findOne(Integer id) throws Exception;
}
