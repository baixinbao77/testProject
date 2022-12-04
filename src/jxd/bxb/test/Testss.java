package jxd.bxb.test;


import jxd.bxb.test.All.EntityUtils.EntityUtil;
import jxd.bxb.test.All.EntityUtils.PoUtils;
import jxd.bxb.test.Connect.Conn.DbConnect;
import jxd.bxb.test.Connect.Conn.PgConnect;
import jxd.bxb.test.Connect.Controller.BaseController;
import jxd.bxb.test.Connect.Conn.MyConnect;
import jxd.bxb.test.Connect.Controller.SQL;
import jxd.bxb.test.Connect.inter.BaseMapper;
import jxd.bxb.test.result.employees.model.po.EmployeesPo;
import jxd.bxb.test.utils.BeanUtils;
import jxd.bxb.test.utils.StringUtil;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author baixinbao
 * @create 2022/6/27
 */
public class Testss{

    public class ListNode {
        int val;
        ListNode next;


        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    class Solution {
        public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
            StringBuffer s1 = new StringBuffer();
            StringBuffer s2 = new StringBuffer();
            ListNode listNode = new ListNode();
            ListNode ln = new ListNode();
            while (l1 != null) {
                s1.append(l1.val);
                l1 = l1.next;

            }
            while (l2 != null) {
                s2.append(l2.val);
                l2 = l2.next;
            }
            String i1 = String.valueOf(Integer.valueOf(s1.reverse().toString()) + Integer.valueOf(s2.reverse().toString()));
            String[] split = i1.split("");
            int i = split.length - 1;
            while(i >= 0){//从头指针开始遍历
                listNode.val = Integer.valueOf(split[i]);
                i--;
                for (int k = 0; k < split.length - i; k++) {
                    listNode.next = listNode;
                }

            //将尾指针和后面连接起来
        }
            return listNode;
        }
    }

@Test
public void testsh(){
        Integer a = 999;
    System.out.println(a.toString().length());
}



    @Test
    public void test () {
        EmployeesPo employeesPo = new EmployeesPo();
        employeesPo.setEmployeeid(1000);
        employeesPo.setEmployeename("zgk");
        employeesPo.setAge(10);
        employeesPo.setIdcard("saasa ");
        employeesPo.setEmployeeid(1000);
        employeesPo.setPassword("saas");
        employeesPo.setPhonenumber("saas");
        employeesPo.setSalary(12);
        employeesPo.setHiredate("2022-02-02");


    }

    @Test
    public void testsas () {
        SQL<EmployeesPo> sql = new SQL<>(EmployeesPo.class);
        SQL id = sql.select("*").select("ID").select("ENPLORMANE","ID");
        System.out.println(id.getSelect());
    }

    @Test
    public void testyyy() throws SQLException, IOException {
        String tableName = "employees";
        String fileName = "employees";
        String name = "employees";
        List<String> fieldList = MyConnect.getFieldList(tableName);
        List<String> fieldDescList = EntityUtil.initFieldDesc(MyConnect.getFieldDescList(tableName));
        List<String> fieldTypeListOld = MyConnect.getFieldTypeList(tableName);
        List<String> fieldTypeListNew = EntityUtil.initFieldTypeList(fieldTypeListOld);
        String poPath = PoUtils.createPoFile(fieldList, fieldTypeListNew, fieldDescList, fileName, name , tableName);
    }



    @Test
    public void testhhsahja() {
        try {
            List<String> fieldList = DbConnect.getFieldList("PSMS_BASE_AbnormalEarlyInfo");
        } catch (SQLException e) {


        }
    }

    @Test
    public void tetdggd (){
        List<EmployeesPo> list = getList();
        EmployeesPo[] li = new EmployeesPo[list.size()];
        list.toArray(li);
        System.out.println(li);
        System.out.println(Arrays.toString(li));
        Arrays.stream(li).map(employeesPo -> employeesPo.getEmployeename()).forEach(System.out::println);

        Iterator<EmployeesPo> iterator = list.iterator();
        while (iterator.hasNext()) {

        }
        LinkedList linkedList = new LinkedList();


    }

