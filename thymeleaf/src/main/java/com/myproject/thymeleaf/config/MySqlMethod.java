package com.myproject.thymeleaf.config;

import com.baomidou.mybatisplus.core.injector.AbstractMethod;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlSource;
import org.apache.ibatis.scripting.defaults.RawSqlSource;

/**
 * @author zhanjianjian
 * @since 2021/7/28
 */
public class MySqlMethod extends AbstractMethod {

    @Override
    public MappedStatement injectMappedStatement(Class<?> mapperClass, Class<?> modelClass, TableInfo tableInfo) {
        /* 执行 SQL ，动态 SQL 参考类 SqlMethod */
        String sql = "select * from " + tableInfo.getTableName() + " where " +
                tableInfo.getLogicDeleteSql(false, false);
        /* mapper 接口方法名一致 */
        String method = "mySqlMethod";
        SqlSource sqlSource = new RawSqlSource(configuration, sql, null);
        return this.addSelectMappedStatementForTable(mapperClass, method, sqlSource, tableInfo);
    }
}
