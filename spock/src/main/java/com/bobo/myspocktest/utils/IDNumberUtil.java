package com.bobo.myspocktest.utils;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

/**
 * 身份证号解析器
 *

 */
public class IDNumberUtil {

    /**
     * 根据身份证号码获取出省日期和年龄
     *
     * @param idNo 18位身份证号码
     * @return 返回格式: birth: 1992-01-01  age: 29
     */
    public static Map<String, String> getBirthAge(String idNo) {

        String birthday = "";
        String age = "";

        int year = Calendar.getInstance().get(Calendar.YEAR);
        birthday = idNo.substring(6, 10) + "-" + idNo.substring(10, 12) + "-" + idNo.substring(12, 14);
        age = String.valueOf(year - Integer.valueOf(idNo.substring(6, 10)));

        Map<String, String> result = new HashMap<>();
        result.put("birthday", birthday);
        result.put("age",age);

        return result;
    }

    public int getAge(String idNo){
        int year = Calendar.getInstance().get(Calendar.YEAR);
        return (year - Integer.valueOf(idNo.substring(6, 10)));
    }
}
