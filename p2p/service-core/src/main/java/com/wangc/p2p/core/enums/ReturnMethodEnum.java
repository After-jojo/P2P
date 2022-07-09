package com.wangc.p2p.core.enums;

/**
 * @author After拂晓
 * @date 2022年05月24日 17:32
 */
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ReturnMethodEnum {

    ONE(1, "等额本息"),
    TWO(2, "等额本金"),
    THREE(3, "每月还息一次还本"),
    FOUR(4, "一次还本还息"),
    ;

    private Integer method;
    private String msg;
}