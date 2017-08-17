package com.wls.diypro.handle;

import com.wls.diypro.exception.MyException;
import com.wls.diypro.util.http.RespUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by wls on 2017/8/7.
 */
@ControllerAdvice
public class ExceptionHandle {

    Logger logger = LoggerFactory.getLogger(ExceptionHandle.class);

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public RespUtil handle(Exception e){
//        synchronized ()
        if(e instanceof MyException){
            MyException myException = (MyException) e;
            return RespUtil.error(myException.getCode(),myException.getMessage());
        }else{
            logger.info("【异常信息】：{}",e);
            return RespUtil.error(-1,"未知错误！");
        }

    }
}
