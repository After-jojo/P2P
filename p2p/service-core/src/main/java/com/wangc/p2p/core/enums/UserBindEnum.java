package com.wangc.p2p.core.enums;

/**
 * @author After拂晓
 * @date 2022年05月24日 17:26
 */
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
//@ToString
public enum UserBindEnum {

    NO_BIND(0, "未绑定"),
    BIND_OK(1, "绑定成功"),
    BIND_FAIL(-1, "绑定失败"),
    ;

    private Integer status;
    private String msg;
}
