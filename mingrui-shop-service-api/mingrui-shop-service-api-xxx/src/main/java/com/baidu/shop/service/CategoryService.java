package com.baidu.shop.service;

import com.alibaba.fastjson.JSONObject;
import com.baidu.shop.base.Result;
import com.baidu.shop.entity.CategoryEntity;
import com.google.gson.JsonObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @ClassName CategoryService
 * @Description: TODO
 * @Author yyq
 * @Date 2021/1/19
 * @Version V1.0
 **/
@Api
public interface CategoryService {
    @ApiOperation(value = "分类查询")
    @GetMapping(value = "category/list")
    Result<List<CategoryEntity>> getCategoryByPid(Integer pid);

    @ApiOperation(value = "通过Id删除分类")
    @DeleteMapping(value = "/category/del")
    Result<JsonObject> delCateGoryById(Integer id);

    @ApiOperation(value = "分类修改")
    @PutMapping(value = "category/edit")
    Result<JsonObject> editCateGoryById(@RequestBody CategoryEntity categoryEntity);

    @ApiOperation(value = "分类新增")
    @PostMapping(value = "/category/save")
    Result<JsonObject> saveCateGoryById(@RequestBody CategoryEntity categoryEntity);
}
