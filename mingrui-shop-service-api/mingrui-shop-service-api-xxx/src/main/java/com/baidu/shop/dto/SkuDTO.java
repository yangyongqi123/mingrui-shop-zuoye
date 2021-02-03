package com.baidu.shop.dto;

import com.baidu.shop.validate.group.MingruiOperation;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;


/**
 * @ClassName SkuDTO
 * @Description: TODO
 * @Author yyq
 * @Date 2021/2/3
 * @Version V1.0
 **/
@ApiModel(value = "sku数据传输类")
@Data
public class SkuDTO {
    @ApiModelProperty(value = "sku主键",example = "1")
    @NotNull(message = "sku主键不能为空", groups = {MingruiOperation.Update.class})
    private Long id;

    @ApiModelProperty(value = "商品Id",example = "1")
    @NotNull(message = "商品Id不能为空", groups = {MingruiOperation.Update.class})
    private Integer spuId;

    @ApiModelProperty(value = "商品标题")
    @NotEmpty(message = "商品标题不能为空", groups = {MingruiOperation.Update.class,MingruiOperation.Add.class})
    private String title;

    @ApiModelProperty(value = "商品的图片")
    private String images;

    @ApiModelProperty(value = "销售价格",example = "1")
    private Integer price;

    @ApiModelProperty(value = "特有规格属性在spu属性模板中的对应下标组合")
    private String indexes;

    @ApiModelProperty(value = "sku的特有规格参数键值对")
    private String ownSpec;

    @ApiModelProperty(value = "是否有效",example = "1")
    private Boolean enable;

    @ApiModelProperty(value = "添加时间")
    private Date createTime;

    @ApiModelProperty(value = "最后修改时间")
    private Date lastUpdateTime;

    //方便接受页面传递过来的参数
    @ApiModelProperty(value = "库存")
    private Integer stock;
}
