package org.example.felixlyd.account.tcc.util;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * class classname
 *
 * @author : liuyaodong
 * @date 2022/10/21
 */
public class ResultHolder {
    private static Map<Class<?>, Map<String, String>> resultMap = new ConcurrentHashMap<Class<?>, Map<String, String>>();

    public static void setResult(Class<?> actionClass, String xid, String v) {
        Map<String, String> results = resultMap.get(actionClass);

        if (results == null) {
            synchronized (resultMap) {
                if (results == null) {
                    results = new ConcurrentHashMap<>();
                    resultMap.put(actionClass, results);
                }
            }
        }

        results.put(xid, v);
    }

    public static String getResult(Class<?> actionClass, String xid) {
        Map<String, String> results = resultMap.get(actionClass);
        if (results != null) {
            return results.get(xid);
        }

        return null;
    }

    public static void removeResult(Class<?> actionClass, String xid) {
        Map<String, String> results = resultMap.get(actionClass);
        if (results != null) {
            results.remove(xid);
        }
    }
}
