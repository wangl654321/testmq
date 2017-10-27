package test;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Administrator on 2016/8/24.
 */
public class Test implements Cloneable {

    private static final Logger logger = LogManager.getLogger();

    public static void main(String[] args) {
        AtomicInteger acount = new AtomicInteger();

    }

    public static String testType(Object parameterObject){
        try{
            if (parameterObject instanceof String) {
                return "String";
            } else if(parameterObject instanceof Map) {
                return "Map";
            } else {
                return "";
            }
        }catch (Exception e) {
            return null;
        }
    }

    private int arrayInt(int[] arr, int z){
        int i = z % 10;
        return arr[i];
    }

    private int switchInt(int[] arr, int z){
        int i = z % 10;
        switch (i){
            case 1: return 1;
            case 2: return 1;
            case 3: return 1;
            case 4: return 1;
            case 5: return 1;
            case 6: return 1;
            case 7: return 1;
            case 8: return 1;
            case 9: return 1;
            default: return -1;
        }
    }

    public static String getStr(){
        return "str";
    }
}
