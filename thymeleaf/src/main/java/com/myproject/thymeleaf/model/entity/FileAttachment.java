package com.myproject.thymeleaf.model.entity;

import lombok.Data;

@Data
public class FileAttachment extends BaseEntity {

    /**
     * 文件在文件服务器中的path
     */
    private String filePath;
    
    /**
     * 上传人用户id
     */
    private String operator;

    /**
     * 文件名称
     */
    private String fileName;
    
    /**
     * 附言
     */
    private String comment;

    public FileAttachment filePath(String filePath) {
        this.filePath = filePath;
        return this;
    }

    public FileAttachment fileName(String fileName) {
        this.fileName = fileName;
        return this;
    }

    public FileAttachment comment(String comment) {
        this.comment = comment;
        return this;
    }

    public FileAttachment build() {
        return new FileAttachment(this.filePath, this.fileName, this.comment);
    }

    public FileAttachment(String filePath, String fileName, String comment) {
        this.filePath = filePath;
        this.fileName = fileName;
        this.comment = comment;
    }


    public FileAttachment() {
    }
}