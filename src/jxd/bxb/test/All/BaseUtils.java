package jxd.bxb.test.All;

import jxd.bxb.test.All.EntityUtils.EntityUtil;
import jxd.bxb.test.All.MapperUtils.MapperUtil;
import jxd.bxb.test.utils.Constant;
import jxd.bxb.test.utils.StringUtil;

/**
 * @author BXBstart
 * @create 2022-06-26 20:06
 */
public class BaseUtils {

    public static void addBrace (StringBuilder builder) {
        builder.append(Constant.RIGHT_BRACE);
    }

    public static String addSpace (String str) {
        return str + Constant.SPACE;
    }

    public static String getFileServiceName (String fileName) {
        return StringUtil.setName(fileName) + Constant.SERVICE_FILE_NAME;
    }

    public static String getImplFileName(String fileName) {
        return StringUtil.setName(fileName) + Constant.SERVICE_FILE_NAME + Constant.SERVICE_IMPL_FILE_NAME;
    }

    public static String getMapperName(String fileName) {
        return StringUtil.setName(fileName) + Constant.MAPPER_FILE_NAME;
    }

    public static String getMappingName(String fileName) {
        return StringUtil.strToStr(fileName) + Constant.MAPPER_FILE_NAME;
    }
    public static String getControllerName(String fileName) {
        return StringUtil.setName(fileName) + Constant.CONTROLLER_FILE_NAME;
    }


    public static String getWorkSpace() {
        return StringUtil.getPropertiesMap(Constant.CREATE_FILE_PATH).get(Constant.WORKSPACE).toString();
    }

    public static void addSpace (StringBuilder builder , String str) {
        builder.append(Constant.DOUBLE_QUOTATION_MARKS).append(str).append(Constant.DOUBLE_QUOTATION_MARKS);
    }

    public static String addDoubleMarks (String str) {
        return Constant.DOUBLE_QUOTATION_MARKS + str + Constant.DOUBLE_QUOTATION_MARKS;
    }

    public static String addBracket (String str) {
         return Constant.LEFT_ANGLE_BRACKET + str + Constant.RIGHT_ANGLE_BRACKET;
    }

    public static String addEqual (String start , String end) {
        return start + Constant.EQUAL + addDoubleMarks(end);
    }
    public static String addParenthesis (String str) {
        return Constant.LEFT_PARENTHESIS + addDoubleMarks(str) + Constant.RIGHT_PARENTHESIS;
    }

    public static void addDate(StringBuilder builder , String poName , String fieldName) {
        builder.append(Constant.TO + Constant.UNDERLINE + Constant.DATE + Constant.LEFT_PARENTHESIS + Constant.WELL_NUMBER +
                Constant.LEFT_BRACE + poName + Constant.SPOT + StringUtil.strToStr(fieldName) + Constant.RIGHT_BRACE +
                Constant.COMMA + Constant.YEAR_MONTH_DAY + Constant.RIGHT_PARENTHESIS);
    }

    public static void addTimeStamp(StringBuilder builder , String poName , String fieldName) {
        builder.append(Constant.TO + Constant.UNDERLINE + Constant.DATE + Constant.LEFT_PARENTHESIS + Constant.WELL_NUMBER +
                Constant.LEFT_BRACE + poName + Constant.SPOT + StringUtil.strToStr(fieldName) + Constant.RIGHT_BRACE +
                Constant.COMMA + Constant.YMDHMS + Constant.RIGHT_PARENTHESIS);
    }

    public static void addInsert(StringBuilder builder , String poName , String fieldName) {
        builder.append(Constant.WELL_NUMBER + Constant.LEFT_BRACE + poName + Constant.SPOT + StringUtil.strToStr(fieldName) + Constant.RIGHT_BRACE);
    }

    public static void addComma(StringBuilder builder) {
        builder.append(Constant.COMMA);
    }
}
