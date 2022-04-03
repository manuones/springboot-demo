package com.bobo.myspocktest

import com.bobo.myspocktest.dao.UserDao
import com.bobo.myspocktest.model.UserInfo
import com.bobo.myspocktest.service.UserService
import org.powermock.reflect.Whitebox
import spock.lang.Specification


class UserInfoSpec extends Specification {

    def userService = new UserService();

    def "getUserInfoById"() {
        given: "准备参数"
        def user1 = new UserInfo(id: 0, name: "小明")
        def user2 = new UserInfo(id: 1, name: "小强")

        and: "mock数据"
        def userDao = Mock(UserDao)
        Whitebox.setInternalState(userService, "userDao", userDao)
        userDao.getAllUserInfo() >> [user1, user2]

        when: "方法调用"
        def response = userService.getUserInfoById(1)

        then: "结果验证"
        with(response) {
            id == 1
            name == "小强"
        }
    }
}
