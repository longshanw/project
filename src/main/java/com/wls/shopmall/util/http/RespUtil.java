package com.wls.shopmall.util.http;

/**
 * Created by wls on 2017/8/7.
 */
public class RespUtil<T> {
    private Integer code;

    private String msg;

    private T data;

    public RespUtil() {
    }

    public RespUtil(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public static RespUtil success(Object object){
        RespUtil resp = new RespUtil();
        resp.setCode(1);
        resp.setMsg("成功！");
        resp.setData(object);
        return resp;
    }

    public static RespUtil success(){
        return success(null);
    }

    public static RespUtil error(Integer code,String message){
        RespUtil resp = new RespUtil();
        resp.setCode(code);
        resp.setMsg(message);
        return resp;
    }

    @Override
    public String toString() {
        return "RespUtil{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
