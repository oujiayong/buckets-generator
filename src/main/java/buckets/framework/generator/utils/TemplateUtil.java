package buckets.framework.generator.utils;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Map;

/**
 * 模板生成工具类
 * @author buckets
 * @date 2020/12/21
 */
public class TemplateUtil {
    /**
     *
     * @param ftl FTL文件地址
     * @param params 填充参数
     * @return
     * @throws IOException
     * @throws TemplateException
     */
    public static String generateTemplate(String ftl, Map<String, Object> params)
            throws IOException, TemplateException {
        Configuration configuration = new Configuration();
        configuration.setClassForTemplateLoading(TemplateUtil.class, "/");
        configuration.setDefaultEncoding("UTF-8");
        configuration.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);

        Template template = configuration.getTemplate(ftl);
        Writer stringWriter = new StringWriter();

        template.process(params, stringWriter);

        return stringWriter.toString();
    }
}