package com.diaos_erp.util;

public class StringUtil
{
    /**
     * 判断字符串是否为空字符串
     * 
     * @param str
     * @return boolean 为null或者空字符串的时候返回true，否则为false
     */
    public static boolean isEmpty(String str)
    {
        return str == null || str.equals("");
    }
}
