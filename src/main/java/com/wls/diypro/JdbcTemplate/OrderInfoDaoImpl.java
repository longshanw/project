package com.wls.diypro.JdbcTemplate;

import com.wls.diypro.JdbcTemplate.dao.IOrderInfoDao;
import com.wls.diypro.model.OrderInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OrderInfoDaoImpl implements IOrderInfoDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int deleteByPrimaryKey(Long id) {
        return jdbcTemplate.update("DELETE from order_info where id=?",id);
    }

    @Override
    public OrderInfo insert(OrderInfo record) throws Exception{
        int count  = jdbcTemplate.update("insert into order_info (address_detail, area, city, order_number, order_status, order_time, province, receiver, street) VALUES (?,?,?,?,?,?,?,?,?) "
                ,record.getAddressDetail(),record.getArea(),record.getCity(),record.getOrderNumber(),record.getOrderStatus(),record.getOrderTime(),record.getProvince(),record.getReceiver(),record.getStreet());
        record.setId(count);
        return record;
    }

    @Override
    public int insertSelective(OrderInfo record) {
        return jdbcTemplate.update("insert into order_info () VALUES (?,?,?,?,?,?,?,?,?) ",record);
    }

    @Override
    public OrderInfo selectByPrimaryKey(Long id) {
        List<OrderInfo> list = jdbcTemplate.query("select * from order_info where id=?",new Object[]{id},new BeanPropertyRowMapper(OrderInfo.class));
        if(list!=null && list.size()>0){
            OrderInfo OrderInfo = list.get(0);
            return OrderInfo;
        }else{
            return null;
        }
    }

    @Override
    public int updateByPrimaryKeySelective(OrderInfo record) {
        return jdbcTemplate.update("UPDATE  order_info SET order_status=? ,city=? WHERE id=?",
                record);
    }

    @Override
    public int updateByPrimaryKey(OrderInfo record) {
        return 0;
    }
}