    public List<EmployeesPo> getList() {
        List<EmployeesPo> list = new ArrayList<>();
        EmployeesPo employeesPo = new EmployeesPo();
        employeesPo.setEmployeeid(1000);
        employeesPo.setEmployeename("zgk");
        employeesPo.setAge(10);
        employeesPo.setIdcard("saasa ");
        employeesPo.setEmployeeid(1000);
        employeesPo.setPassword("saas");
        employeesPo.setPhonenumber("saas");
        employeesPo.setSalary(13);
        employeesPo.setHiredate("2022-02-02");

        EmployeesPo employeesPo1 = new EmployeesPo();
        employeesPo1.setEmployeeid(1001);
        employeesPo1.setEmployeename("lgh");
        employeesPo1.setAge(10);
        employeesPo1.setIdcard("saasa ");
        employeesPo1.setEmployeeid(1000);
        employeesPo1.setPassword("saas");
        employeesPo1.setPhonenumber("saas");
        employeesPo1.setSalary(12);
        employeesPo1.setHiredate("2022-02-02");
        list.add(employeesPo);
        list.add(employeesPo1);
        return list;
    }

    @Test
    public void testtts() throws SQLException {
        List<String> fieldList = PgConnect.getFieldList("t_process_interlock_account");
        for (String s : fieldList) {
            System.out.println(StringUtil.strToStr(s));
        }
    }

    @Test
    public void testtasts() throws SQLException {
        List<String> fieldList = PgConnect.getFieldDescList("t_process_interlock_account");
        for (String s : fieldList) {
            System.out.println(s);
        }
    }

    @Test
    public void test21() {
        String str = "private String ID;\n" +
                "    private String Enterprise;\n" +
                "    private String Department;\n" +
                "    private String EquipmentName;\n" +
                "    private String AlarmStartTime;\n" +
                "    //gaoxj_2022-06-29\n" +
                "    private String ShowAlarmStartTime;\n" +
                "    private String AlarmEndTime;\n" +
                "    private String ShowAlarmEndTime;\n" +
                "    private int AlarmCount;\n" +
                "    private int UrgentCount;\n" +
                "    private int MajorCount;\n" +
                "    private int NormalCount;\n" +
                "    private BigDecimal AlarmHourAvg;\n" +
                "    private BigDecimal Alarm10MinuteMax;\n" +
                "    private BigDecimal AlarmRatio;\n" +
                "    private BigDecimal ACKRatio;\n" +
                "    private BigDecimal TimelyACKRatio;\n" +
                "    private int AlarmAllTime;\n" +
                "    private BigDecimal Alarm24HourSustain;\n" +
                "    private BigDecimal AlarmHourAvgScore;\n" +
                "    private BigDecimal Alarm10MinuteMaxScore;\n" +
                "    private BigDecimal Alarm24HourSustainScore;\n" +
                "    private BigDecimal AlarmScore;\n" +
                "    private String TagName;\n" +
                "    private String TagDesc;\n" +
                "    private int SustainedTime;\n" +
                "    private String AlarmProperty;\n" +
                "    private int PropertyCount;\n" +
                "    private String AlarmState;\n" +
                "    private int StateCount;\n" +
                "    private int Sustained5s;\n" +
                "    private int Sustained60s;\n" +
                "    private int Sustained5m;\n" +
                "    private int Sustained6h;\n" +
                "    private int Sustained24h;\n" +
                "    private int Sustained1d;\n" +
                "    private int NoConfirmShortCount;\n" +
                "    private int NoConfirmH10L30Count;\n" +
                "    private int NoConfirmH30L60Count;\n" +
                "    private int NoConfirmCount;\n" +
                "    private int ConfirmShortCount;\n" +
                "    private int ConfirmH10L30Count;\n" +
                "    private int ConfirmH30L60Count;\n" +
                "    private int ConfirmCount;\n" +
                "    private int AlarmItemCount;\n" +
                "    private int Flag;\n" +
                "    private String CountTagName;\n" +
                "    private String CountTagDesc;\n" +
                "    private int CountAlarmCount;\n" +
                "    private String CountAlarmState;\n" +
                "    private BigDecimal CountAlarmRatio;\n" +
                "    private String SustainedTagName;\n" +
                "    private String SustainedTagDesc;\n" +
                "    private String SustainedAlarmState;\n" +
                "    private int SustainedAlarmSustained;\n" +
                "    private int NotTimelyCount;\n" +
                "    private int ReportType;\n" +
                "    //报告类型\n" +
                "    private int? StateType;\n" +
                "    private String CountCause;\n" +
                "    private String CountMeasure;\n" +
                "    private String SustainedCause;\n" +
                "    private String SustainedMeasure;\n" +
                "    //gaoxj_2022_06_10\n" +
                "    private int AlarmTime1;\n" +
                "    private int AlarmTime2;\n" +
                "    private int ConfirmTime;\n" +
                "    //AlarmConfirmTime\n" +
                "    private String AlarmConfirmTime;\n" +
                "    private String ShowAlarmConfirmTime;\n" +
                "    private String AlarmType;\n" +
                "    private BigDecimal AlarmRate;\n" +
                "    private int AlarmCountRT;\n" +
                "    private int UrgentCountRT;\n" +
                "    private int MajorCountRT;\n" +
                "    private int NormalCountRT;\n" +
                "    private int AlarmTime1RT;\n" +
                "    private int AlarmTime2RT;\n" +
                "    private String GroupName;\n" +
                "    private int GroupCount;\n" +
                "    private int IsSIS;\n" +
                "    private int IsCard;\n" +
                "    private int IsGropCull;\n" +
                "    private String Cause;\n" +
                "    private String Notes;\n" +
                "    private String ApplyPersonnel;\n" +
                "    private String ApplyTime;\n" +
                "    private String ShowApplyTime;\n" +
                "    private BigDecimal AlarmEndRate;";

//        PoField(str);
        fieldToResultMap(str);
    }


