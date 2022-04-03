package com.bobo.myspocktest;

import com.bobo.myspocktest.utils.IDNumberUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Map;
import java.util.function.Predicate;


public class IDNumberUtilJunitTest {

    @ParameterizedTest
    @MethodSource("getBirthAgeParams")
    public void testGetBirthAge(String idNo,Predicate<Map<String,String>> predicate){
        Map<String, String> birthAgeMap = IDNumberUtil.getBirthAge(idNo);
        System.out.println(birthAgeMap);
        Assertions.assertTrue(predicate.test(birthAgeMap));
    }

    public static Object[] getBirthAgeParams(){
        return new Object[]{
                new Object[]{
                        "410225199208091234",(Predicate<Map<String,String>>) map -> "{birthday=1992-08-09, age=29}".equals(map.toString())
                },
                new Object[]{
                        "410225199308091234",(Predicate<Map<String,String>>) map -> "{birthday=1993-08-09, age=28}".equals(map.toString())
                },
                new Object[]{
                        "410225199408091234",(Predicate<Map<String,String>>) map -> "{birthday=1994-08-09, age=27}".equals(map.toString())
                },
                new Object[]{
                        "410225199508091234",(Predicate<Map<String,String>>) map -> "{birthday=1995-08-09, age=26}".equals(map.toString())
                },
                new Object[]{
                        "410225199608091234",(Predicate<Map<String,String>>) map -> "{birthday=1996-08-09, age=25}".equals(map.toString())
                },
        };
    }
}
