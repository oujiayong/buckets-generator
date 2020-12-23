package ${common.parentPackageName}.entity;

import buckets.framework.base.common.entity.BaseEntity;
<#if common.useSwagger>
import io.swagger.annotations.*;
</#if>
<#if common.useLombok == true>
import lombok.Data;
</#if>

import javax.persistence.*;
import java.util.Date;

/**
 * ${table.tableComment} 实体
 * @author ${common.author}
 * @email ${common.email}
 * @date ${common.date}
 */
<#if common.useLombok == true>
@Data
</#if>
@Table(name = "${table.tableName}")
<#if common.useSwagger>
@ApiModel(value = "${table.tableComment}新建、修改视图对象")
</#if>
public class ${common.className} extends BaseEntity{

<#list table.columns as column>
    <#if "PRI" == column.columnKey>
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    </#if>
    @Column(name="${column.columnSqlName}",columnDefinition = "${column.columnComment}")
    <#if common.useSwagger>
    @ApiModelProperty(value = "${column.columnComment}",example = "${column.columnComment} dataType:${column.dataType}",dataType = "${column.dataType}")
    </#if>
    private ${column.dataType} ${column.columnName};

</#list>
<#if common.useLombok == false>
<#list table.columns as column>
    public void set${column.columnName?cap_first}(${column.dataType} ${column.columnName}){
    this.${column.columnName} = ${column.columnName};
    }

    public ${column.dataType} get${column.columnName?cap_first}(){
    return this.${column.columnName};
    }

</#list>
</#if>
}