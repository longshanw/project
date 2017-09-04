package com.wls.integrateplugs.jpa.primary.repository;

import com.wls.integrateplugs.jpa.primary.model.OrderInfo;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by wls on 2017/8/5.
 */
public interface IOrderInfoRepository extends JpaRepository<OrderInfo,Integer>{
}
