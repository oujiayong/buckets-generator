package buckets.framework.generator.utils;

import cn.hutool.json.JSONUtil;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.*;

/**
 * @author buckets
 * @date 2020/12/21
 */
public class JdbcUtils {

    private static String driver;
    private static String url;
    private static String user;
    private static String pwd;

    static {
        try {
            InputStream bundle = JdbcUtils.class.getClassLoader().getResourceAsStream("generator.properties");
            Properties properties = new Properties();
            properties.load(bundle);
            driver = properties.getProperty("jdbc.driver");
            url = properties.getProperty("jdbc.url");
            user = properties.getProperty("jdbc.user");
            pwd = properties.getProperty("jdbc.pwd");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    //获取一个链接mysql的Connection对象
    static public Connection getConnection() {

        try {
            Class.forName(driver);
            return DriverManager.getConnection(url, user, pwd);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    /**
     * 释放Mysql资源(毕竟mysql连入个数是有限的)
     *
     * @param resultSet  结果集
     * @param statement
     * @param connection 链接
     */
    public static void releaseResc(ResultSet resultSet, Statement statement, Connection connection) {

        try {
            if (resultSet != null) {
                resultSet.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if (statement != null) {
                statement.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<Map<String, String>> execute(String querySql) {

        ResultSet resultSet = null;
        Statement statement = null;
        Connection connection = null;
        Map<String, String> map;
        List<Map<String, String>> list = new ArrayList<>();
        try {
            connection = JdbcUtils.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(querySql);
            while (resultSet.next()) {
                map = new HashMap<>();
                ResultSetMetaData rsmd = resultSet.getMetaData();
                for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                    map.put(rsmd.getColumnName(i), resultSet.getString(i));
                }
                list.add(map);
            }

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {

            JdbcUtils.releaseResc(resultSet, statement, connection);
        }
        return list;
    }

    public static void main(String[] args) {
        String sql = "SELECT TABLE_NAME, TABLE_COMMENT FROM INFORMATION_SCHEMA.tables WHERE table_name = 'dictionary' and table_schema = (select database());";
//        String sql = "SELECT * FROM INFORMATION_SCHEMA.Columns WHERE table_name = 'dictionary' and table_schema = 'buckets-base';";

        List<Map<String, String>> list = execute(sql);
        list.forEach(System.out::println);
    }
}
