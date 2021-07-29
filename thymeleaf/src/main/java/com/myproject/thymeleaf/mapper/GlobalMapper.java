package com.myproject.thymeleaf.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * @author zhanjianjian
 * @since 2021/7/28
 */
public interface GlobalMapper<T> extends BaseMapper<T> {

    List<T> listAllAvailable();

}
