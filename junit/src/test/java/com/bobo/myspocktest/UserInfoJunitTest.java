package com.bobo.myspocktest;

import com.bobo.myspocktest.dao.UserDao;
import com.bobo.myspocktest.model.UserInfo;
import com.bobo.myspocktest.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;


@ExtendWith({MockitoExtension.class})
public class UserInfoJunitTest {

    @Mock
    private UserDao userDao;

    @InjectMocks
    private UserService userService;

    @Before
    public void before(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void userinfoTest(){
        // 准备参数
        List<UserInfo> userInfoList = new ArrayList<>();
        UserInfo userInfo1 = new UserInfo();
        userInfo1.setId(0);
        userInfo1.setName("小明");

        UserInfo userInfo2 = new UserInfo();
        userInfo1.setId(1);
        userInfo1.setName("小强");

        userInfoList.add(userInfo1);
        userInfoList.add(userInfo2);

        // mock数据
        Mockito.when(userDao.getAllUserInfo()).thenReturn(userInfoList);
        UserInfo userInfo = userService.getUserInfoById(1);

        // 验证结果
        Assertions.assertEquals(userInfoList.get(1),userInfo);
    }
}
