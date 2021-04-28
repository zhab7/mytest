package com.myproject.thymeleaf.exception;


public enum ProjectEnum implements ExceptionEnumInterface {
    REPEAT_SUBMIT("0001", "repeat_submit"),
    ;

    private String code;

    private String msg;

    @Override
    public String getCode() {
        return this.code;
    }

    @Override
    public String getMsg() {
        return this.msg;
    }

    ProjectEnum() {
    }

    ProjectEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
