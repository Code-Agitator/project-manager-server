package pers.llz.user.config.mybatis.handler;

import com.baomidou.mybatisplus.core.injector.AbstractMethod;
import com.baomidou.mybatisplus.core.metadata.TableFieldInfo;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import org.apache.ibatis.executor.keygen.NoKeyGenerator;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlSource;

public class RecoverLogicDeleted extends AbstractMethod {
    public RecoverLogicDeleted(String methodName) {
        super(methodName);
    }

    @Override
    public MappedStatement injectMappedStatement(Class<?> mapperClass, Class<?> modelClass, TableInfo tableInfo) {
        final String sql = "<script>update %s set %s where %s</script>";
        final String fieldSql = prepareFieldSql(tableInfo);
        final String whereSql = prepareWhereSqlForMysqlBatch(tableInfo);
        final String sqlResult = String.format(sql, tableInfo.getTableName(), fieldSql, whereSql);
        SqlSource sqlSource = languageDriver.createSqlSource(configuration, sqlResult, modelClass);
        return this.addInsertMappedStatement(mapperClass, modelClass, "recoverLogicDeleted", sqlSource, new NoKeyGenerator(), null, null);
    }

    private String prepareFieldSql(TableInfo tableInfo) {
        final StringBuilder valueSql = new StringBuilder();
        final TableFieldInfo logicDeleteFieldInfo = tableInfo.getLogicDeleteFieldInfo();
        if (logicDeleteFieldInfo != null) {
            final String logicDeleteColum = logicDeleteFieldInfo.getColumn();
            final String logicNotDeleteValue = logicDeleteFieldInfo.getLogicNotDeleteValue();
            valueSql.append(logicDeleteColum).append("=").append(logicNotDeleteValue);
        }
        return valueSql.toString();
    }

    private String prepareWhereSqlForMysqlBatch(TableInfo tableInfo) {
        return tableInfo.getKeyColumn() + " " + "=" + " " + "#{id}";
    }


}
