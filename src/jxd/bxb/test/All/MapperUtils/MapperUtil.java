package jxd.bxb.test.All.MapperUtils;

import com.sun.corba.se.impl.interceptors.SlotTableStack;
import jxd.bxb.test.All.BaseUtils;
import jxd.bxb.test.All.EntityUtils.EntityUtil;
import jxd.bxb.test.All.EntityUtils.Path;
import jxd.bxb.test.utils.Constant;
import jxd.bxb.test.utils.FileUtils;
import jxd.bxb.test.utils.StringUtil;

import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;

/**
 * @author BXBstart
 * @create 2022-06-26 18:09
 */
public class MapperUtil extends BaseUtils {


    public static String createMapperFile (String fileName , String mapperName) throws IOException {
        Logger logger = Logger.getLogger(MapperUtil.class.getName());
        if (StringUtil.isEmpty(fileName , mapperName)) {
            logger.info("信息不正确");
        }
        StringBuilder result = new StringBuilder();
        String filePath = getWorkSpace();
        filePath = FileUtils.getFilePath(filePath, fileName, new Path(Constant.DAO));
        mapperName = getMapperName(fileName);
        File file = FileUtils.createJavaFile(filePath, mapperName);
        FileUtils.packageName(result, filePath);
        getMapper(result,  fileName);
        EntityUtil.addLineFeed(result, 8, Constant.LINE_FEED);
        addBrace(result);
        FileUtils.writeFile(file, result);
        logger.info("Mapper文件生成成功！");
        return FileUtils.getFilePackage(filePath) + Constant.SPOT+ mapperName;
    }



    public static void getMapper (StringBuilder builder , String fileName) {
        builder.append(addSpace(Constant.PUBLIC) + addSpace(Constant.INTERFACES) + addSpace(getMapperName(fileName)) +
                addSpace(Constant.EXTENDS) + addSpace(Constant.BASEMAPPER + Constant.LEFT_ANGLE_BRACKET + EntityUtil.getPoName(fileName) +
                Constant.RIGHT_ANGLE_BRACKET) + Constant.LEFT_BRACE + Constant.LINE_FEED);
    }


}
