package com.madman.base.util;

import com.alibaba.fastjson.JSONObject;
import java.util.Collection;
import java.util.Dictionary;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EmptyChecker {

    //日志管理器
    private final static Logger logger = LoggerFactory.getLogger(EmptyChecker.class);

    public static boolean isEmpty(Object obj) {
        if (obj == null || "null".equals(obj)) {
            return true;
        }
        if (obj instanceof String) {
            return ((String) obj).trim().isEmpty();
        }
        if (obj instanceof Collection) {
            return ((Collection<?>) obj).isEmpty();
        }
        if (obj instanceof Dictionary) {
            return ((Dictionary<?, ?>) obj).isEmpty();
        }
        if (obj instanceof Map) {
            return ((Map<?, ?>) obj).isEmpty();
        }

        return false;
    }

    public static boolean isEmpty(Object... array) {
        if (array == null || array.length == 0) {
            return true;
        }

        for (Object o : array) {
            if (isNotEmpty(o)) {
                return false;
            }
        }

        return true;
    }

    public static boolean isEmpty(String... array) {
        if (array == null || array.length == 0) {
            return true;
        }

        for (String o : array) {
            if (isNotEmpty(o)) {
                return false;
            }
        }

        return true;
    }

    public static boolean isNotEmpty(Object obj) {
        return !isEmpty(obj);
    }

    public static boolean isNotEmpty(Object... array) {
        if (array == null || array.length == 0) {
            return false;
        }

        for (Object o : array) {
            if (isEmpty(o)) {
                return false;
            }
        }

        return true;
    }

    public static boolean isNotEmpty(String... array) {
        if (array == null || array.length == 0) {
            return false;
        }
        for (String o : array) {
            if (isEmpty(o)) {
                return false;
            }
        }
        return true;
    }

    public static void checkEmpty(Map<String, Object> items, String... strings) throws QTException {
        for (String paraKey : strings) {
            if (isEmpty(items.get(paraKey))) {
                logger.error("请求参数异常:[" + paraKey + "]");
                throw new QTException("9999", "系统异常");
            }
        }
    }

    public static void checkEmptyJson(JSONObject items, String... strings) throws QTException {
        for (String paraKey : strings) {
            if (isEmpty(items.get(paraKey))) {
                logger.error("请求参数异常:[" + paraKey + "]");
                throw new QTException("9999", "系统异常", "系统异常");
            }
        }
    }

    public static String notNullString(Object mess) {
        String msg = "";
        if (EmptyChecker.isEmpty(mess)) {
            return msg;
        }
        return mess.toString();
    }
}
