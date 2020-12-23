package buckets.framework.generator.module;

import java.util.List;

/**
 * @author buckets
 * @email oujiayong0531@163.com
 * @date 2020/12/21
 */
public class CommonInfo {

    /**
     * 统一包名前缀
     */
    private String parentPackageName;

    /**
     * 类名
     */
    private String className;

    /**
     * 实例化后名称
     */
    private String beanName;

    /**
     * 是否使用Lombok插件
     */
    private boolean useLombok;

    /**
     * 是否使用Lombok插件
     */
    private boolean useSwagger;

    /**
     * 作者
     */
    private String author;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 生成时间
     */
    private String date;

    /**
     * 接口类mapping地址
     */
    private String controllerMappingPath;

    public String getParentPackageName() {
        return parentPackageName;
    }

    public void setParentPackageName(String parentPackageName) {
        this.parentPackageName = parentPackageName;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public boolean isUseLombok() {
        return useLombok;
    }

    public void setUseLombok(boolean useLombok) {
        this.useLombok = useLombok;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getBeanName() {
        return beanName;
    }

    public void setBeanName(String beanName) {
        this.beanName = beanName;
    }

    public String getControllerMappingPath() {
        return controllerMappingPath;
    }

    public void setControllerMappingPath(String controllerMappingPath) {
        this.controllerMappingPath = controllerMappingPath;
    }

    public boolean isUseSwagger() {
        return useSwagger;
    }

    public void setUseSwagger(boolean useSwagger) {
        this.useSwagger = useSwagger;
    }
}
