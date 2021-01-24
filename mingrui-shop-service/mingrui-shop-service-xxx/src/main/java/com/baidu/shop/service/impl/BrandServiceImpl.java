package com.baidu.shop.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baidu.shop.base.BaseApiService;
import com.baidu.shop.base.Result;
import com.baidu.shop.dto.BrandDTO;
import com.baidu.shop.entity.BrandEntity;
import com.baidu.shop.entity.CategoryBrandEntity;
import com.baidu.shop.mapper.BrandMapper;
import com.baidu.shop.mapper.CategoryBrandMapper;
import com.baidu.shop.service.BrandService;
import com.baidu.shop.utils.BaiduBeanUtils;
import com.baidu.shop.utils.PinyinUtil;
import com.github.pagehelper.PageHelper;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.RestController;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @ClassName BrandServiceImpl
 * @Description: TODO
 * @Author yyq
 * @Date 2021/1/23
 * @Version V1.0
 **/
@RestController
public class BrandServiceImpl extends BaseApiService implements BrandService {
    @Resource
    private BrandMapper  brandMapper;

    @Resource
    private CategoryBrandMapper categoryBrandMapper;

    @Transactional
    @Override
    public Result<JSONObject> saveBrandInfo(BrandDTO brandDTO) {

        BrandEntity brandeEntity = BaiduBeanUtils.copyProperties(brandDTO, BrandEntity.class);
        brandeEntity.setLetter(PinyinUtil.getUpperCase(String.valueOf(brandeEntity.getName().toCharArray()[0]), false).toCharArray()[0]);
        brandMapper.insertSelective(brandeEntity);

        this.insertCategoryBrandList(brandDTO.getCategories(),brandeEntity.getId());

        return this.setResultSuccess();
    }

    @Override
    public Result<PageInfo<BrandEntity>> getBrandInfo(BrandDTO brandDTO) {
        //当sort不为空的情况下
        if (!StringUtils.isEmpty(brandDTO.getSort())) {
            PageHelper.orderBy(brandDTO.getSort() + " " + (Boolean.valueOf(brandDTO.getOrder()) ? "desc" : "asc"));
        }
        //分页
        PageHelper.startPage(brandDTO.getPage(), brandDTO.getRows());

        //条件查询
        BrandEntity brandeEntity = BaiduBeanUtils.copyProperties(brandDTO, BrandEntity.class);

        //创建Example的实例
        Example example = new Example(BrandEntity.class);
        example.createCriteria().andLike("name", "%" + brandeEntity.getName() + "%");
        List<BrandEntity> list = brandMapper.selectByExample(example);

        PageInfo<BrandEntity> pageInfo = new PageInfo<>(list);
        return this.setResultSuccess(pageInfo);
    }

    private void insertCategoryBrandList(String categories,Integer brandId){
        if (StringUtils.isEmpty(categories)) throw  new RuntimeException("商品分类信息不能为空");

        if (categories.contains(",")) {
            categoryBrandMapper.insertList(Arrays.asList(categories.split(","))
                    .stream()
                    .map(categoryIdStr -> new CategoryBrandEntity(Integer.valueOf(categoryIdStr), brandId))
                    .collect(Collectors.toList())
            );
        } else {
            CategoryBrandEntity categoryBrandEntity = new CategoryBrandEntity();
            categoryBrandEntity.setBrandId(brandId);
            categoryBrandEntity.setCategoryId(Integer.valueOf(categories));
            categoryBrandMapper.insertSelective(categoryBrandEntity);
        }
    }
}
