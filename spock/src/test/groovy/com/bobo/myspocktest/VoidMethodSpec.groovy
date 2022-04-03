package com.bobo.myspocktest

import com.bobo.myspocktest.model.UserInfo
import com.bobo.myspocktest.service.UserService
import com.bobo.myspocktest.utils.IDNumberUtil
import org.powermock.reflect.Whitebox
import spock.lang.Specification


class VoidMethodSpec extends Specification {

    def userService = new UserService()

    def idNumberUtil = Mock(IDNumberUtil)

    def "测试void方法"() {

        given: "准备参数"
        def user1 = new UserInfo(id: 1, cardId: "410225199208091234")
        def user2 = new UserInfo(id: 2, cardId: "410225199208091234")
        def list = [user1, user2]

        and: "属性设置"
        Whitebox.setInternalState(userService, "idNumberUtil", idNumberUtil)

        when: "调用方法"
        userService.setAgeTest(list)

        then: "验证调用获取年龄是否符合预期: 一共调用2次, 第一次输出29, 第二次是28"
        2 * idNumberUtil.getAge(_) >> 29 >> 28

        and: "验证年龄计算后的结果是否符合预期"
        with(list) {
            list[0].age == 29
            list[1].age == 28
        }
    }
}
