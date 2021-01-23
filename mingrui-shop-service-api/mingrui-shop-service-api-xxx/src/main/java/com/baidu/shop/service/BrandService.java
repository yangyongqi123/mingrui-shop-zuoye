package com.baidu.shop.service;

import com.baidu.shop.base.Result;
import com.baidu.shop.dto.BrandDTO;
import com.baidu.shop.entity.BrandEntity;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @ClassName BrandService
 * @Description: TODO
 * @Author yyq
 * @Date 2021/1/23
 * @Version V1.0
 **/
public interface BrandService {
    @ApiOperation(value = "获取品牌信息")
    @GetMapping(value = "/brand/list")
    Result<PageInfo<BrandEntity>> getBrandInfo(BrandDTO brandDTO);
}
