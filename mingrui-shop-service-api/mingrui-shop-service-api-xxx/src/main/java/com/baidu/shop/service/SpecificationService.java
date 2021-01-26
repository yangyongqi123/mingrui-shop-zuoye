package com.baidu.shop.service;

import com.baidu.shop.base.Result;
import com.baidu.shop.dto.SpecGroupDTO;
import com.baidu.shop.entity.SpecGroupEntity;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * @ClassName SpecificationService
 * @Description: TODO
 * @Author yyq
 * @Date 2021/1/26
 * @Version V1.0
 **/
public interface SpecificationService {
    @ApiOperation("通过条件查询规则数据")
    @GetMapping("/specgroup/getSpecGroupInfo")
    Result<List<SpecGroupEntity>> getSpecGroupInfo(SpecGroupDTO specGroupDTO);
}
