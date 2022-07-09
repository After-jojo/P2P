package com.wangc.p2p.common.exception;

import com.wangc.p2p.common.result.ResponseEnum;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author After拂晓
 * @date 2022年05月16日 16:52
 */
@Data
@NoArgsConstructor
public class BusinessException extends RuntimeException{
    private Integer code;
    private String message;

    public BusinessException(String message, Integer code, String message1) {
        super(message);
        this.code = code;
        this.message = message1;
    }

    public BusinessException(String message, String message1) {
        super(message);
        this.message = message1;
    }

    public BusinessException(String message, Integer code, Throwable cause) {
        super(cause);
        this.code = code;
        this.message = message;
    }

    public BusinessException(ResponseEnum responseEnum){
        this.code = responseEnum.getCode();
        this.message = responseEnum.getMessage();
    }

    public BusinessException(ResponseEnum responseEnum, Throwable cause){
        super(cause);
        this.code = responseEnum.getCode();
        this.message = responseEnum.getMessage();
    }

    public BusinessException(String message) {
        this.message = message;
    }
}
