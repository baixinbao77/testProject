package jxd.bxb.test.All.MapperUtils;

import jxd.bxb.test.All.BaseUtils;
import jxd.bxb.test.All.EntityUtils.EntityUtil;
import jxd.bxb.test.All.EntityUtils.Path;
import jxd.bxb.test.utils.Constant;
import jxd.bxb.test.utils.FileUtils;
import jxd.bxb.test.utils.StringUtil;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

/**
 * @author BXBstart
 * @create 2022-06-26 20:17
 */
public class MappingUtils extends BaseUtils {


    public static void createMappingFile (String fileName , String mapperName , String poPath , String mapperPath , List<String> fieldList , String tableName , List<String> fieldTypeList) throws IOException {
        Logger logger = Logger.getLogger(MappingUtils.class.getName());
        if (StringUtil.isEmpty(fileName , mapperName)) {
            logger.info("信息不正确！");
        }
        StringBuilder result = new StringBuilder();
        String filePath = getWorkSpace();
        filePath = FileUtils.getFilePath(filePath, fileName, new Path(Constant.DAO , new Path(Constant.MAPPERING)));
        String mappingName = getMappingName(fileName);
        File file = FileUtils.createXmlFile(filePath, mappingName);
        mappingStart(result, mapperPath);
        resultMap(result, poPath, fieldList);
        insert(result, poPath, fieldList, tableName, fieldTypeList);
        End(result, Constant.MAPPER_FILE_PATH);
        FileUtils.writeFile(file ,result);
        logger.info("mapping文件生成成功！");
    }

    private static void insert (StringBuilder builder , String poPath , List<String> fieldList , String tableName , List<String> fieldTypeList) {
        String poName = poPath.substring(poPath.lastIndexOf(Constant.SPOT) + 1);
        EntityUtil.addLineFeed(builder, 1, Constant.BOX_DRAWINGS);
        builder.append(addBracket(addSpace(Constant.INSERT) + addSpace(addEqual(Constant.ID, Constant.SAVE + poName))
        + addEqual(Constant.PARAMETER_TYPE, poPath)) + Constant.LINE_FEED);
        EntityUtil.addLineFeed(builder, 2, Constant.BOX_DRAWINGS);
        builder.append(addSpace(Constant.INSERT) + addSpace(Constant.INTO) + addDoubleMarks(tableName) + Constant.LINE_FEED);
        EntityUtil.addLineFeed(builder, 2, Constant.BOX_DRAWINGS);
        builder.append(Constant.LEFT_PARENTHESIS);
        int j = 0;
        for (int i = 0; i < fieldList.size(); i++) {
            builder.append(addDoubleMarks(fieldList.get(i)) + Constant.SPACE);
            j++;
            if (i != fieldList.size() - 1) {
                addComma(builder);
            }
            if (j == 3) {
                EntityUtil.addLineFeed(builder, 1, Constant.LINE_FEED);
                EntityUtil.addLineFeed(builder, 2, Constant.BOX_DRAWINGS);
                j = 0;
            }
        }
        EntityUtil.addLineFeed(builder, 1, Constant.RIGHT_PARENTHESIS);
        EntityUtil.addLineFeed(builder, 1, Constant.LINE_FEED);
        EntityUtil.addLineFeed(builder, 2, Constant.BOX_DRAWINGS);
        EntityUtil.addLineFeed(builder, 1, Constant.VALUES);
        EntityUtil.addLineFeed(builder, 1, Constant.LEFT_PARENTHESIS);
        j=0;
        for (int i = 0; i < fieldList.size(); i++) {
            if (fieldTypeList.get(i).indexOf("date") > -1) {
                addDate(builder, poName, fieldList.get(i));
            } else if (fieldTypeList.get(i).indexOf("timestamp") > -1) {
                addTimeStamp(builder, poName, fieldList.get(i));
            } else {
                addInsert(builder, poName, fieldList.get(i));
            }
            if (i != fieldList.size() - 1) {
                addComma(builder);
            }
            j++;
            if (j == 3) {
                EntityUtil.addLineFeed(builder, 1, Constant.LINE_FEED);
                EntityUtil.addLineFeed(builder, 3, Constant.BOX_DRAWINGS);
                j = 0;
            }
        }
        builder.append(Constant.RIGHT_PARENTHESIS + Constant.SEMICOLON);
        EntityUtil.addLineFeed(builder, 1, Constant.LINE_FEED);
        EntityUtil.addLineFeed(builder, 1, Constant.BOX_DRAWINGS);
        End(builder, Constant.INSERT);
    }

