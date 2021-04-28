package com.myproject.thymeleaf.msg.bean.model;

import lombok.Data;

/**
 * @author zhanjianjian
 * @since 2021/2/22
 */
@Data
public class SendParam {
    /**
     * 模板Id
     */
    private String templateId;

    /**
     * 消息参数
     */
    private MsgParam msgParam;
}
