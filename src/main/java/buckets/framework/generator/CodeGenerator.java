package buckets.framework.generator;

import buckets.framework.generator.configuration.GenerationConfig;
import buckets.framework.generator.module.Column;
import buckets.framework.generator.module.CommonInfo;
import buckets.framework.generator.module.TableInfo;
import buckets.framework.generator.utils.HumpToLine;
import buckets.framework.generator.utils.JdbcUtils;
import buckets.framework.generator.utils.SqlUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.StrUtil;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;

import java.io.*;
import java.util.*;

/**
 * @author buckets
 * @date 2020/12/21
 */
public class CodeGenerator {

    public static void main(String[] args) {
        try {
            new CodeGenerator().gen();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TemplateException e) {
            e.printStackTrace();
        }

//        System.out.println(getTableInfo("dictionary"));
    }

    public void gen() throws IOException, TemplateException {

        Configuration cfg = new Configuration(Configuration.VERSION_2_3_22);
        cfg.setClassForTemplateLoading(this.getClass(), "/template");
        cfg.setDefaultEncoding("UTF-8");
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);

        String[] tableNames = GenerationConfig.tableNames.split(",");

        for (String tableName : tableNames) {
            TableInfo tableInfo = getTableInfo(tableName);
            if (tableInfo == null) {
                continue;
            }

            CommonInfo commonInfo = new CommonInfo();
            commonInfo.setAuthor(GenerationConfig.author);
            commonInfo.setBeanName(HumpToLine.lineToHump(tableNameToBeanName(tableName)));
            commonInfo.setControllerMappingPath(tableNameToMapping(tableNameToBeanName(tableName)));
            commonInfo.setClassName(StrUtil.upperFirst(commonInfo.getBeanName()));
            commonInfo.setUseLombok(GenerationConfig.useLombok);
            commonInfo.setUseSwagger(GenerationConfig.useSwagger);
            commonInfo.setDate(DateUtil.format(new Date(), "yyyy-MM-dd HH:mm:ss"));
            commonInfo.setEmail(GenerationConfig.email);
            commonInfo.setParentPackageName(GenerationConfig.parentPkgName);

            Map<String, Object> map = new HashMap<>(2);
            map.put("table", tableInfo);
            map.put("common", commonInfo);

            genEntity(cfg, map, commonInfo.getClassName());
            System.out.println(String.format("数据库表[%s] entity生成完成", tableName));

            genDao(cfg, map, commonInfo.getClassName());
            System.out.println(String.format("数据库表[%s] dao生成完成", tableName));

            genService(cfg, map, commonInfo.getClassName());
            System.out.println(String.format("数据库表[%s] service生成完成", tableName));

            genController(cfg, map, commonInfo.getClassName());
            System.out.println(String.format("数据库表[%s] controller生成完成", tableName));
        }

        System.out.println("gen code success!");
    }

    public static void genEntity(Configuration cfg, Map<String, Object> root, String tableToClassName) throws IOException, TemplateException {
        Template temp = cfg.getTemplate("Entity.ftl");
        genJavaBean(temp, root, "/entity/" + tableToClassName + ".java");
    }

    public static void genDao(Configuration cfg, Map<String, Object> root, String tableToClassName) throws IOException, TemplateException {
        Template dao = cfg.getTemplate("Dao.ftl");
        genJavaBean(dao, root, "/dao/" + tableToClassName + "Dao.java");
        Template mapper = cfg.getTemplate("Mapper.ftl");
        genResources(mapper, root, "/buckets/mybatis/mapper/" + tableToClassName + "Mapper.xml");

    }

    public static void genService(Configuration cfg, Map<String, Object> root, String tableToClassName) throws IOException, TemplateException {
        Template svc = cfg.getTemplate("Service.ftl");
        genJavaBean(svc, root, "/service/" + tableToClassName + "Service.java");
        Template impl = cfg.getTemplate("ServiceImpl.ftl");
        genJavaBean(impl, root, "/service/impl/" + tableToClassName + "ServiceImpl.java");
    }

    public static void genController(Configuration cfg, Map<String, Object> root, String tableToClassName) throws IOException, TemplateException {
        Template vo = cfg.getTemplate("SaveVo.ftl");
        genJavaBean(vo, root, "/vo/" + tableToClassName + "SaveVo.java");
        Template controller = cfg.getTemplate("Controller.ftl");
        genJavaBean(controller, root, "/controller/" + tableToClassName + "Controller.java");
    }

    public static void genJavaBean(Template temp, Map<String, Object> root, String pathName) throws IOException, TemplateException {
        OutputStream out = FileUtil.getOutputStream(GenerationConfig.baseDir + "/src/main/java/" + GenerationConfig.parentPkgName.replace(".", "/") + "/" + pathName);
        temp.process(root, new OutputStreamWriter(out));
        out.flush();
        out.close();
    }

    public static void genResources(Template temp, Map<String, Object> root, String pathName) throws IOException, TemplateException {
        OutputStream out = FileUtil.getOutputStream(GenerationConfig.baseDir + "/src/main/resources/" + pathName);
        temp.process(root, new OutputStreamWriter(out));
        out.flush();
        out.close();
    }

    public static TableInfo getTableInfo(String tableName) {
        TableInfo tableInfo = new TableInfo();
        tableInfo.setTableName(tableName);
        String tableSql = "SELECT * FROM INFORMATION_SCHEMA.tables WHERE table_name = '" + tableName + "' and table_schema = (select database());";
        List<Map<String, String>> tableList = JdbcUtils.execute(tableSql);
        if (tableList.size() == 0) {
            System.out.println(String.format("数据库表[%s]不存在", tableName));
            return null;
        }

        tableInfo.setTableComment(tableList.get(0).get("TABLE_COMMENT"));

        //查询表字段
        String tableColumnSql = "SELECT * FROM INFORMATION_SCHEMA.Columns WHERE table_name = '" + tableName + "' and table_schema = (select database());";
        List<Map<String, String>> tableColumnList = JdbcUtils.execute(tableColumnSql);

        Column column;
        List<Column> columns = new ArrayList<>();
        for (Map<String, String> tableColumn :
                tableColumnList) {
            column = new Column();
            column.setColumnSqlName(tableColumn.get("COLUMN_NAME"));
            column.setColumnName(HumpToLine.lineToHump(tableColumn.get("COLUMN_NAME")));
            column.setDataType(SqlUtil.toSqlToJava(tableColumn.get("DATA_TYPE")));
            column.setNullable("YES".equals(tableColumn.get("IS_NULLABLE")));
            column.setColumnKey(tableColumn.get("COLUMN_KEY"));
            column.setColumnComment(tableColumn.get("COLUMN_COMMENT"));
            columns.add(column);

            if ("PRI".equals(column.getColumnKey().toUpperCase())) {
                tableInfo.setTableIdType(column.getDataType());
            }
        }
        tableInfo.setColumns(columns);
        return tableInfo;
    }

    public static String tableNameToBeanName(String tableName) {
        return tableName.replaceFirst(GenerationConfig.tablePrefix, "");
    }

    public static String tableNameToMapping(String tableName) {
        return "/" + tableName.replace("_", "/") + "/";
    }
}
