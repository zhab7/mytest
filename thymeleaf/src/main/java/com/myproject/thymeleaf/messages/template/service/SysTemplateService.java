package com.myproject.thymeleaf.messages.template.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.myproject.thymeleaf.messages.template.bean.entity.SysTemplate;
import com.myproject.thymeleaf.messages.template.bean.vo.SysTemplateVo;
import com.myproject.thymeleaf.messages.template.bean.vo.TemplateQueryVo;

public interface SysTemplateService {

    void saveTemplate(SysTemplate sysTemplate);

    void updateTemplate(Long id, SysTemplate sysTemplate);

    void deleteById(Long id);

    SysTemplate getByUserIdAndRoleId(String userId, String roleId);

    IPage<SysTemplateVo> selectWithPage(Long currentPage, Integer size, TemplateQueryVo templateQueryVo);

}
