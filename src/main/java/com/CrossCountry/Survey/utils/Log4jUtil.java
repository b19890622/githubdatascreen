package com.CrossCountry.Survey.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * 记录日志的工具类 主要输出字符串 </br>
 *
 * @version 1.0
 * @since 2017/03/08</br>
 * @author bluetata
 */
public class Log4jUtil {
//    private static Log4jUtil instance = null;
//    private static Logger logger = null;
//    private static Log log = LogFactory.getLog("Fault");

//    static {
////        logger = Logger.getLogger(Log4jUtil.class);
//    }

    private Log4jUtil() {
    }

//    public static Log4jUtil getInstance() {
//        synchronized (Log4jUtil.class) {
//            if (instance == null) {
//                instance = new Log4jUtil();
//            }
//        }
//        return instance;
//    }

    public static void debug(Class<? extends Object> clazz ,String str) {
    	Logger logger = LogManager.getLogger("log4j2_info");
        logger.debug(str);
    }

    public static void debug(Class<? extends Object> clazz ,String str, Throwable e) {
    	Logger logger = LogManager.getLogger("log4j2_info");
        logger.debug(str, e);
    }

    public static void info(Class<? extends Object> clazz ,String str) {
    	Logger logger = LogManager.getLogger("log4j2_info");
        logger.info(str);
    }

    public static void info(Class<? extends Object> clazz ,String str, Throwable e) {
    	Logger logger = LogManager.getLogger("log4j2_info");
        logger.info(str, e);
    }

    public static void warn(Class<? extends Object> clazz ,String str) {
    	Logger logger = LogManager.getLogger("log4j2_warn");
        logger.warn(str);
    }

    public static void warn(Class<? extends Object> clazz ,String str, Throwable e) {
    	Logger logger = LogManager.getLogger("log4j2_warn");
        logger.warn(str, e);
    }

    public static void error(Class<? extends Object> clazz ,String str) {
    	Logger logger = LogManager.getLogger(clazz.getName());
        logger.error(str);
    }

    public static void error(Class<? extends Object> clazz ,String str, Throwable e) {
    	Logger logger = LogManager.getLogger(clazz.getName());
        logger.error(str, e);
    }

    public static void fatal(Class<? extends Object> clazz ,String str) {
    	Logger logger = LogManager.getLogger(clazz);
        logger.fatal(str);
    }

    public static void fatal(Class<? extends Object> clazz ,String str, Throwable e) {
    	Logger logger = LogManager.getLogger(clazz);
        logger.fatal(str, e);
    }


    public static void fault(String str) {
    	Logger logger = LogManager.getLogger("log4j2_default");
    	logger.info(str);
    }

    public static void fault(Class<? extends Object> clazz ,String str, Throwable e) {
    	Logger logger = LogManager.getLogger("log4j2_default");
    	logger.info(str, e);
    }
    
    public static void directFile(String logtype, String msg){
    	Logger logger = LogManager.getLogger(logtype);
    	logger.info(msg);
    }
}