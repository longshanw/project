package com.wls.diypro.mapper.jpa;

import com.wls.diypro.model.OrderInfo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by wls on 2017/8/5.
 */
public interface IOrderInfoMapper extends JpaRepository<OrderInfo,Integer>{
}
