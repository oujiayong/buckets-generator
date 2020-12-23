package buckets.framework.generator.module;

/**
 * @author buckets
 * @date 2020/12/21
 */
public class Column {

    /**
     * 列名
     */
    private String columnSqlName;

    /**
     * 列名
     */
    private String columnName;

    /**
     * 数据类型
     */
    private String dataType;

    /**
     * 键值
     */
    private String columnKey;

    /**
     * 是否允许为空
     */
    private boolean isNullable;

    /**
     * 备注
     */
    private String columnComment;

    public String getColumnSqlName() {
        return columnSqlName;
    }

    public void setColumnSqlName(String columnSqlName) {
        this.columnSqlName = columnSqlName;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public String getColumnKey() {
        return columnKey;
    }

    public void setColumnKey(String columnKey) {
        this.columnKey = columnKey;
    }

    public boolean isNullable() {
        return isNullable;
    }

    public void setNullable(boolean nullable) {
        isNullable = nullable;
    }

    public String getColumnComment() {
        return columnComment;
    }

    public void setColumnComment(String columnComment) {
        this.columnComment = columnComment;
    }

    @Override
    public String toString() {
        return "Column{" +
                "columnName='" + columnName + '\'' +
                ", dataType='" + dataType + '\'' +
                ", columnKey='" + columnKey + '\'' +
                ", isNullable=" + isNullable +
                ", columnComment='" + columnComment + '\'' +
                '}';
    }
}
