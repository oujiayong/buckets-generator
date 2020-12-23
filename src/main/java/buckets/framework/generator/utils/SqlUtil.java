package buckets.framework.generator.utils;

/**
 * @author buckets
 * @date 2020/12/22
 */
public class SqlUtil {


    /**
     * 数据类型转化JAVA
     * @param sqlType：类型名称
     * @return javaType
     */
    public static String toSqlToJava(String sqlType) {
        if( sqlType == null || sqlType.trim().length() == 0 ) {
            return sqlType;
        }
        sqlType = sqlType.toLowerCase();
        switch(sqlType){
            case "nvarchar":
            case "varchar":
            case "char":
            case "nchar":
            case "text":
                return "String";
            case "blob":
            case "image":
                return "byte[]";
            case "id":
                return "Long";
            case "int":
            case "integer":
            case "tinyint":
            case "smallint":
            case "mediumint":
                return "Integer";
            case "bit":
            case "boolean":
                return "Boolean";
            case "bigint":return "java.math.BigInteger";
            case "float":return "Fload";
            case "double":
            case "smallmoney":
            case "money":
                return "Double";
            case "decimal":
            case "real":
            case "numeric":
                return "java.math.BigDecimal";
            case "date":
            case "year":
            case "datetime":
            case "time":
            case "timestamp":
                return "Date";
            default:
                System.out.println("-----------------》转化失败：未发现的类型"+sqlType);
                break;
        }
        return sqlType;
    }

}
