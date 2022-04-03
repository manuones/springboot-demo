package com.bobo.myspocktest.service;

import com.bobo.myspocktest.dao.UserDao;
import com.bobo.myspocktest.model.UserInfo;
import com.bobo.myspocktest.utils.IDNumberUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.Map;


public class UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private IDNumberUtil idNumberUtil;

    public UserInfo getUserInfoById(long id) {
        List<UserInfo> userInfoList = userDao.getAllUserInfo();
        for (UserInfo userInfo : userInfoList) {
            if (userInfo.getId() == id) {
                return userInfo;
            }
        }
        return null;
    }

    public int getUserAgeByCardId(String cardId) {
        Map<String, String> birthAge = IDNumberUtil.getBirthAge(cardId);
        String a=new String();
        if(a.equals("sfd")){
            System.out.println("sdf");
        }else {
            System.out.println("sdff");
        }
        return Integer.valueOf(birthAge.get("age"));
    }

    /**
     * 根据idNo计算年龄然后赋值给age属性
     * @param list
     */
    public void setAgeTest(List<UserInfo> list) {
        if (CollectionUtils.isEmpty(list)) {
            return;
        }
        for (UserInfo userInfo : list) {
            int age = idNumberUtil.getAge(userInfo.getCardId());
            userInfo.setAge(age);
        }
    }
}
