package com.myproject.thymeleaf.msg.bean.model;

import lombok.Data;

import java.util.Map;

/**
 * @author zhanjianjian
 * @since 2021/2/22
 */
@Data
public class MsgParam {

    /**
     * 接收者：假设有多个，则用「,」分隔开
     */
    private String receiver;

    /**
     * 自定义参数（文案）
     */
    private Map<String, String> variables;

}
