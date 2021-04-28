package com.myproject.thymeleaf.exception;

import lombok.Data;

/**
 * @author zhanjianjian
 * @since 2021/4/16
 */
@Data
public class ProjectGlobalException extends RuntimeException {

    private String code;

    private String message;

    private Object[] args;

    public ProjectGlobalException(String message) {
        this.message = message;
        this.code = "-1";
        this.args = null;
    }

    public ProjectGlobalException(String code, String message) {
        this.message = message;
        this.code = code;
        this.args = null;
    }

    public ProjectGlobalException(String message, Object... args) {
        this.message = message;
        this.code = "-1";
        this.args = args;
    }

    public ProjectGlobalException(ExceptionEnumInterface exceptionEnumInterface, Object... args) {
        this.message = exceptionEnumInterface.getMsg();
        this.code = exceptionEnumInterface.getCode();
        this.args = args;
    }

}
