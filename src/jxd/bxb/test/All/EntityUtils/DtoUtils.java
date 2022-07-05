package jxd.bxb.test.All.EntityUtils;

import jxd.bxb.test.All.BaseUtils;
import jxd.bxb.test.utils.Constant;
import jxd.bxb.test.utils.FileUtils;
import jxd.bxb.test.utils.StringUtil;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author BXBstart
 * @create 2022-06-26 11:40
 */
public class DtoUtils extends BaseUtils {


    public static void createDtoFile(List<String> fieldList , List<String> fieldTypeList , List<String> fieldDescList , String fileName , String DtoName) throws SQLException, IOException {
        Logger logger = Logger.getLogger(DtoUtils.class.getName());
        if (StringUtil.isEmpty(fieldList , fieldDescList ,fieldTypeList)) {
            logger.info("信息不正确");
        }
        List<String> initField = EntityUtil.initField(fieldList);
        String filePath = getWorkSpace();
        DtoName = EntityUtil.getDtoName(DtoName);
        filePath = FileUtils.getFilePath(filePath, fileName, new Path(Constant.MODEL , new Path(Constant.DTO_PATH)));
        File file = FileUtils.createJavaFile(filePath, DtoName);
        StringBuilder result = new StringBuilder();
        FileUtils.packageName(result, filePath);
        result.append(EntityUtil.getClassMethod(DtoName));
        result.append(EntityUtil.field(StringUtil.strToStr(fieldList.get(0)) , fieldTypeList.get(0), fieldDescList.get(0)));
        result.append(EntityUtil.fieldList(fieldList, fieldTypeList, fieldDescList , false));
        EntityUtil.addLineFeed(result, 1 , Constant.LINE_FEED);
        result.append(EntityUtil.setAndGet(initField, fieldTypeList));
        EntityUtil.addLineFeed(result, 2 , Constant.LINE_FEED);
        result.append(Constant.RIGHT_BRACE);
        logger.info("Dto文件生成成功！");
        FileUtils.writeFile(file , result);
    }
}
