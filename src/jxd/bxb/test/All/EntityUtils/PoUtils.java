package jxd.bxb.test.All.EntityUtils;

import jxd.bxb.test.All.BaseUtils;
import jxd.bxb.test.Connect.PgConnect;
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
 * @create 2022-06-25 19:16
 */
public class PoUtils extends BaseUtils {


    public static String createPoFile(List<String> fieldList , List<String> fieldTypeList , List<String> fieldDescList , String fileName , String poName , String tableName) throws SQLException, IOException {
        Logger logger = Logger.getLogger(PoUtils.class.getName());
        if (StringUtil.isEmpty(fieldList , fieldDescList ,fieldTypeList)) {
            logger.info("信息不正确");
        }
        List<String> initField = EntityUtil.initField(fieldList);
        String filePath = getWorkSpace();
        poName = EntityUtil.getPoName(poName);
        filePath = FileUtils.getFilePath(filePath, fileName, new Path(Constant.MODEL , new Path(Constant.PO_PATH)));
        File file = FileUtils.createJavaFile(filePath, poName);
        StringBuilder result = new StringBuilder();
        FileUtils.packageName(result, filePath);
        EntityUtil.addTableName(result, tableName);
        result.append(EntityUtil.getClassMethod(poName));
        result.append(EntityUtil.tableId(fieldList.get(0)));
        result.append(EntityUtil.field(StringUtil.strToStr(fieldList.get(0)) , fieldTypeList.get(0), fieldDescList.get(0)));
        result.append(EntityUtil.fieldList(fieldList, fieldTypeList, fieldDescList , true));
        EntityUtil.addLineFeed(result, 1 , Constant.LINE_FEED);
        result.append(EntityUtil.setAndGet(initField, fieldTypeList));
        EntityUtil.addLineFeed(result, 2 , Constant.LINE_FEED);
        result.append(Constant.RIGHT_BRACE);
        logger.info( "Po文件生成成功！");
        FileUtils.writeFile(file , result);
        return FileUtils.getFilePackage(filePath) + Constant.SPOT+ poName;
    }

}
