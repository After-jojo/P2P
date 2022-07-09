package com.wangc.p2p.core.vo;

import com.wangc.p2p.core.enums.TransTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * @author After拂晓
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransFlowBO {

    private String agentBillNo;

    private String bindCode;

    private BigDecimal amount;

    private TransTypeEnum transTypeEnum;

    private String memo;
}
