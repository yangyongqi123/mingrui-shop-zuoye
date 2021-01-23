package com.baidu.shop.utils;

import org.springframework.beans.BeanUtils;

/**
 * @ClassName BaiduBeanUtils
 * @Description: TODO
 * @Author yyq
 * @Date 2021/1/23
 * @Version V1.0
 **/
public class BaiduBeanUtils<T> {
    public static  <T> T copyProperties(Object obj, Class<T> clazz){

        try {
            T t = clazz.newInstance();
            BeanUtils.copyProperties(obj,t);
            return t;
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return  null;
    }
}
