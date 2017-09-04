package com.wls.integrateplugs.mvc.enums;

/**
 * Created by wls on 2017/8/7.
 */
public enum RespEnum {
    UNKNOW_CODE(-1,"未知错误!"),
    JD_CODE(100,"接单！"),
    FH_CODE(101,"发货！"),
    WD_CODE(200,"完单！"),;

    private Integer code;

    private String msg;


    RespEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
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
}
