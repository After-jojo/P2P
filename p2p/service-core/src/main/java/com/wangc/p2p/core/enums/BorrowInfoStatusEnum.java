package com.wangc.p2p.core.enums;
import lombok.AllArgsConstructor;
import lombok.Getter;
/**
 * @author After拂晓
 * @date 2022年05月24日 17:29
 */

@AllArgsConstructor
@Getter
public enum BorrowInfoStatusEnum {

    NO_AUTH(0, "未认证"),
    CHECK_RUN(1, "审核中"),
    CHECK_OK(2, "审核通过"),
    CHECK_FAIL(-1, "审核不通过"),
    ;

    private Integer status;
    private String msg;

    public static String getMsgByStatus(int status) {
        BorrowInfoStatusEnum[] arrObj = BorrowInfoStatusEnum.values();
        for (BorrowInfoStatusEnum obj : arrObj) {
            if (status == obj.getStatus().intValue()) {
                return obj.getMsg();
            }
        }
        return "";
    }
}
