package com.bobo.myspocktest.utils;


public class TypeCodeUtil {

    public static String getTypeNameByCode(int code){
        String result = "";
        switch (code){
            case 1:
                result = "name1";
                break;
            case 2:
                result = "name2";
                break;
            case 3:
                result = "name3";
                break;
            case 4:
                result = "name4";
                break;
            case 5:
                result = "name5";
                break;
            default:
                break;
        }
        return result;
    }
}
