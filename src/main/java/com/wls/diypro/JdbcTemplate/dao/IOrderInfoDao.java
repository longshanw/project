package com.wls.diypro.JdbcTemplate.dao;

import com.wls.diypro.model.OrderInfo;
import org.springframework.stereotype.Repository;

@Repository
public interface IOrderInfoDao {
    int deleteByPrimaryKey(Long id);

    OrderInfo insert(OrderInfo record) throws Exception;

    int insertSelective(OrderInfo record);

    OrderInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(OrderInfo record);

    int updateByPrimaryKey(OrderInfo record);
}