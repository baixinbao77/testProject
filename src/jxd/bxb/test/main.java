package jxd.bxb.test;

import jxd.bxb.test.All.EntityUtils.DtoUtils;
import jxd.bxb.test.All.EntityUtils.EntityUtil;
import jxd.bxb.test.All.EntityUtils.PoUtils;
import jxd.bxb.test.All.MapperUtils.MapperUtil;
import jxd.bxb.test.All.MapperUtils.MappingUtils;
import jxd.bxb.test.All.ServiceUtils.Impl.ImplUtil;
import jxd.bxb.test.All.ServiceUtils.ServiceUtil.ServiceUtil;
import jxd.bxb.test.Connect.MyConnect;
import jxd.bxb.test.Connect.PgConnect;
import jxd.bxb.test.utils.StringUtil;
import org.junit.jupiter.api.Test;


import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;

/**
 * @author BXBstart
 * @create 2022-06-25 21:58
 */
public class main {



    public static void main(String[] args) throws SQLException, IOException {
        List<String> fieldList = EntityUtil.initField(MyConnect.getFieldList("employees"));
        List<String> fieldDescList = EntityUtil.initFieldDesc(MyConnect.getFieldDescList("employees"));
        List<String> fieldTypeList = EntityUtil.initFieldTypeList(MyConnect.getFieldTypeList("employees"));
        PoUtils.createPoFile(fieldList, fieldTypeList, fieldDescList, "employees", "employees" , "");
        DtoUtils.createDtoFile(fieldList, fieldTypeList, fieldDescList, "employees", "employees");
        System.out.println("文件生成完成！");
    }


    @Test
    public void test() throws Exception{
        Logger logger = Logger.getLogger(main.class.getName());
        Scanner scanner = new Scanner(System.in);
        String tableName = "t_task_complete";
        String fileName = "taskComplete";
        String name = "TaskComplete";
        List<String> fieldList = PgConnect.getFieldList(tableName);
        List<String> fieldDescList = EntityUtil.initFieldDesc(PgConnect.getFieldDescList(tableName));
        List<String> fieldTypeListOld = PgConnect.getFieldTypeList(tableName);
        List<String> fieldTypeListNew = EntityUtil.initFieldTypeList(fieldTypeListOld);
        if (StringUtil.isEmpty(fieldList , fieldTypeListOld , fieldDescList)) {
            System.out.println("请输入正确的表名！");
        }
        createFile(fieldList , fieldTypeListNew , fieldDescList ,fileName , name , tableName , fieldTypeListOld);
        logger.info("文件生成成功");
    }

    private void createFile(List<String> fieldList , List<String> fieldTypeListNew , List<String> fieldDescList , String fileName , String name , String tableName , List<String> fieldTypeListOld) throws Exception {
        String poPath = PoUtils.createPoFile(fieldList, fieldTypeListNew, fieldDescList, fileName, name , tableName);
        DtoUtils.createDtoFile(fieldList, fieldTypeListNew, fieldDescList, fileName, name);
        ServiceUtil.createServiceFile(fileName, name);
        ImplUtil.createImplFile(fileName, name);
        String mapperPath = MapperUtil.createMapperFile(fileName, name);
        MappingUtils.createMappingFile(fileName , name  , poPath ,  mapperPath ,fieldList , tableName , fieldTypeListOld);
    }

}
