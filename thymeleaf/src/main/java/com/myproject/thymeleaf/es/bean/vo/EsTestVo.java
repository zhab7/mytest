package com.myproject.thymeleaf.es.bean.vo;

import lombok.Data;

import java.util.List;

/**
 * @author zhanjianjian
 * @since 2021/3/25
 */
@Data
public class EsTestVo {

    String name;

    Integer age;

    List<String> hobbies;

}
