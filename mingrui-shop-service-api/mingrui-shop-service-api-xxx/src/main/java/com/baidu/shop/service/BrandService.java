package com.baidu.shop.service;

import com.alibaba.fastjson.JSONObject;
import com.baidu.shop.base.Result;
import com.baidu.shop.dto.BrandDTO;
import com.baidu.shop.entity.BrandEntity;
import com.baidu.shop.validate.group.MingruiOperation;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @ApiOperation(value = "品牌新增")
    @PostMapping(value = "/brand/save")
    Result<JSONObject> saveBrandInfo(@RequestBody BrandDTO brandDTO);

    @ApiOperation(value = "修改品牌信息")
    @PutMapping(value = "/brand/save")
    Result<JSONObject>  updateBrandInfo(@RequestBody  BrandDTO brandDTO);

    @ApiOperation(value = "删除品牌")
    @DeleteMapping(value = "/brand/delete")
    Result<JSONObject>  deleteBrandInfo(Integer id);

    @ApiOperation(value = "根据分类Id查询品牌信息")
    @GetMapping(value = "/brand/getBrandByCategoryId")
    Result<List<BrandEntity>>  getBrandByCategoryId(Integer cid);
}
