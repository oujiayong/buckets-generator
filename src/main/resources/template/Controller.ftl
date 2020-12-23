package ${common.parentPackageName}.controller;

import buckets.framework.base.common.utils.PageUtil;
import buckets.framework.base.common.web.BaseRestController;
import ${common.parentPackageName}.service.${common.className}Service;
import ${common.parentPackageName}.vo.${common.className}SaveVo;
import ${common.parentPackageName}.entity.${common.className};
<#if common.useSwagger>
import io.swagger.annotations.*;
</#if>
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * ${table.tableComment} 接口类
 * @author ${common.author}
 * @email ${common.email}
 * @date ${common.date}
 */
<#if common.useSwagger>
@Api(tags = "${table.tableComment}")
</#if>
@RestController
@RequestMapping("${common.controllerMappingPath}")
public class ${common.className}Controller extends BaseRestController {

    @Autowired
    ${common.className}Service ${common.beanName}Service;

    @GetMapping("/get/{id}")
<#if common.useSwagger>
    @ApiOperation(value = "查询详情")
</#if>
    public ${common.className} get(@PathVariable("id") ${table.tableIdType} id) {
        return ${common.beanName}Service.get(id);
    }

    @PostMapping("/add")
<#if common.useSwagger>
    @ApiOperation(value = "添加")
</#if>
    public ${common.className} add(@RequestBody ${common.className}SaveVo ${common.beanName}SaveVo) {
        ${common.className} ${common.beanName} = new ${common.className}();
        BeanUtils.copyProperties(${common.beanName}SaveVo, ${common.beanName});
        return ${common.beanName}Service.add(${common.beanName});
    }

    @PostMapping("/update/{id}")
<#if common.useSwagger>
    @ApiOperation(value = "修改")
</#if>
    public ${common.className} update(@PathVariable("id") ${table.tableIdType} id, @RequestBody ${common.className}SaveVo ${common.beanName}SaveVo) {
        ${common.className} ${common.beanName} = new ${common.className}();
        BeanUtils.copyProperties(${common.beanName}SaveVo, ${common.beanName});
        ${common.beanName}.setId(id);
        return ${common.beanName}Service.update(${common.beanName});
    }

    @PostMapping("/del")
<#if common.useSwagger>
    @ApiOperation(value = "删除")
</#if>
    public int del(@RequestBody ${table.tableIdType}[] ids) {
        return ${common.beanName}Service.del(ids);
    }

    @PostMapping("/page")
<#if common.useSwagger>
    @ApiOperation(value = "分页查询")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "page", value = "当前页", defaultValue = "1", example = "1", dataType = "Integer", paramType = "query"),
        @ApiImplicitParam(name = "size", value = "每页数量", defaultValue = "10", example = "10", dataType = "Integer", paramType = "query"),
    })
</#if>
    public PageUtil<${common.className}> page(@ApiParam(hidden = true) @RequestParam Map<String, Object> params) {
        return ${common.beanName}Service.page(params);
    }
}