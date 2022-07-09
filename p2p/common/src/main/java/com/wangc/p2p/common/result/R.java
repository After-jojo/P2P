package com.wangc.p2p.common.result;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * @author After拂晓
 * @date 2022年05月16日 11:07
 */
@Data
public class R {
    private Integer code;
    private String massage;
    private Map<String, Object> data = new HashMap<>();
    private R(){

    }
    public static R ok(){
        R r = new R();
        r.setCode(ResponseEnum.SUCCESS.getCode());
        r.setMassage(ResponseEnum.SUCCESS.getMessage());
        return r;
    }

    public static R error(){
        R r = new R();
        r.setCode(ResponseEnum.ERROR.getCode());
        r.setMassage(ResponseEnum.ERROR.getMessage());
        return r;
    }

    public static R setResult(ResponseEnum responseEnum){
        R r = new R();
        r.setCode(responseEnum.getCode());
        r.setMassage(responseEnum.getMessage());
        return r;
    }

    public R data(String massage, Object data){
        this.data.put(massage, data);
        return this;
    }

    public R data(Map<String, Object> map){
        this.setData(map);
        return this;
    }

    public R message(String massage){
        this.setMassage(massage);
        return this;
    }

    public R code(Integer code){
        this.setCode(code);
        return this;
    }
}
