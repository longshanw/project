package com.wls.integrateplugs.exception.controller;

import com.wls.integrateplugs.exception.MyException;
import com.wls.integrateplugs.mvc.enums.RespEnum;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/exception")
public class ExceptionController {
    @RequestMapping("/html")
    public String hello() throws Exception {
        throw new Exception("发生错误");
    }

    @RequestMapping("/json")
    public String json() throws MyException {
        throw new MyException(RespEnum.UNKNOW_CODE);
    }
}
