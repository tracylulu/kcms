package com.h3c.itrd.util;

import org.apache.commons.lang3.StringUtils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * @author zhanghao
 * @ClassName: DateUtil
 * @Description: 日期工具类
 * @date 2018年12月28日
 */
public class DateUtil {

//    private  static SimpleDateFormat defaultSimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//    private  static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
//    private  static SimpleDateFormat simpleDayFormat = new SimpleDateFormat("dd");


    /**
     * 返回yyyy-MM-dd HH:mm:ss格式的日期字符串
     * @return 日期字符串
     */
    public static String getDateStrForDefault(Date date) throws Exception {
        if(date == null){
            throw new Exception("参数为null");
        }
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
    }

    /**
     * 返回yyyy-MM-dd格式的日期字符串
     * @return 日期字符串
     */
    public static String getDateFormat(Date date) throws Exception {
        if(date == null){
            throw new Exception("参数为null");
        }
        return new SimpleDateFormat("yyyy-MM-dd").format(date);
    }

    /**
     * 返回yyyyMMdd格式的日期字符串
     * @return 日期字符串
     */
    public static String getDateFormat2(Date date) throws Exception {
        if(date == null){
            throw new Exception("参数为null");
        }
        return new SimpleDateFormat("yyyyMMdd").format(date);
    }

    /**
     * 返回Date
     * @return Date
     */
    public static Date getDateForDefault(String date) throws Exception {
        if(StringUtils.isBlank(date)){
            throw new Exception("参数为不正确");
        }
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(date);
    }

    /**
     * 返回Date
     * @return Date
     */
    public static Date getDateForDefaultymd(String date) throws Exception {
        if(StringUtils.isBlank(date)){
            throw new Exception("参数为不正确");
        }
        return new SimpleDateFormat("yyyy-MM-dd").parse(date);
    }
    
    /**
     * 返回当前DateString
     * @return string
     */
    public static String getCurrDateStrForDefault() throws Exception {

        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
    }
    
    /**
     * 返回今天日期String
     * @return string
     */
    public static String getcurrDateStr() throws Exception {

        return new SimpleDateFormat("yyyy-MM-dd").format(new Date());
    }
    
    /**
     * 返回今天几号String
     * @return string
     */
    public static String getcurrDayStr() throws Exception {

        return new SimpleDateFormat("dd").format(new Date());
    }
    
    /**
     * 返回昨天日期String
     * @return string
     */
    public static String getyestDateStr() throws Exception {
    	Calendar calendar = new GregorianCalendar();

    	calendar.setTime(new Date());

    	calendar.add(calendar.DATE,-1);

        return new SimpleDateFormat("yyyy-MM-dd").format(calendar.getTime());
    }
    
    /**
     * 返回昨天几号String
     * @return string
     */
    public static String getyestDayStr() throws Exception {

    	Calendar calendar = new GregorianCalendar();

    	calendar.setTime(new Date());

    	calendar.add(calendar.DATE,-1);
    	
        return new SimpleDateFormat("dd").format(calendar.getTime());
    }
    
    /**
     * 返回当前月第一天日期String
     * @return string
     */
    public static String getcurrFirstDateStr() throws Exception {
        //String firstDate;
    	//Date date;
    	Calendar cale = null;
    	cale = Calendar.getInstance();
    	cale.add(Calendar.MONTH, 0);
    	cale.set(Calendar.DAY_OF_MONTH, 1);        
        return new SimpleDateFormat("yyyy-MM-dd").format(cale.getTime());
    }
    
    /**
     * 返回上个月第一天日期String
     * @return string
     */
    public static String getpreFirstDateStr() throws Exception {
        //String firstDate;
    	//Date date;
    	Calendar cale = null;
    	cale = Calendar.getInstance();
    	cale.add(Calendar.MONTH, -1);
    	cale.set(Calendar.DAY_OF_MONTH, 1);        
        return new SimpleDateFormat("yyyy-MM-dd").format(cale.getTime());
    }
    
    /**
     * 返回上个月最后一天日期String
     * @return string
     */
    public static String getpreLastDateStr() throws Exception {
        //String firstDate;
    	//Date date;
    	Calendar cale = null;
    	cale = Calendar.getInstance();
    	cale.set(Calendar.DAY_OF_MONTH, 1); 
    	cale.add(Calendar.DATE, -1);       
        return new SimpleDateFormat("yyyy-MM-dd").format(cale.getTime());
    }
    
    /**
     * 返回参数的上个月的第一天日期String
     * @return string
     */
    public static String getpreParamLastDateStr(String dateStr) throws Exception {
        SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd");
        Date date =null;
        date=sdf.parse(dateStr);
    	Calendar cale = null;
    	cale = Calendar.getInstance();
    	cale.setTime(date);
    	cale.add(Calendar.MONTH, -1);
    	cale.set(Calendar.DAY_OF_MONTH, 1);  
//    	cale.set(Calendar.DAY_OF_MONTH, -1); 
//    	cale.add(Calendar.DATE,0);       
        return new SimpleDateFormat("yyyy-MM-dd").format(cale.getTime());
    }

}
