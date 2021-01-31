package com.baidu.shop.service;

import com.baidu.shop.base.Result;
import com.baidu.shop.dto.SpuDTO;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @ClassName GoodsService
 * @Description: TODO
 * @Author yyq
 * @Date 2021/1/31
 * @Version V1.0
 **/
@Api(tags = "商品接口")
public interface GoodsService {
    @ApiOperation(value = "获取商品信息")
    @GetMapping(value = "goods/getSpuInfo")
    Result<PageInfo<SpuDTO>> getSpuInfo(SpuDTO spuDTO);
}
