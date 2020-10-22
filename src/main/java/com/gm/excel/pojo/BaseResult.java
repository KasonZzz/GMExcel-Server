package com.gm.excel.pojo;

import lombok.Data;
import lombok.ToString;

/**
 * @Author KasonZzz
 * @Description //TODO
 * @Date 10:21 2020/7/22
 */
@Data
@ToString
public class BaseResult<T> {
    /**
     * 响应中的数据
     */
    private T data;

    /**
     * 响应消息
     */
    private String msg;

    /**
     * 响应业务状态
     */
    private Integer code;

    public BaseResult() {
    }

    public BaseResult(String msg, Integer code) {
        this.msg = msg;
        this.code = code;
    }

    public BaseResult(T data) {
        this.data = data;
        msg = "success";
        code = 200;
    }

    public BaseResult(Integer code) {
        String msg;
        switch (code){
            case 200:
                msg = "success";
                break;
            case 400:
                msg="bad request";
                break;
            case 401:
                msg="401 Unauthorized";
                break;
            case 403:
                msg="403 Forbidden";
                break;
            case 404:
                msg = "404 Not Found";
                break;
            case 500:
                msg=" Internal Server Error ";
                break;
            default:
                msg = "fail";
                break;
        }
        this.msg = msg;
        this.code = code;
    }
}
