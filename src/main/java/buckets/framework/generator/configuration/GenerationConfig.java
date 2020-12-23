package buckets.framework.generator.configuration;

import buckets.framework.generator.utils.JdbcUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author buckets
 * @date 2020/12/22
 */
public class GenerationConfig {

    public static String baseDir;
    public static String author;
    public static String email;
    public static String parentPkgName;
    public static String tableNames;
    public static String tablePrefix;
    public static String tableSuffix;
    public static boolean useLombok;
    public static boolean useSwagger;

    static {
        try {
            InputStream bundle = JdbcUtils.class.getClassLoader().getResourceAsStream("generator.properties");
            Properties properties = new Properties();
            properties.load(bundle);
            author = properties.getProperty("generator.author");
            email = properties.getProperty("generator.email");
            parentPkgName = properties.getProperty("generator.parentPackageName");
            tableNames = properties.getProperty("generator.tableNames");
            tablePrefix = properties.getProperty("generator.table.prefix");
            tableSuffix = properties.getProperty("generator.table.suffix");
            useLombok = "true".equals(properties.getProperty("generator.useLombok"));
            useSwagger = "false".equals(properties.getProperty("generator.useSwagger")) ? false : true;
            baseDir = System.getProperty("user.dir");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
