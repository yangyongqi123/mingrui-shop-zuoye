package com.baidu.shop.mapper;

import com.baidu.shop.entity.CategoryEntity;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.additional.idlist.SelectByIdListMapper;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @ClassName CategoryMapper
 * @Description: TODO
 * @Author yyq
 * @Date 2021/1/19
 * @Version V1.0
 **/
public interface CategoryMapper extends Mapper<CategoryEntity>, SelectByIdListMapper<CategoryEntity,Integer> {
    @Select("select id,name from tb_category where id in (select category_id from tb_category_brand where brand_id=#{brandId} )")
    List<CategoryEntity> getCategoryByBrandId(Integer brandId);
}
