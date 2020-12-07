package com.myproject.thymeleaf.messages.template.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.myproject.thymeleaf.messages.template.bean.entity.SysTemplate;
import com.myproject.thymeleaf.messages.template.bean.vo.SysTemplateVo;
import com.myproject.thymeleaf.messages.template.bean.vo.TemplateQueryVo;
import com.myproject.thymeleaf.messages.template.mapper.SysTemplateMapper;
import com.myproject.thymeleaf.messages.template.service.SysTemplateService;
import com.myproject.thymeleaf.model.constant.SysConstant;
import com.myproject.thymeleaf.model.entity.SysRole;
import com.myproject.thymeleaf.model.entity.SysUser;
import com.myproject.thymeleaf.service.SysRoleService;
import com.myproject.thymeleaf.service.SysUserService;
import com.myproject.thymeleaf.util.BeanConvertUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by zhanjianjian on 2020/12/1.
 */
@Service
@Slf4j
public class SysTemplateServiceImpl implements SysTemplateService {

    @Resource
    private SysTemplateMapper sysTemplateMapper;
    @Resource
    private SysRoleService sysRoleService;
    @Resource
    private SysUserService sysUserService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveTemplate(SysTemplate sysTemplate) {
        // 1.判断用户或者角色是否为空，若为空则用通配符*代替。2.判断该用户 + 角色是否已存在模板，若存在则更新
        if (StringUtils.isBlank(sysTemplate.getUserId())) {
            sysTemplate.setUserId(SysConstant.ASTERISK);
        }
        if (StringUtils.isBlank(sysTemplate.getRoleId())) {
            sysTemplate.setRoleId(SysConstant.ASTERISK);
        }
        // 根据用户id和角色id查询模板，有就更新，没有就新增
        SysTemplate template = getByUserIdAndRoleId(sysTemplate.getUserId(), sysTemplate.getRoleId());
        if (template == null) {
            sysTemplateMapper.insert(sysTemplate);
        } else {
            updateTemplate(template.getId(), sysTemplate);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateTemplate(Long id, SysTemplate sysTemplate) {
        LambdaQueryWrapper<SysTemplate> query = Wrappers.<SysTemplate>lambdaQuery().eq(SysTemplate::getId, id);
        sysTemplateMapper.update(sysTemplate, query);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteById(Long id) {
        sysTemplateMapper.delete(Wrappers.<SysTemplate>lambdaQuery().eq(SysTemplate::getId, id));
    }

    @Override
    public SysTemplate getByUserIdAndRoleId(String userId, String roleId) {
        return sysTemplateMapper.selectOne(Wrappers.<SysTemplate>lambdaQuery().eq(SysTemplate::getUserId, userId).eq(SysTemplate::getRoleId, roleId));
    }

    @Override
    public IPage<SysTemplateVo> selectWithPage(Long currentPage, Integer size, TemplateQueryVo templateQueryVo) {
        IPage<SysTemplate> iPage = new Page<>(currentPage, size);
        // 查询数据库，获取数据
        iPage = sysTemplateMapper.selectWithPage(iPage, templateQueryVo);
        IPage<SysTemplateVo> page = iPage.convert(template -> BeanConvertUtils.map(template, SysTemplateVo.class));
        List<SysTemplateVo> templateVos = page.getRecords();
        // 查询文本信息，填充VO
        dealWithVo(templateVos);
        return page;
    }

    private void dealWithVo(List<SysTemplateVo> templateVos) {
        if (CollectionUtils.isNotEmpty(templateVos)) {
            List<String> userIdList = new LinkedList<>();
            List<String> roleIdList = new LinkedList<>();
            List<String> operatorIdList = new LinkedList<>();
            templateVos.forEach(vo -> {
                userIdList.add(vo.getUserId());
                roleIdList.add(vo.getRoleId());
                operatorIdList.add(vo.getOperatorId());
            });
            // FIXME 是否需要将用户和操作人组合成一个list，然后查询
            // 查询数据库，获取文本值
            List<SysUser> sysUsers = sysUserService.selectByUserName(userIdList);
            List<SysRole> sysRoles = sysRoleService.selectByRoleName(roleIdList);
            List<SysUser> operators = sysUserService.selectByUserName(operatorIdList);
            // 构建map
            Map<String, String> userMap = new HashMap<>(sysUsers.size());
            Map<String, String> roleMap = new HashMap<>(sysRoles.size());
            Map<String, String> operatorMap = new HashMap<>(operators.size());

            sysUsers.forEach(u -> userMap.put(u.getUserName(), u.getRealName()));
            sysRoles.forEach(r -> roleMap.put(r.getRoleName(), r.getDescription()));
            operators.forEach(o ->operatorMap.put(o.getUserName(), o.getRealName()));

            templateVos.forEach(t -> {
                if (MapUtils.isNotEmpty(userMap) && userMap.containsKey(t.getUserId())) {
                    t.setUserName(userMap.get(t.getUserId()));
                }
                if (MapUtils.isNotEmpty(roleMap) && roleMap.containsKey(t.getRoleId())) {
                    t.setRoleName(roleMap.get(t.getRoleId()));
                }
                if (MapUtils.isNotEmpty(operatorMap) && operatorMap.containsKey(t.getOperatorId())) {
                    t.setOperatorName(operatorMap.get(t.getOperatorId()));
                }
            });
        }
    }
}
