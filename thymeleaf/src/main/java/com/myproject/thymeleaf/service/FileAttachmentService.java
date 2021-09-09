package com.myproject.thymeleaf.service;

import com.myproject.thymeleaf.model.entity.FileAttachment;

public interface FileAttachmentService {

    void saveFileDetail(FileAttachment fileAttachment);

    FileAttachment getByFilePath(String filePath);
}
