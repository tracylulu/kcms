package com.h3c.itrd.util;



import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.h3c.itrd.common.consts.SystemConstant;
import com.xxl.job.core.log.XxlJobLogger;

/**
 * Created by chenlulu on 2021/4/7
 */
public class DateTimeUtils {
    /**
     * 时间戳按指定格式转化为日期（String）
     * @param timestamp
     * @param pattern
     * @return
     */
    public static String convertTimestamp2Date(Long timestamp, String pattern) {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

        return simpleDateFormat.format(new Date(timestamp));
    }

    public static Map getTimeSpace(String quarter){
        Map<String,String> timeMap=new HashMap();
        String year = quarter.substring(0, 4);
        String d = quarter.substring(quarter.length()-2);
        System.out.println(year+"--"+d);
        String start="";
        String end="";
        String start1="";
        String end1="";
        if(d.equals("Q1")){
             start=SystemConstant.START_D_Q1;
             end=SystemConstant.END_D_Q1;
             start1=start;
             end1=end;
        }else if(d.equals("Q2")){
            start=SystemConstant.START_D_Q2;
            end=SystemConstant.END_D_Q2;
            start1=start;
            end1=end;
        }else if(d.equals("Q3")){
            start=SystemConstant.START_D_Q3;
            end=SystemConstant.END_D_Q3;
            start1=start;
            end1=end;
        }else{
            start=SystemConstant.START_D_Q4;
            end=SystemConstant.END_D_Q4;
            start1=start;
            end1=end;
        }
        start=year+start+" 00:00:00";
        end=year+end+" 23:59:59";
        start1=year+start1;
        end1=year+end1;
        timeMap.put("start",start);
        timeMap.put("end",end);
        timeMap.put("start1",start1);
        timeMap.put("end1",end1);
        return timeMap;
    }

    /**
     * 获取当前季度：2021Q2
     *
     */
    public static String getQuarter() {
        Calendar c = Calendar.getInstance();
        //获取年份
        String year=c.get(Calendar.YEAR)+"";
        //获取月份
        int month = c.get(Calendar.MONTH) + 1;
        int quarter = 0;
        if (month >= 1 && month <= 3) {
            quarter = 1;
        } else if (month >= 4 && month <= 6) {
            quarter = 2;
        } else if (month >= 7 && month <= 9) {
            quarter = 3;
        } else {
            quarter = 4;
        }
        return year+"Q"+quarter;
    }

