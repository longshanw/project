package com.wls.diypro.mapper;

import com.wls.diypro.model.MPOrderInfo;
import org.springframework.stereotype.Repository;

@Repository
public interface MPOrderInfoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(MPOrderInfo record);

    int insertSelective(MPOrderInfo record);

    MPOrderInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(MPOrderInfo record);

    int updateByPrimaryKey(MPOrderInfo record);
}