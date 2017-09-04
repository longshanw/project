package com.wls.integrateplugs.mybatis.mapper;

import com.wls.integrateplugs.jpa.primary.model.OrderInfo;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderInfoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(OrderInfo record);

    int insertSelective(OrderInfo record);

    OrderInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(OrderInfo record);

    int updateByPrimaryKey(OrderInfo record);
}