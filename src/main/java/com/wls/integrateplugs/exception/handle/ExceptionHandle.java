package com.wls.integrateplugs.exception.handle;

import com.wls.integrateplugs.exception.MyException;
import com.wls.integrateplugs.exception.dto.ErrorInfo;
import com.wls.integrateplugs.mvc.util.http.RespUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by wls on 2017/8/7.
 */
@ControllerAdvice
public class ExceptionHandle {

    Logger logger = LoggerFactory.getLogger(ExceptionHandle.class);

    /*@ExceptionHandler(value = Exception.class)
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
    }*/


    @ExceptionHandler(value = Exception.class)
    public ModelAndView defaultErrorHandler(HttpServletRequest req, Exception e) throws Exception {
        ModelAndView mav = new ModelAndView();
        mav.addObject("exception", e);
        mav.addObject("url", req.getRequestURL());
        mav.setViewName("error");
        return mav;
    }

    @ExceptionHandler(value = MyException.class)
    @ResponseBody
    public ErrorInfo<String> jsonErrorHandler(HttpServletRequest req, MyException e) throws Exception {
        ErrorInfo<String> r = new ErrorInfo<>();
        r.setMessage(e.getMessage());
        r.setCode(ErrorInfo.ERROR);
        r.setData("Some Data");
        r.setUrl(req.getRequestURL().toString());
        return r;
    }

}
