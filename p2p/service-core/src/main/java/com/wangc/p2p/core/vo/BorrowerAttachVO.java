package com.wangc.p2p.core.vo;

/**
 * @author After拂晓
 * @date 2022年05月28日 15:46
 */
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value="借款人附件资料")
public class BorrowerAttachVO {

    @ApiModelProperty(value = "图片类型（idCard1：身份证正面，idCard2：身份证反面，house：房产证，car：车）")
    private String imageType;

    @ApiModelProperty(value = "图片路径")
    private String imageUrl;
}