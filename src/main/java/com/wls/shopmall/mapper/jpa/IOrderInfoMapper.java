package com.wls.shopmall.mapper.jpa;

import com.wls.shopmall.model.OrderInfo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by wls on 2017/8/5.
 */
public interface IOrderInfoMapper extends JpaRepository<OrderInfo,Integer>{
}
