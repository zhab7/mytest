package com.myproject.thymeleaf.msg.service.impl;

import com.myproject.thymeleaf.msg.bean.model.SendParam;
import com.myproject.thymeleaf.msg.service.SendMsgService;

/**
 * @author zhanjianjian
 * @since 2021/2/22
 */
public class SendMsgServiceImpl implements SendMsgService {


    @Override
    public void sendMsg(SendParam sendParam) {

        /*
        入参：接收人、发送人、发送内容、短信还是邮件、提醒还是验证码、是否限制不接收消息、接收者id类型
        业务方 --> 统一处理层 --> 消息中间件 --> 发送层
        业务方调用发送消息接口，统一处理补全消息发送到消息中间件不同的topic上，过滤接收消息限制条件，发送消息。

        */

    }

    @Override
    public void batchSendMsg(SendParam sendParam) {

    }
}
