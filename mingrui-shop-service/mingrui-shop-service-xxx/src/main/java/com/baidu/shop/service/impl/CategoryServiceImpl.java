package com.baidu.shop.service.impl;

import com.baidu.shop.base.BaseApiService;
import com.baidu.shop.base.Result;
import com.baidu.shop.entity.CategoryEntity;
import com.baidu.shop.mapper.CategoryMapper;
import com.baidu.shop.service.CategoryService;
import com.baidu.shop.utils.ObjectUtil;
import com.google.gson.JsonObject;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RestController;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName CategoryServiceImpl
 * @Description: TODO
 * @Author yyq
 * @Date 2021/1/19
 * @Version V1.0
 **/
@RestController
public class CategoryServiceImpl extends BaseApiService implements CategoryService {

    @Resource
    private CategoryMapper categoryMapper;

    @Transactional
    @Override
    public Result<JsonObject> saveCateGoryById(CategoryEntity categoryEntity) {
        CategoryEntity categoryEntity1 = new CategoryEntity();
        categoryEntity1.setId(categoryEntity.getParentId());
        categoryEntity1.setIsParent(1);
        categoryMapper.updateByPrimaryKeySelective(categoryEntity1);

        categoryMapper.insertSelective(categoryEntity);
        return this.setResultSuccess();
    }

    @Transactional
    @Override
    public Result<JsonObject> editCateGoryById(CategoryEntity categoryEntity) {
            categoryMapper.updateByPrimaryKeySelective(categoryEntity);
        return this.setResultSuccess();
    }

    @Transactional
    @Override
    public Result<JsonObject> delCateGoryById(Integer id) {
        //判断Id是否合法
        if (ObjectUtil.isNull(id)) return this.setResultError("Id不合法");

        //根据Id查询数据
        CategoryEntity categoryEntity = categoryMapper.selectByPrimaryKey(id);

        //判断对象不为空
        if (ObjectUtil.isNull(categoryEntity)) return this.setResultError("数据不存在");

        //判断当前节点是否为父节点
        if (categoryEntity.getIsParent() == 1) return this.setResultError("当前节点为父节点");

        //通过被删除节点查询数据
        Example example = new Example(CategoryEntity.class);
        example.createCriteria().andEqualTo("parentId",categoryEntity.getParentId());
        List<CategoryEntity> categoryEntities = categoryMapper.selectByExample(example);

        //如果查询到的数据不大于一天  那么将父节点状态改为叶子节点
        if (categoryEntities.size() <= 1){
            CategoryEntity categoryEntity1 = new CategoryEntity();
            categoryEntity1.setIsParent(0);
            categoryEntity1.setId(categoryEntity.getParentId());
            categoryMapper.updateByPrimaryKeySelective(categoryEntity1);
        }

        categoryMapper.deleteByPrimaryKey(id);
        return this.setResultSuccess();
    }

    @Override
    public Result<List<CategoryEntity>> getCategoryByPid(Integer pid) {
        CategoryEntity entity = new CategoryEntity();
        entity.setParentId(pid);
        List<CategoryEntity> list = categoryMapper.select(entity);
        return this.setResultSuccess(list);
    }
}
