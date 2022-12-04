package jxd.bxb.test.All.ControllerUtils;

import jxd.bxb.test.All.BaseUtils;
import jxd.bxb.test.All.EntityUtils.Path;
import jxd.bxb.test.utils.Constant;
import jxd.bxb.test.utils.FileUtils;
import jxd.bxb.test.utils.StringUtil;

import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;

/**
 * @author baixinbao
 * @create 2022/6/27
 */
public class ControllerUtil extends BaseUtils {

    public static void createControllerFile (String fileName) throws IOException {
        Logger logger = Logger.getLogger(ControllerUtil.class.getName());
        if (StringUtil.isEmpty(fileName)) {
            logger.info("信息错误");
        }
        StringBuilder result = new StringBuilder();
        String filePath = getWorkSpace();
        String controllerName = getControllerName(fileName);
        filePath = FileUtils.getFilePath(filePath, fileName, new Path(Constant.CONTROLLER));
        File file = FileUtils.createJavaFile(filePath, controllerName);
        FileUtils.packageName(result, FileUtils.getFilePackage(filePath));



    }


}
