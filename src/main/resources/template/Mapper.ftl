<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="${common.parentPackageName}.dao.${common.className}Dao">

    <sql id="baseColumns">
        <#list table.columns as column>
        `${column.columnSqlName}` AS "${column.columnName}" <#if column_has_next>,</#if>
        </#list>
    </sql>

</mapper>