package jxd.bxb.test.All.EntityUtils;

import jxd.bxb.test.All.BaseUtils;
import jxd.bxb.test.utils.Constant;
import jxd.bxb.test.utils.StringUtil;

import javax.crypto.interfaces.PBEKey;
import java.util.ArrayList;
import java.util.List;


/**
 * @author BXBstart
 * @create 2022-06-25 19:17
 */
public class EntityUtil {

    public static String getPoName(String fileName) {
        return StringUtil.setName(fileName) + Constant.PO_FILE_NAME;
    }
    public static String getDtoName(String fileName) {
        return StringUtil.setName(fileName) + Constant.DTO_FILE_NAME;
    }

    public static void addTableName(StringBuilder builder , String tanleName) {
        builder.append(Constant.AITE_ONE + Constant.TABLE_NAME + BaseUtils.addParenthesis(tanleName) + Constant.LINE_FEED);
    }

    public static StringBuilder setAndGet(List<String> fieldList , List<String> fieldTypeList) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < fieldList.size(); i++) {
            builder.append(getSetMethod(fieldList.get(i), fieldTypeList.get(i)))
                    .append(getGetMethod(fieldList.get(i), fieldTypeList.get(i)));
        }
        return builder;
    }

    public static StringBuilder getSetMethod(String fieldName , String typeName) {
        StringBuilder builder = new StringBuilder();
        builder.append(Constant.BOX_DRAWINGS + Constant.PUBLIC + Constant.SPACE + Constant.VOID + Constant.SPACE + Constant.SET_METHOD + StringUtil.setName(fieldName) + Constant.LEFT_PARENTHESIS + typeName + Constant.SPACE + fieldName + Constant.RIGHT_PARENTHESIS + Constant.LEFT_BRACE + Constant.LINE_FEED);
        addLineFeed(builder ,2 , Constant.BOX_DRAWINGS);
        builder.append(Constant.THIS + Constant.SPOT + fieldName + Constant.SPACE + Constant.EQUAL + Constant.SPACE + fieldName + Constant.SEMICOLON + Constant.SPACE + Constant.LINE_FEED + Constant.BOX_DRAWINGS + Constant.RIGHT_BRACE + Constant.LINE_FEED);
        return  builder;

    }

    public static String getGetMethod(String fieldName , String typeName) {
        return Constant.BOX_DRAWINGS + Constant.PUBLIC + Constant.SPACE + typeName + Constant.SPACE + Constant.GET_METHOD + StringUtil.setName(fieldName) + Constant.LEFT_PARENTHESIS + Constant.RIGHT_PARENTHESIS + Constant.LEFT_BRACE + Constant.LINE_FEED
                + Constant.BOX_DRAWINGS + Constant.BOX_DRAWINGS + Constant.RETURN +Constant.SPACE + fieldName + Constant.SEMICOLON + Constant.LINE_FEED + Constant.BOX_DRAWINGS + Constant.RIGHT_BRACE + Constant.LINE_FEED;
    }

    public static String getClassMethod(String className) {
        return Constant.PUBLIC + Constant.SPACE + Constant.CLASS + className + Constant.LEFT_BRACE + Constant.LINE_FEED;
    }

    public static String getDesc(String desc) {
        return desc == null ? "" : "// " + desc + Constant.LINE_FEED;
    }

    public static List<String> initField(List<String> list) {
        List<String> result = new ArrayList<>();
        for (String str : list) {
            result.add(StringUtil.strToStr(str));
        }
        return result;
    }

    public static List<String> initFieldDesc(List<String> list) {
        List<String> result = new ArrayList<>();
        for (String str : list) {
            result.add(getDesc(str));
        }
        return result;
    }

    public static String tableId(String str) {
        return Constant.AITE + Constant.TABLE_ID + Constant.LEFT_PARENTHESIS + Constant.DOUBLE_QUOTATION_MARKS + str + Constant.DOUBLE_QUOTATION_MARKS + Constant.RIGHT_PARENTHESIS + Constant.LINE_FEED;
    }
    public static String tableField(String str) {
        return Constant.AITE + Constant.TABLE_FIELD + Constant.LEFT_PARENTHESIS + Constant.DOUBLE_QUOTATION_MARKS + str + Constant.DOUBLE_QUOTATION_MARKS + Constant.RIGHT_PARENTHESIS + Constant.LINE_FEED;
    }

    /**
     *
     * @param fieldList
     * @param fieldTypeList
     * @param fieldDescList
     * @param PoOrDto true 的话生成Po  false 生成Dto
     * @return
     */
    public static StringBuilder fieldList(List<String> fieldList , List<String> fieldTypeList , List<String> fieldDescList , boolean PoOrDto) {
        StringBuilder result = new StringBuilder();
        if (PoOrDto) {
            for (int i = 1; i < fieldList.size(); i++) {
                result.append(tableField(fieldList.get(i)))
                        .append(field(StringUtil.strToStr(fieldList.get(i)), fieldTypeList.get(i), fieldDescList.get(i)));
            }
        } else {
            for (int i = 1; i < fieldList.size(); i++) {
                result.append(field(StringUtil.strToStr(fieldList.get(i)), fieldTypeList.get(i), fieldDescList.get(i)));
            }
        }
        return result;
    }
    public static StringBuilder field(String field , String fieldType , String fieldDesc) {
        StringBuilder result = new StringBuilder();
        if (fieldType.indexOf("Integer") > -1) {
            result.append(getPrivateInteger(field, fieldDesc));
        } else {
            result.append(getPrivateString(field, fieldDesc));
        }
        return result;
    }


    public static List<String> initFieldTypeList(List<String> list) {
        List<String> result = new ArrayList<>();
        if (StringUtil.isEmpty(list)) {
            return result;
        }
        for (int i = 0; i < list.size(); i++) {
            if(list.get(i).indexOf("int8") > -1) {
                result.add(Constant.LONG);
            } else if (list.get(i).indexOf("INT") > -1) {
                result.add(Constant.INTEGER);
            } else if(list.get(i).indexOf("int") > -1) {
                result.add(Constant.INTEGER);
            } else {
                result.add(Constant.STRING);
            }
        }
        return result;
    }

    public static String getPrivateString(String field , String desc) {
        return Constant.BOX_DRAWINGS + Constant.PRIVATE + Constant.SPACE + Constant.STRING + Constant.SPACE + field + Constant.SEMICOLON + desc ;
    }
    public static String getPrivateLong(String field , String desc) {
        return Constant.BOX_DRAWINGS + Constant.PRIVATE + Constant.SPACE + Constant.LONG + Constant.SPACE + field + Constant.SEMICOLON + desc ;
    }
    public static String getPrivateInteger(String field , String desc) {
        return Constant.BOX_DRAWINGS + Constant.PRIVATE + Constant.SPACE + Constant.INTEGER + Constant.SPACE + field + Constant.SEMICOLON + desc ;
    }

    public static StringBuilder addLineFeed(StringBuilder builder , int size , String addField) {
        for (int i = 0; i < size; i++) {
            builder.append(addField);
        }
        return builder;
    }
}
