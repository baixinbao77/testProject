package jxd.bxb.test.All.ServiceUtils.Impl;

import jxd.bxb.test.All.BaseUtils;
import jxd.bxb.test.All.EntityUtils.EntityUtil;
import jxd.bxb.test.All.EntityUtils.Path;
import jxd.bxb.test.All.MapperUtils.MapperUtil;
import jxd.bxb.test.All.ServiceUtils.ServiceUtil.ServiceUtil;
import jxd.bxb.test.utils.Constant;
import jxd.bxb.test.utils.FileUtils;
import jxd.bxb.test.utils.StringUtil;

import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;

/**
 * @author BXBstart
 * @create 2022-06-26 15:55
 */
public class ImplUtil extends BaseUtils {
    public static void createImplFile (String fileName , String implName) throws IOException {
        Logger logger = Logger.getLogger(ImplUtil.class.getName());
        if (StringUtil.isEmpty(fileName , implName)) {
            logger.info("信息不正确");
        }
        StringBuilder result = new StringBuilder();
        String filePath = getWorkSpace();
        filePath = FileUtils.getFilePath(filePath, fileName, new Path(Constant.SERVICE_FILE_PATH , new Path(Constant.SERVICE_IMPL_PATH)));
        implName = getImplFileName(fileName);
        File file = FileUtils.createJavaFile(filePath, implName);
        FileUtils.packageName(result, filePath);
        addService(result);
        result.append(getImplName(fileName));
        EntityUtil.addLineFeed(result, 4, Constant.LINE_FEED);
        result.append(Constant.RIGHT_BRACE);
        FileUtils.writeFile(file , result);
        logger.info("Impl文件生成成功！");
    }

    public static String getImplName (String fileName) {
        return addSpace(Constant.PUBLIC) + addSpace(Constant.CLASS) + addSpace(getImplFileName(fileName)) + addSpace(Constant.EXTENDS) +
                addSpace(Constant.SERVICE_FILE_NAME + Constant.SERVICE_IMPL_FILE_NAME + Constant.LEFT_ANGLE_BRACKET +
                        MapperUtil.getMapperName(fileName) + Constant.COMMA + EntityUtil.getPoName(fileName) + Constant.RIGHT_ANGLE_BRACKET) +
                addSpace(Constant.IMPLEMENTS) + addSpace(getFileServiceName(fileName)) + Constant.LEFT_BRACE + Constant.LINE_FEED;
    }

    public static void addService(StringBuilder builder) {
        builder.append(Constant.AITE_ONE + Constant.SERVICE_FILE_NAME + Constant.LINE_FEED);
    }
}
