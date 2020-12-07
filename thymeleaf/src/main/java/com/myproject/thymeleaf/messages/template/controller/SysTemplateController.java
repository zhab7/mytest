package com.myproject.thymeleaf.messages.template.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.myproject.thymeleaf.messages.template.bean.entity.SysTemplate;
import com.myproject.thymeleaf.messages.template.bean.req.SysTemplateReq;
import com.myproject.thymeleaf.messages.template.bean.vo.SysTemplateVo;
import com.myproject.thymeleaf.messages.template.bean.vo.TemplateQueryVo;
import com.myproject.thymeleaf.messages.template.service.SysTemplateService;
import com.myproject.thymeleaf.model.entity.SysUser;
import com.myproject.thymeleaf.util.BeanConvertUtils;
import org.apache.shiro.SecurityUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * Created by zhanjianjian on 2020/12/1.
 */
@RestController
@RequestMapping("/rest/template")
public class SysTemplateController {

    @Resource
    private SysTemplateService sysTemplateService;

    @PostMapping()
    public void saveTemplate(@RequestBody @Valid SysTemplateReq sysTemplateReq) {
        SysTemplate sysTemplate = BeanConvertUtils.map(sysTemplateReq, SysTemplate.class);
        SysUser user = (SysUser) SecurityUtils.getSubject().getPrincipal();
        sysTemplate.setOperatorId(user.getUserName());
        sysTemplateService.saveTemplate(sysTemplate);
    }

    @PutMapping("/{id}")
    public void updateTemplate(@PathVariable Long id, @RequestBody @Valid SysTemplateReq sysTemplateReq) {
        SysTemplate sysTemplate = BeanConvertUtils.map(sysTemplateReq, SysTemplate.class);
        SysUser user = (SysUser) SecurityUtils.getSubject().getPrincipal();
        sysTemplate.setOperatorId(user.getUserName());
        sysTemplateService.updateTemplate(id, sysTemplate);
    }

    @DeleteMapping("/{id}")
    public void deleteTemplate(@PathVariable Long id) {
        sysTemplateService.deleteById(id);
    }

    @PostMapping("/queryTemplate")
    public IPage<SysTemplateVo> queryTemplate(@RequestParam(required = false, defaultValue = "1") Long currentPage,
                                              @RequestParam(required = false, defaultValue = "20") Integer size,
                                              TemplateQueryVo templateQueryVo) {
        // 查询数据库，获取数据
        return sysTemplateService.selectWithPage(currentPage, size, templateQueryVo);
    }
}
