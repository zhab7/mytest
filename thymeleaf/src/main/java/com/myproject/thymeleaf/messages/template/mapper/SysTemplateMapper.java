package com.myproject.thymeleaf.messages.template.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.myproject.thymeleaf.messages.template.bean.entity.SysTemplate;
import com.myproject.thymeleaf.messages.template.bean.vo.TemplateQueryVo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface SysTemplateMapper extends BaseMapper<SysTemplate> {

    @Select("<script> SELECT " +
            " *  " +
            " FROM " +
            " sys_template  " +
            " WHERE " +
            " deleted IS NULL  " +
            "<if test = 'query.templateTypeList != null and query.templateTypeList.size()>0'>" +
            " AND template_type IN  " +
            "<foreach collection='query.templateTypeList' item='item'  open='(' close=')' separator=','> " +
            "  #{item}  " +
            "</foreach>" +
            "</if>" +
            "<if test = 'query.roleIdList != null and query.roleIdList.size()>0'>" +
            " AND role_id IN  " +
            "<foreach collection='query.roleIdList' item='item'  open='(' close=')' separator=','> " +
            "  #{item}  " +
            "</foreach>" +
            "</if>" +
            "<if test = 'query.userIdList != null and query.userIdList.size()>0'>" +
            " AND user_id IN " +
            "<foreach collection='query.userIdList' item='item'  open='(' close=')' separator=','> " +
            "  #{item}  " +
            "</foreach>" +
            "</if>" +
            "<if test = 'query.operatorIdList != null and query.operatorIdList.size()>0'>" +
            " AND operator_id IN " +
            "<foreach collection='query.operatorIdList' item='item'  open='(' close=')' separator=','> " +
            "  #{item}  " +
            "</foreach>" +
            "</if>" +
            " <if test = 'query.templateEnable != null and query.templateEnable != \"\" ' > " +
            " AND template_enable = #{query.templateEnable}  " +
            "</if>" +
            "<if test = 'query.startOperatorTime != null'>" +
            "<![CDATA[ AND updated  >= #{query.startOperatorTime}  ]]>" +
            "</if>" +
            "<if test = 'query.endOperatorTime != null'>" +
            "<![CDATA[ AND updated  <= #{query.endOperatorTime}  ]]>" +
            "</if>" +
            " </script>")
    IPage<SysTemplate> selectWithPage(IPage<SysTemplate> iPage, @Param("query") TemplateQueryVo templateQueryVo);
}
