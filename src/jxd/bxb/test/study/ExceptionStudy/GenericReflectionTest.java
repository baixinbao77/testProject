package jxd.bxb.test.study.ExceptionStudy;

import jxd.bxb.test.utils.StringUtil;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Scanner;

/**
 * @ClassName GenericReflectionTest
 * @Description TODO
 * @Author 白新报
 * @Date 2022/11/12 12:00
 * @Version 1.0
 **/
public class GenericReflectionTest {
    public static void main(String[] args) {
        String name;
        if (StringUtil.isNotEmpty(args)) {
            name = args[0];
        } else {
            try (Scanner sc = new Scanner(System.in)) {
                System.out.print("Please input name:");
                name = sc.next();
            }
        }
        try {
            Class<?> cl = Class.forName(name);
            printClass(cl);
            for (Method m : cl.getDeclaredMethods()) {
                printMethod(m);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void printClass(Class<?> cl) {
//        System.out.println(cl);
//        printTypes(cl.getTypeParameters(), "<", ", ", ">", true);
//        Type sc = cl.getGenericSuperclass();
//        if (sc != null) {
//            System.out.print(" extends ");
//            printType(sc, false);
//        }
//        printTypes(cl.getGenericInterfaces() , " implements " , ", " , "" , false);
//        System.out.println();
    }


    public static void printMethod(Method m) {
        String name = m.getName();
        System.out.println(Modifier.toString(m.getModifiers()));
        System.out.print(" ");
    }

    @Test
    public void runii() {
        StringBuffer sb = new StringBuffer();
        sb.append("<soap:Envelope xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">\n");
        sb.append("<soap:Header>\n");
        sb.append("<username xmlns=\"artisoft.cn\">").append("gypw").append("</username>\n");
        sb.append("<password xmlns=\"artisoft.cn\">").append("Gypw@5843").append("</password>\n");
        sb.append("</soap:Header>\n");
        sb.append("<soap:Body>\n");
        sb.append("<ns3:api_query_rdiTags xmlns:ns2=\"artisoft.API/2019/09/15\" xmlns:ns3=\"http://tempuri.org/\" xmlns:ns4=\"http://schemas.microsoft.com/2003/10/Serialization/\" xmlns:ns5=\"http://schemas.microsoft.com/2003/10/Serialization/Arrays\">\n");
        sb.append("<ns3:filter>").append("rdiTagQueryDto.getFilter()").append("</ns3:filter>\n");
        sb.append("<ns3:maxCount>").append("rdiTagQueryDto.getMaxCount()").append("</ns3:maxCount>\n");
        sb.append("<ns3:mTimeOut>").append("rdiTagQueryDto.getmTimeOut()").append("</ns3:mTimeOut>\n");
        sb.append("</ns3:api_query_rdiTags>\n");
        sb.append("</soap:Body>\n");
        sb.append("</soap:Envelope>");
        String soap = sb.toString();
        System.out.println(soap);
    }
}