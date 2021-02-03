package com.baidu.shop.mapper;

import com.baidu.shop.entity.BrandEntity;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @ClassName BrandMapper
 * @Description: TODO
 * @Author yyq
 * @Date 2021/1/23
 * @Version V1.0
 **/
public interface BrandMapper extends Mapper<BrandEntity> {
    @Select(value = "SELECT b.* FROM tb_brand b WHERE b.id in (SELECT brand_id FROM tb_category_brand cb where cb.category_id = #{cid})")
    List<BrandEntity> getBrandByCategoryId(Integer cid);
}