        private static void resultMap (StringBuilder builder , String poPath , List<String> fieldList) {
        EntityUtil.addLineFeed(builder, 1, Constant.BOX_DRAWINGS);
        builder.append(Constant.LEFT_ANGLE_BRACKET)
                .append(Constant.RESULT_MAP)
                .append(Constant.SPACE)
                .append(Constant.ID)
                .append(Constant.EQUAL);
        addSpace(builder , Constant.QUERY_MAP);
        builder.append(Constant.SPACE)
                .append(Constant.TYPE)
                .append(Constant.EQUAL);
        addSpace(builder , poPath);
        builder.append(Constant.RIGHT_ANGLE_BRACKET).append(Constant.LINE_FEED);
        EntityUtil.addLineFeed(builder, 2, Constant.BOX_DRAWINGS);
        builder.append(addSpace(Constant.LEFT_ANGLE_BRACKET + Constant.ID) + Constant.PROPERTY + Constant.EQUAL +
                addSpace(addDoubleMarks(StringUtil.strToStr(fieldList.get(0)))) + Constant.COLUMN + Constant.EQUAL +
                addDoubleMarks(fieldList.get(0)) + Constant.RIGHT_ANGLE_BRACKET + Constant.LEFT_ANGLE_BRACKET + Constant.SLASH +
                Constant.ID + Constant.RIGHT_ANGLE_BRACKET + Constant.LINE_FEED);
        for (int i = 1; i < fieldList.size(); i++) {
            EntityUtil.addLineFeed(builder, 2, Constant.BOX_DRAWINGS);
            builder.append(addSpace(Constant.LEFT_ANGLE_BRACKET + Constant.RESULT) + Constant.PROPERTY + Constant.EQUAL +
                    addSpace(addDoubleMarks(StringUtil.strToStr(fieldList.get(i)))) + Constant.COLUMN + Constant.EQUAL +
                    addDoubleMarks(fieldList.get(i)) + Constant.RIGHT_ANGLE_BRACKET + Constant.LEFT_ANGLE_BRACKET + Constant.SLASH +
                    Constant.RESULT + Constant.RIGHT_ANGLE_BRACKET + Constant.LINE_FEED);
        }
        End(builder, Constant.RESULT_MAP);
    }

    private static void mappingStart (StringBuilder builder , String mapperPath) {
        builder.append(Constant.MAPPER_VERSION).append(Constant.LINE_FEED)
                .append(Constant.MAPPER_DOCTYPE).append(Constant.LINE_FEED)
                .append(Constant.LEFT_ANGLE_BRACKET)
                .append(Constant.MAPPER_FILE_PATH)
                .append(Constant.SPACE)
                .append(Constant.NAME_SPACE)
                .append(Constant.EQUAL);
        addSpace(builder , mapperPath);
        builder.append(Constant.RIGHT_ANGLE_BRACKET).append(Constant.LINE_FEED);
    }

    private static void End (StringBuilder builder , String str) {
        builder.append(Constant.LEFT_ANGLE_BRACKET)
                .append(Constant.SLASH)
                .append(str)
                .append(Constant.RIGHT_ANGLE_BRACKET);
        EntityUtil.addLineFeed(builder, 2, Constant.LINE_FEED);
    }
}
