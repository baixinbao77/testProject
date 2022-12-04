package jxd.bxb.test.All.ServiceUtils.ServiceUtil;


import jxd.bxb.test.All.BaseUtils;
import jxd.bxb.test.All.EntityUtils.EntityUtil;
import jxd.bxb.test.All.EntityUtils.Path;
import jxd.bxb.test.utils.Constant;
import jxd.bxb.test.utils.FileUtils;
import jxd.bxb.test.utils.StringUtil;

import java.io.File;
import java.util.logging.Logger;

/**
 * @author BXBstart
 * @create 2022-06-25 19:16
 */
public class ServiceUtil extends BaseUtils {


    public static void createServiceFile(String fileName , String serviceName) throws Exception {
        Logger logger = Logger.getLogger(ServiceUtil.class.getName());
        if (StringUtil.isEmpty(fileName , serviceName)) {
            logger.info("信息不正确");
        }
        StringBuilder result = new StringBuilder();
        String filePath = getWorkSpace();
        filePath = FileUtils.getFilePath(filePath, fileName, new Path(Constant.SERVICE_FILE_PATH));
        serviceName = getFileServiceName(fileName);
        File file = FileUtils.createJavaFile(filePath, serviceName);
        FileUtils.packageName(result, filePath);
        result.append(getServiceName(fileName));
        EntityUtil.addLineFeed(result, 4, Constant.LINE_FEED);
        addBrace(result);
        FileUtils.writeFile(file , result);
        logger.info("Service文件生成成功！");
    }

    public static String getServiceName (String fileName) {
        return addSpace(Constant.PUBLIC) + addSpace(Constant.INTERFACES) + addSpace(getFileServiceName(fileName)) + addSpace(Constant.EXTENDS) + addSpace(Constant.ISSERVICE + Constant.LEFT_ANGLE_BRACKET + EntityUtil.getPoName(fileName) + Constant.RIGHT_ANGLE_BRACKET) + Constant.LEFT_BRACE + Constant.LINE_FEED;
    }
}
