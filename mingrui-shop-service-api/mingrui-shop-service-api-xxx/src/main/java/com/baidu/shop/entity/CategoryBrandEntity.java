package com.baidu.shop.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Table;

/**
 * @ClassName CategoryBrandEntity
 * @Description: TODO
 * @Author yyq
 * @Date 2021/1/24
 * @Version V1.0
 **/
@Table(name = "tb_category_brand")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryBrandEntity {
    private Integer categoryId;
    private Integer brandId;
}
