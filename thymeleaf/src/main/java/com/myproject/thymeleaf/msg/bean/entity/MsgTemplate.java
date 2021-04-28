package com.myproject.thymeleaf.msg.bean.entity;

/**
 * @author zhanjianjian
 * @since 2021/2/22
 */
public class MsgTemplate {

    /**
     * 消息类型：1.邮件；2.短信
     */
    String msgType;

    /**
     * 消息文案内容
     */
    String msgContent;

    /**
     * 发送者账号
     */
    String msgSendId;

    /**
     * 消息内容类型：通知、营销、验证码；主要是区分消息的优先性和及时性，走不通通道，避免阻塞
     */
    String msgOrderType;

}
