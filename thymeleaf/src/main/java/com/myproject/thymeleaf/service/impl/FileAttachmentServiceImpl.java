package com.myproject.thymeleaf.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.myproject.thymeleaf.mapper.FileAttachmentMapper;
import com.myproject.thymeleaf.model.entity.FileAttachment;
import com.myproject.thymeleaf.service.FileAttachmentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author zhanjianjian
 * @since 2021/9/9
 */
@Slf4j
@Service
public class FileAttachmentServiceImpl implements FileAttachmentService {

    @Resource
    private FileAttachmentMapper fileAttachmentMapper;

    @Override
    public void saveFileDetail(FileAttachment fileAttachment) {

//        fileAttachment.setOperator();
        fileAttachmentMapper.insert(fileAttachment);
    }

    @Override
    public FileAttachment getByFilePath(String filePath) {

        return fileAttachmentMapper.selectOne(Wrappers.<FileAttachment>lambdaQuery().eq(FileAttachment::getFilePath, filePath));
    }
}
