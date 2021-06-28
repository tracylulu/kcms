package com.h3c.itrd.util;

import org.apache.poi.ss.formula.functions.T;

import com.h3c.itrd.common.consts.SystemConstant;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 系统的一些工具类
 * Created by chenlulu on 2021/4/29
 */
public class SystemCommonUtil {
    /**
     *
     * 按照固定大小分割 list 集合
     * @param
     * @param
     * @return
     */
    // 使用并行流处理
    public static <T> List<List<T>> splitList(List<T> list, int size) {
        Integer limit = (list.size() + size - 1) / size;
        List<List<T>> splitList = Stream.iterate(0, n -> n + 1).limit(limit).parallel()
                .map(a -> list.stream().skip(a * size).limit(size).parallel().collect(Collectors.toList()))
                .collect(Collectors.toList());
        return splitList;
    }

    //严重程度 '致命'=>10,'严重'=>3,'一般'=>1,'提示'=>0.5
    public static double getIdmsType(String odcSeverity) {
        double value=0d;
        if ("致命".equals(odcSeverity)) {
            value=SystemConstant.FATAL;
        }else if("严重".equals(odcSeverity)){
            value=SystemConstant.SERIOUS;
        }else if("一般".equals(odcSeverity)){
            value=SystemConstant.COMMONLY;
        }else if("提示".equals(odcSeverity)){
            value=SystemConstant.TIPS;
        }else{
            value=0d;
        }
        return value;
    }

    //保留2位小数
    public static double doubleFormat2(double d) {
    	BigDecimal b = new BigDecimal(d);
    	d = b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();        
    	System.out.println(d);
        return d;
    }
    
    //保留3位小数（开发得分汇算那块用到）
    public static double doubleFormat3(double d) {
    	BigDecimal b = new BigDecimal(d);
    	d = b.setScale(3, BigDecimal.ROUND_HALF_UP).doubleValue();        
    	System.out.println(d);
        return d;
    }
    
    //软件开发鉴定得分C： 按鉴定评价的9个等级依次给100到10分
    public static int getAppraiseEva(String appraiseEva) {
        int value=0;
        if ("A1".equals(appraiseEva)) {
            value=100;
        }else if("A2".equals(appraiseEva)){
            value=90;
        }else if("B1".equals(appraiseEva)){
            value=80;
        }else if("B2".equals(appraiseEva)){
            value=70;
        }else if("C1".equals(appraiseEva)){
            value=60;
        }else if("C2".equals(appraiseEva)){
            value=50;
        }else if("D1".equals(appraiseEva)){
            value=40;
        }else if("D2".equals(appraiseEva)){
            value=30;
        }else if("E1".equals(appraiseEva)){
            value=20;
        }else if("E2".equals(appraiseEva)){
            value=10;
        }else if("A".equals(appraiseEva)){
            value=100;
        }else if("B".equals(appraiseEva)){
            value=80;
        }else if("C".equals(appraiseEva)){
            value=60;
        }else if("D".equals(appraiseEva)){
            value=40;
        }else if("E".equals(appraiseEva)){
            value=20;
        }else {
        	value=0;
        }
        return value;
    }
    
    /**
     * URL 转码
     *
     */
    public static String getURLEncoderString(String str) {
        String result = "";
        if (null == str) {
            return "";
        }
        try {
            result = java.net.URLEncoder.encode(str, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return result;
    }
    
    
    

    public static void main(String[] args) {

       // String quarter = getQuarter();
        //System.out.println(quarter);
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7);
//        int limit=(list.size() + MAX_SEND - 1) / MAX_SEND;
//        System.out.println(limit);
//        //方法二：获取分割后的集合
//        List<List<Integer>> splitList = Stream.iterate(0, n -> n + 1).limit(limit).parallel().
//                map(a -> list.stream().skip(a * MAX_SEND).limit(MAX_SEND).parallel().collect(Collectors.toList())).
//                collect(Collectors.toList());
//        List<List<Integer>> lists = splitList(list, 3);
//        for (int i = 0; i <lists.size() ; i++) {
//            System.out.println(lists.get(i));
//        }
        double doubleFormat2 = doubleFormat2(123.256987);
        System.out.println(doubleFormat2);
        /*Map timeSpace = getTimeSpace("2020Q4");
        System.out.println(timeSpace.get("start").toString());
        System.out.println(timeSpace.get("end").toString());*/
        /*Long timestamp = System.currentTimeMillis();

        String pattern = "yyyy-MM-dd HH:mm:ss";

        System.out.println(convertTimestamp2Date(timestamp, pattern));*/

    }
}
