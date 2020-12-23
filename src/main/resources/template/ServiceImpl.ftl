package ${common.parentPackageName}.service.impl;

import buckets.framework.base.common.service.impl.BaseServiceImpl;
import ${common.parentPackageName}.dao.${common.className}Dao;
import ${common.parentPackageName}.service.${common.className}Service;
import ${common.parentPackageName}.entity.${common.className};
import org.springframework.stereotype.Service;

/**
 * ${table.tableComment} 业务实现类
 * @author ${common.author}
 * @email ${common.email}
 * @date ${common.date}
 */
@Service
public class ${common.className}ServiceImpl extends BaseServiceImpl<${common.className}Dao, ${common.className}, ${table.tableIdType}> implements ${common.className}Service {

}
