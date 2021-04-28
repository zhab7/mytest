package com.myproject.thymeleaf.msg.service;

import com.myproject.thymeleaf.msg.bean.model.SendParam;


public interface SendMsgService {

    /**
     * 相同文案，发给0~N 人
     *
     * @param sendParam
     */
    void sendMsg(SendParam sendParam);

    /**
     * 不同文案，发给不同人，一次可接收多组
     *
     * @param sendParam
     */
    void batchSendMsg(SendParam sendParam);
}