    /**
     * 获取当前时间的上个季度
     */
    public static String lastQuarter(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH) + 1;
        int quarter = month % 3 == 0 ? month / 3 : (month / 3) + 1;
        if (quarter == 1) {
            return (year - 1) + "Q4";
        }
        return year + "Q" + (quarter - 1);
    }
    
    /**
     * 获取当前季度：2021-Q2
     *
     */
    public static String getQuarter1() {
        Calendar c = Calendar.getInstance();
        //获取年份
        String year=c.get(Calendar.YEAR)+"";
        //获取月份
        int month = c.get(Calendar.MONTH) + 1;
        int quarter = 0;
        if (month >= 1 && month <= 3) {
            quarter = 1;
        } else if (month >= 4 && month <= 6) {
            quarter = 2;
        } else if (month >= 7 && month <= 9) {
            quarter = 3;
        } else {
            quarter = 4;
        }
        return year+"-Q"+quarter;
    }
    /**
     * 获取当前季度：2021-04-01
     *
     */
    public static String getQuarterDate() {
    	Calendar c = Calendar.getInstance();
    	//获取月份
        int month = c.get(Calendar.MONTH);
        int quarterFirstMon =month+1-month%3;
        int year = c.get(Calendar.YEAR);
    	return year+"-"+quarterFirstMon+"-1";
    }
    
    /**
     * 获取今天日期：2021-5-26
     *
     */
    public static String getTodayDate() {
    	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
    	Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        Date d = c.getTime();
        return  format.format(d);
    }
    
    /**
     * 获取今天前/后几天日期。昨天：传参 -1，明天：传参：1
     *
     */
    public static String getBaseTodayDate(int day) {
    	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
    	Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.add(Calendar.DATE,day);
        Date d = c.getTime();
        return  format.format(d);
    }

    public static String getQuarterByDate(String date) throws ParseException{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date datePar = sdf.parse(date);
		Calendar c = Calendar.getInstance();
		c.setTime(datePar);
		//获取年份
        String year=c.get(Calendar.YEAR)+"";
        //获取月份
        int month = c.get(Calendar.MONTH) + 1;
        int quarter = 0;
        if (month >= 1 && month <= 3) {
            quarter = 1;
        } else if (month >= 4 && month <= 6) {
            quarter = 2;
        } else if (month >= 7 && month <= 9) {
            quarter = 3;
        } else {
            quarter = 4;
        }
        return year+"Q"+quarter;
	}
    
    /**
     * 根据月获得季度
     * @param month  月
     * @return  季度
     */
    private static int getQuarter(int month) {
        if(month == 1 || month == 2 || month == 3){
            return 1;
        }else if(month == 4 || month == 5 || month == 6){
            return  2;
        }else if(month == 7 || month == 8 || month == 9){
            return 3;
        }else{
            return 4;
        }
    }
    
    public static List<String> getRangeSet_Q(String beginDate,String endDate){
        /*      Date1.after(Date2),当Date1大于Date2时，返回TRUE，当小于等于时，返回false；
          Date1.before(Date2)，当Date1小于Date2时，返回TRUE，当大于等于时，返回false；
          如果业务数据存在相等的时候，而且相等时也需要做相应的业务判断或处理时，你需要使用：！Date1.after(Date2);*/
        List<String> rangeSet =null;
        SimpleDateFormat sdf = null;
          Date begin_date = null;
          Date end_date = null;
          String[] numStr =null;
          String Q=null;
          rangeSet = new java.util.ArrayList<String>();
          sdf = new SimpleDateFormat("yyyy-MM");
        try {
              begin_date = sdf.parse(beginDate);//定义起始日期
              end_date = sdf.parse(endDate);//定义结束日期
        } catch (Exception e) {
            System.out.println("时间转化异常，请检查你的时间格式是否为yyyy-MM或yyyy-MM-dd");
        }
          Calendar dd = Calendar.getInstance();//定义日期实例
          dd.setTime(begin_date);//设置日期起始时间
          while(!dd.getTime().after(end_date)){//判断是否到结束日期
              numStr=  sdf.format(dd.getTime()).split("-",0);
              Q = getQuarter(Integer.valueOf(numStr[1]))+"";
              System.out.println(numStr[0].toString()+"年"+numStr[1].toString()+"月"+"为"+numStr[0].toString()+"年第"+Q+"季");
              rangeSet.add(Q);
              dd.add(Calendar.MONTH, 1);//进行当前日期月份加1
          }
          return rangeSet;
    }
    
    //按每3个一组分割
    private static final Integer MAX_SEND = 4;



    public static void main(String[] args) throws ParseException {

       // String quarter = getQuarter();
        //System.out.println(quarter);
//        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7);
//        int limit=(list.size() + MAX_SEND - 1) / MAX_SEND;
//        System.out.println(limit);
//        //方法二：获取分割后的集合
//        List<List<Integer>> splitList = Stream.iterate(0, n -> n + 1).limit(limit).parallel().
//                map(a -> list.stream().skip(a * MAX_SEND).limit(MAX_SEND).parallel().collect(Collectors.toList())).
//                collect(Collectors.toList());
//
//        System.out.println(splitList);
    	//String i =getBaseTodayDate(-7);
    	//String lastQuarter = lastQuarter(new Date());
    	//System.out.println(lastQuarter);
    	//根据当前时间获取当前季度
		String nowQuarter = DateTimeUtils.getQuarter();
		System.out.println(nowQuarter);
		//根据当前时间获取上个季度
		String lastQuarter = DateTimeUtils.lastQuarter(new Date());
		if(nowQuarter.equals(lastQuarter)) {
			System.out.println("相同");
		}else {
			System.out.println("不同");
		}
		String baseTodayDate = getBaseTodayDate(-30);
		System.out.println(baseTodayDate);
		String quarterByDate = getQuarterByDate(baseTodayDate);
		System.out.println(quarterByDate);
        /*Map timeSpace = getTimeSpace("2020Q4");
        System.out.println(timeSpace.get("start").toString());
        System.out.println(timeSpace.get("end").toString());*/
        /*Long timestamp = System.currentTimeMillis();

        String pattern = "yyyy-MM-dd HH:mm:ss";

        System.out.println(convertTimestamp2Date(timestamp, pattern));*/

    }
}
