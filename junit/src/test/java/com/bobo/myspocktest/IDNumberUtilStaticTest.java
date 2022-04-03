package com.bobo.myspocktest;

import com.bobo.myspocktest.service.UserService;
import com.bobo.myspocktest.utils.IDNumberUtil;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.HashMap;
import java.util.Map;


@RunWith(PowerMockRunner.class)
@PrepareForTest(IDNumberUtil.class)
public class IDNumberUtilStaticTest {

    @InjectMocks
    private UserService userService;

    @Before
    public void setup(){
        PowerMockito.mockStatic(IDNumberUtil.class);
    }

    @Test
    public void testStatic(){

        // 准备参数
        Map<String,String> map = new HashMap<>();
        map.put("birthday","1992-08-09");
        map.put("age","29");

        // mock
        PowerMockito.when(IDNumberUtil.getBirthAge(Mockito.anyString())).thenReturn(map);
        int age = userService.getUserAgeByCardId("123");

        // 验证结果
        Assertions.assertEquals(29,age);
    }
}
