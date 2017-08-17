package com.wls.shopmall.controller;

import com.alibaba.fastjson.JSONObject;
import com.wls.shopmall.model.OrderInfo;
import com.wls.shopmall.service.IOrderInfoService;
import com.wls.shopmall.util.http.RespUtil;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by wls on 2017/8/4.
 */
@RestController
@RequestMapping(value = "/order")
@MapperScan(value = "com.wls.shopmall.mapper")
public class OrderInfoController {

    private final static Logger logger = LoggerFactory.getLogger(OrderInfoController.class);

    @Autowired
    private OrderInfo orderInfo;

    @Autowired
    private IOrderInfoService iOrderInfoService;

    @GetMapping(value = "/page/one/{id}")
    public ModelAndView showOrder(@PathVariable(value = "id") Integer id,Model model) throws Exception {
        RespUtil<Object> resp = RespUtil.success(iOrderInfoService.findOne(id));
        model.addAttribute("name", JSONObject.toJSONString(resp));
        logger.info(resp.toString());
        ModelAndView mv = new ModelAndView("index");
        return mv;
    }

    @GetMapping(value = "/{orderNumber}")
    public String getOrderInfo(@PathVariable("orderNumber") String orderNumber) {
        return "Hello " + orderNumber;
    }

    @GetMapping(value = "/list")
    public List<OrderInfo> getOrderList() throws Exception {
        return iOrderInfoService.getOrders();
    }

    @GetMapping(value = "/one/{id}")
    public RespUtil<Object> findOne(@PathVariable(value = "id") Integer id) throws Exception{
        return RespUtil.success(iOrderInfoService.findOne(id));
    }



    @PostMapping(value = "/add",produces = "application/json")
    public RespUtil<Object> addOrder(@Valid @RequestBody OrderInfo orderInfo, BindingResult result) throws Exception {
        if(result.hasErrors()){
            return null;
//            return RespUtil.error(0,result.getFieldError().getDefaultMessage());
        }
        return RespUtil.success(iOrderInfoService.addOrder(orderInfo));
    }

    /**
     * 事务控制测试
     * @throws Exception
     */
    @PostMapping(value = "/addTrans")
    public void addOrderTrans() throws Exception {
         iOrderInfoService.addOrderTrans();
    }

    @PutMapping(value = "/update/{id}",produces = "application/json")
    public OrderInfo updateOrder(@PathVariable(value = "id") Integer id,@RequestBody OrderInfo orderInfo) throws Exception {
        return iOrderInfoService.updateOrder(id,orderInfo);
    }

    @DeleteMapping(value = "/delete/{id}")
    public void deleteOrder(@PathVariable("id") Integer id) throws Exception{
        iOrderInfoService.deleteOrder(id);
    }

    /**
     * AOP(单个查询)  统一异常处理/方法前后日志记录
     * @param id
     * @throws Exception
     */
    @GetMapping(value = "/status/{id}")
    public void getStatus(@PathVariable("id") Integer id) throws Exception{
        iOrderInfoService.getStatus(id);
    }
}