    @Test
    public void PoField(String str) {
        String[] split = str.split(";");
        for (String s : split) {
            System.out.print(s.trim().substring(0 , StringUtil.getFieldPosition(s)));
            System.out.println(StringUtil.strToPsms(StringUtil.checkField(s)) + ";");
        }
    }

    @Test
    public void fieldToResultMap(String str) {
        List<String> fieldList = Arrays.stream(str.split(";")).map(s -> s = StringUtil.checkField(s)).collect(Collectors.toList());

        System.out.println("<id property=\"" + StringUtil.strToPsms(fieldList.get(0)) + "\" column=\"" + fieldList.get(0) + "\"></id>");
        for (int i = 1; i < fieldList.size(); i++) {
            System.out.println("<result property=\"" + StringUtil.strToPsms(fieldList.get(i)) + "\" column=\"" + fieldList.get(i) + "\"></result>");
        }
    }

    @Test
    public void shahsa() {
        String str = "1|原料波动";
        System.out.println(str.split("\\|").length);
    }

    @Test
    public void samm() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
        String startTime = "2021-0s2-23";
        Date start = new Date();
        try {
             start = sdf.parse(startTime);
        } catch (ParseException e) {
            try {
                start = sdf1.parse(startTime);
            } catch (ParseException ex) {
                ex.printStackTrace();
            }
        }
        System.out.println(sdf.format(start));
    }

    @Test
    public void Po() throws SQLException {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("TableName：");
            String tableName = scanner.nextLine();
            if (StringUtil.isEmpty(tableName)) {
                continue;
            }
            List<String> fieldList = DbConnect.getFieldList(tableName);
            List<String> fieldTypeList = DbConnect.getFieldTypeList(tableName);
            List<String> fieldDescList = DbConnect.getFieldDescList(tableName);
            if (StringUtil.isEmpty(fieldList ,fieldTypeList)) {
                continue;
            }
            fieldDescList = new ArrayList<>(fieldList.size());
            System.out.println("@TableId(\"" + fieldList.get(0) + "\")");
            System.out.println("private String " + StringUtil.strToStr(fieldList.get(0)) + ";" );
            for (int i = 1; i < fieldList.size(); i++) {
                System.out.println("@TableField(\"" + fieldList.get(i) + "\")");
                if (fieldTypeList.get(i).equals("int8")) {
                    System.out.println("private Long " + StringUtil.strToStr(fieldList.get(i)) + ";" );
                } else if (fieldTypeList.get(i).indexOf("int") > -1) {
                    System.out.println("private Integer " + StringUtil.strToStr(fieldList.get(i)) + ";" );
                } else {
                    System.out.println("private String " + StringUtil.strToStr(fieldList.get(i)) + ";");
                }
            }
        }
    }

    public static List<String> getsaList() {
        List<String> list = new ArrayList<>();
        list.add("章");
        list.add("后");
        list.add("狗");
        list.add("轲");
        return list;
    }

    @Test
    public void testst() {
        String str = "[ProcessCode],[ProcessHisID],[ProcessStepIndex],[AuditingPersonnel],[AuditingTime],[AuditingRemark],[AuditingState],[ProcessIsStop]";
        String[] split = str.split(",");
        for (String s : split) {
            String substring = s.substring(s.indexOf("[") + 1, s.lastIndexOf("]"));
            System.out.println("private" + " String " +StringUtil.strToStr(substring));
        }
    }

    @Test
    public void sbbas() {
        String str = "Enterprise|Department|EquipmentName|TagName|TagDesc|AlarmState|AlarmCount|AlarmRatio|AlarmChatterCount|AlarmChatterRatio|";
        String[] split = str.split("\\|");
        for (String s : split) {
            System.out.println("private String " +  StringUtil.strToStr(s.trim()) + ";");
        }
        resd(str);

    }

    @Test
    public void resd(String str) {
        List<String> split = Arrays.stream(str.split("\\|")).map(s -> s.trim()).collect(Collectors.toList());
        soutResultMap(split);
    }

    private void soutResultMap(List<String> fieldList) {
        System.out.println("<id property=\"" + StringUtil.strToStr(fieldList.get(0)) + "\" column=\"" + fieldList.get(0) + "\"></id>");
        for (int i = 1; i < fieldList.size(); i++) {
            System.out.println("<result property=\"" + StringUtil.strToStr(fieldList.get(i)) + "\" column=\"" + fieldList.get(i) + "\"></result>");
        }
    }


    @Test
    public void sahha() {
        String str = "private Integer taskStatus = Integer.valueOf(-1);   // 判断任务结束时间是不是为空，1 空 待办  ， 2 不为空 已办 区分待办已办\n" +
                "    private String enterpriseId;\n" +
                "    private String taskName;\n" +
                "    private String startTime;\n" +
                "    private String endTime;\n" +
                "    private String receiveUserId;\n" +
                "    private String receiveDeptId;\n" +
                "    private Integer pageCurrent = Integer.valueOf(-1);//当前页数\n" +
                "    private Integer pageSize = Integer.valueOf(-1);//每页条数\n" +
                "    private Integer pageNo = Integer.valueOf(-1);\n";
        for (String s : str.split(";")) {
            System.out.println(StringUtil.checkField(s));

        }
    }

    @Test
    public void tetetye() {
        String strart = "2015-08-01 08:00:00";
        String end = "2022-08-10 20:00:00";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
        System.out.println(strart.substring(0 , 16));
        try {
            Date startTime = sdf.parse(strart);
            Date endTime = sdf.parse(end);

            System.out.println(sdf1.format(startTime));
            System.out.println( sdf1.format(endTime).length());
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void sysay() {
        String str = "qwertyuiop";
        System.out.println(str.substring(0 , str.indexOf("y")));
    }


    @Test
    public void sahsa() {
        String str = " if (ones.get(oneindex) == j && oneindex < ones.size() - 1) {\n" +
                "                sheet.addMergedRegion(new CellRangeAddress(ones.get(oneindex)+ 3, ones.get(oneindex + 1) + 2, 1, 1));\n" +
                "                cell = newrow.createCell(1);\n" +
                "                cell.CellStyle = style;\n" +
                "                cell.setCellValue(dt[j].Department);\n" +
                "                oneindex++;\n" +
                "            } else {\n" +
                "                cell = newrow.createCell(1);\n" +
                "                cell.CellStyle = style;\n" +
                "            }\n" +
                "            //装置\n" +
                "            if (twos[twoindex] == j && twoindex < twos.Count - 1) {\n" +
                "                sheet.addMergedRegion(new CellRangeAddress(twos[twoindex] + 3, twos[twoindex + 1] + 2, 2, 2));\n" +
                "                cell = newrow.createCell(2);\n" +
                "                cell.CellStyle = style;\n" +
                "                cell.setCellValue(dt[j].EquipmentName);\n" +
                "                flag = true;\n" +
                "            } else {\n" +
                "                cell = newrow.createCell(2);\n" +
                "                cell.CellStyle = style;\n" +
                "            }\n" +
                "            //报警次数\n" +
                "            if (twos[twoindex] == j && twoindex < twos.Count - 1) {\n" +
                "                sheet.addMergedRegion(new CellRangeAddress(twos[twoindex] + 3, twos[twoindex + 1] + 2, 3, 3));\n" +
                "                cell = newrow.createCell(3);\n" +
                "                cell.CellStyle = style;\n" +
                "                cell.setCellValue(dt[j].AlarmCount);\n" +
                "            } else {\n" +
                "                cell = newrow.createCell(3);\n" +
                "                cell.CellStyle = style;\n" +
                "            }\n" +
                "            //时平均报警数\n" +
                "            if (twos[twoindex] == j && twoindex < twos.Count - 1) {\n" +
                "                sheet.addMergedRegion(new CellRangeAddress(twos[twoindex] + 3, twos[twoindex + 1] + 2, 4, 4));\n" +
                "                cell = newrow.createCell(4);\n" +
                "                if (dt[j].AlarmHourAvg == 0)\n" +
                "                {\n" +
                "                    cell.CellStyle = style;\n" +
                "                }\n" +
                "                else if (dt[j].AlarmHourAvg < 6)\n" +
                "                    cell.CellStyle = stylegreen;\n" +
                "                else if (dt[j].AlarmHourAvg < 10)\n" +
                "                    cell.CellStyle = styleyellow;\n" +
                "                else\n" +
                "                    cell.CellStyle = stylered;\n" +
                "                cell.setCellValue(dt[j].AlarmHourAvg.ToString());\n" +
                "            } else {\n" +
                "                cell = newrow.createCell(4);\n" +
                "                if (dt[j].AlarmHourAvg == 0)\n" +
                "                {\n" +
                "                    cell.CellStyle = style;\n" +
                "                }\n" +
                "                else if (dt[j].AlarmHourAvg < 6)\n" +
                "                    cell.CellStyle = stylegreen;\n" +
                "                else if (dt[j].AlarmHourAvg < 10)\n" +
                "                    cell.CellStyle = styleyellow;\n" +
                "                else\n" +
                "                    cell.CellStyle = stylered;\n" +
                "            }\n" +
                "            //位号\n" +
                "            cell = newrow.createCell(5);\n" +
                "            style.Alignment = HorizontalAlignment.Left;\n" +
                "            cell.CellStyle = style;\n" +
                "            style.Alignment = HorizontalAlignment.Center;\n" +
                "            cell.setCellValue(dt[j].CountTagName);\n" +
                "            //描述\n" +
                "            cell = newrow.createCell(6);\n" +
                "            cell.CellStyle = stylel;\n" +
                "            cell.setCellValue(dt[j].CountTagDesc);\n" +
                "            //次数\n" +
                "            cell = newrow.createCell(7);\n" +
                "            cell.CellStyle = style;\n" +
                "            cell.setCellValue(dt[j].CountAlarmCount);\n" +
                "            //状态\n" +
                "            cell = newrow.createCell(8);\n" +
                "            cell.CellStyle = stylel;\n" +
                "            cell.setCellValue(dt[j].CountAlarmState);\n" +
                "            //占比\n" +
                "            cell = newrow.createCell(9);\n" +
                "            cell.CellStyle = style;\n" +
                "            cell.setCellValue(dt[j].CountAlarmRatio.ToString());\n" +
                "            //位号\n" +
                "            cell = newrow.createCell(10);\n" +
                "            cell.CellStyle = stylel;\n" +
                "            cell.setCellValue(dt[j].SustainedTagName);\n" +
                "            //描述\n" +
                "            cell = newrow.createCell(11);\n" +
                "            cell.CellStyle = stylel;\n" +
                "            cell.setCellValue(dt[j].SustainedTagDesc);\n" +
                "            //时长\n" +
                "            cell = newrow.createCell(12);\n" +
                "            cell.CellStyle = style;\n" +
                "            cell.setCellValue(dt[j].SustainedAlarmSustained);\n" +
                "            //状态\n" +
                "            cell = newrow.createCell(13);\n" +
                "            cell.CellStyle = style;\n" +
                "            cell.setCellValue(dt[j].SustainedAlarmState);\n" +
                "            //未及时\n" +
                "            if (twos[twoindex] == j && twoindex < twos.Count - 1) {\n" +
                "                sheet.addMergedRegion(new CellRangeAddress(twos[twoindex] + 3, twos[twoindex + 1] + 2, 14, 14));\n" +
                "                cell = newrow.createCell(14);\n" +
                "                cell.CellStyle = style;\n" +
                "                cell.setCellValue(dt[j].NotTimelyCount);\n" +
                "            } else {\n" +
                "                cell = newrow.createCell(14);\n" +
                "                cell.CellStyle = style;\n" +
                "            }\n" +
                "            //响应率\n" +
                "            if (twos[twoindex] == j && twoindex < twos.Count - 1) {\n" +
                "                sheet.addMergedRegion(new CellRangeAddress(twos[twoindex] + 3, twos[twoindex + 1] + 2, 15, 15));\n" +
                "                cell = newrow.createCell(15);\n" +
                "                cell.CellStyle = style;\n" +
                "                cell.setCellValue(dt[j].TimelyACKRatio.ToString());\n" +
                "                if (dt[j].TimelyACKRatio > 20)\n" +
                "                    cell.CellStyle = stylered;\n" +
                "            } else {\n" +
                "                cell = newrow.createCell(15);\n" +
                "                cell.CellStyle = style;\n" +
                "                if (dt[j].TimelyACKRatio > 20)\n" +
                "                    cell.CellStyle = stylered;\n" +
                "            }\n" +
                "            //备注\n" +
                "            if (twos[twoindex] == j && twoindex < twos.Count - 1) {\n" +
                "                sheet.addMergedRegion(new CellRangeAddress(twos[twoindex] + 3, twos[twoindex + 1] + 2, 16, 16));\n" +
                "                cell = newrow.createCell(16);\n" +
                "                cell.CellStyle = stylel;\n" +
                "                cell.setCellValue(\"\");\n" +
                "            } else {\n" +
                "                cell = newrow.createCell(16);\n" +
                "                cell.CellStyle = stylel;\n" +
                "            }\n" +
                "            if (flag)\n" +
                "                twoindex++;";
        System.out.println(str.replaceAll("hrow", "headTwoRow").replaceAll(".CellStyle = headerstyle;", ".setCellStyle(headerStyle);").replaceAll("ICell", "HSSFCell")
                .replaceAll("CreateCell", "createCell").replaceAll("SetColumnWidth", "setColumnWidth").replaceAll("SetCellValue", "setCellValue")
                .replaceAll("AddMergedRegion", "addMergedRegion").replaceAll("CellStyle = stylel;", "setCellStyle(style1);")
                .replaceAll("CellStyle = style;", "setCellStyle(style);").replaceAll("CellStyle = stylered;", "setCellStyle(styleRed);").replaceAll("dt[j]", "reports.get(j)"));
    }


    @Test
    public void testFile() {
        List<String> list = Arrays.asList("bxb", "zgk", "lgh");
        Iterator<String> iterator = list.iterator();
        System.out.println(iterator.next());
        System.out.println(iterator.next());
        System.out.println(iterator.next());
        System.out.println(iterator.next());
    }



    @Test
    public void testUrl() throws Exception {
        URL url = null;
        try {
            url = new URL("http://localhost:8005/oauth2/token?grant_type=password&username=1&password=888888.a");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        HttpURLConnection conn = null;
        conn = (HttpURLConnection) url.openConnection();
        conn.setDoInput(true);
        conn.setDoOutput(true);
        conn.setRequestMethod("GET");
        conn.setRequestProperty("client-id" , "hussar-base");
        conn.setRequestProperty("password", "888888.");
        conn.setRequestProperty("Content-Type", "multipart/form-data; boundary=<calculated when request is sent>");
        InputStream is = conn.getInputStream();
        byte[] bytes = new byte[1024];
        int len = 0;  //记录每次读取的有效字节个数
        StringBuilder builder = new StringBuilder();
        while ((len = is.read(bytes)) != -1){
            //String (byte[] bytes,int offset,int length)把把字节数组的一部分转换为字符串 offset:数组的开始索引  length:转换的字节个数
            builder.append(new String(bytes,0,len));
        }
        is.close();
        System.out.println(builder);
        Map<String , Object> map = new HashMap<>();

    }

    @Test
    public void sahah() {
        Map<String , String> map = new HashMap<>();
        String sa = map.get("sa");
        System.out.println(sa);
        map.put("", "sa");
        System.out.println(map.get(""));
    }



}
