package buckets.framework.generator.module;

import java.util.List;

/**
 * @author buckets
 * @date 2020/12/21
 */
public class TableInfo {

    /**
     * 表名称
     */
    private String tableName;

    /**
     * 表注释
     */
    private String tableComment;

    /**
     * 表ID数据类型
     */
    private String tableIdType;

    /**
     * 表字段
     */
    private List<Column> columns;

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getTableComment() {
        return tableComment;
    }

    public void setTableComment(String tableComment) {
        this.tableComment = tableComment;
    }

    public List<Column> getColumns() {
        return columns;
    }

    public void setColumns(List<Column> columns) {
        this.columns = columns;
    }

    public String getTableIdType() {
        return tableIdType;
    }

    public void setTableIdType(String tableIdType) {
        this.tableIdType = tableIdType;
    }

    @Override
    public String toString() {
        return "TableInfo{" +
                "tableName='" + tableName + '\'' +
                ", tableComment='" + tableComment + '\'' +
                ", tableIdType='" + tableIdType + '\'' +
                ", columns=" + columns +
                '}';
    }
}
