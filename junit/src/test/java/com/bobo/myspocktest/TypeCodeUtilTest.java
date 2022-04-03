package com.bobo.myspocktest;

import com.bobo.myspocktest.utils.TypeCodeUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Map;
import java.util.function.Predicate;


public class TypeCodeUtilTest {

    @ParameterizedTest
    @MethodSource("getTypeCodeParams")
    public void testTypeCode(int code, Predicate<String> predicate){
        String typeNameByCode = TypeCodeUtil.getTypeNameByCode(code);
        Assertions.assertTrue(predicate.test(typeNameByCode));
    }

    public static Object[] getTypeCodeParams(){
        return new Object[]{
                new Object[]{
                        1,(Predicate<String>) value -> "name1".equals(value)
                },
                new Object[]{
                        2,(Predicate<String>) value -> "name2".equals(value)
                },
                new Object[]{
                        3,(Predicate<String>) value -> "name3".equals(value)
                },
                new Object[]{
                        4,(Predicate<String>) value -> "name4".equals(value)
                },
                new Object[]{
                        5,(Predicate<String>) value -> "name5".equals(value)
                },
                new Object[]{
                        6,(Predicate<String>) value -> "".equals(value)
                },

        };
    }

}
