package ${common.parentPackageName}.vo;

<#if common.useSwagger>
import io.swagger.annotations.*;
</#if>
<#if common.useLombok == true>
import lombok.Getter;
import lombok.Setter;
</#if>

/**
 * ${table.tableComment} 新增、修改视图对象
 * @author ${common.author}
 * @email ${common.email}
 * @date ${common.date}
 */
<#if common.useLombok == true>
@Setter
@Getter
</#if>
<#if common.useSwagger>
@ApiModel(value = "${table.tableComment}新建、修改视图对象")
</#if>
public class ${common.className}SaveVo {

<#list table.columns as column>
<#if column.columnName != "id" && column.columnName != "createTime" && column.columnName != "updateTime">
    <#if common.useSwagger>
    @ApiModelProperty(value = "${column.columnComment}",example = "${column.columnComment} dataType:${column.dataType}",dataType = "${column.dataType}")
    </#if>
    private ${column.dataType} ${column.columnName};

</#if>
</#list>
<#if common.useLombok == false>
<#list table.columns as column>
<#if column.columnName != "id" && column.columnName != "createTime" && column.columnName != "updateTime">
    public void set${column.columnName?cap_first}(${column.dataType} ${column.columnName}){
    this.${column.columnName} = ${column.columnName};
    }

    public ${column.dataType} get${column.columnName?cap_first}(){
    return this.${column.columnName};
    }
</#if>
</#list>
</#if>
}