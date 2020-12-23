package buckets.framework.generator.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author buckets
 * @date 2020/12/22
 */
public class HumpToLine {

    public static void main(String[] args) throws Exception {
        String str = "a_bef_cer_dsss_";
        String str2 = lineToHump(str);
        String str3 = humpToLine(str2);
        System.out.println(str2);
        System.out.println(str3);
    }

    /*下划线转驼峰*/
    public static String lineToHump(String str){
        Pattern pattern = Pattern.compile("_(\\w){1}");
        Matcher matcher = pattern.matcher(str.toLowerCase());

        while(matcher.find()){
            String upperCase = matcher.group(1).toUpperCase();
            str = str.replace(matcher.group(), upperCase);
        }
        return str;

    }

    //*驼峰转下划线
    public static String humpToLine(String str){
        /**
         * $0 = matcher.group(0)
         */
        return str.replaceAll("[A-Z]", "_$0").toLowerCase();
    }

    public static String humpToLine2(String str){
        Pattern pattern = Pattern.compile("[A-Z]");
        Matcher matcher = pattern.matcher(str);
        StringBuffer sb = new StringBuffer();
        while(matcher.find()){
            matcher.appendReplacement(sb,"_"+matcher.group().toLowerCase());
        }
        matcher.appendTail(sb);
        return sb.toString();
    }
}
