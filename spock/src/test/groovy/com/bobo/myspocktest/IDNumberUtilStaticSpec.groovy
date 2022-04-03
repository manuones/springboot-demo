package com.bobo.myspocktest

import com.bobo.myspocktest.service.UserService
import com.bobo.myspocktest.utils.IDNumberUtil
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.powermock.api.mockito.PowerMockito
import org.powermock.core.classloader.annotations.PrepareForTest
import org.powermock.modules.junit4.PowerMockRunner
import org.powermock.modules.junit4.PowerMockRunnerDelegate
import org.spockframework.runtime.Sputnik
import spock.lang.Specification


@PrepareForTest(IDNumberUtil.class)
@RunWith(PowerMockRunner.class)
@PowerMockRunnerDelegate(Sputnik.class)
class IDNumberUtilStaticSpec extends Specification {

    def userService = new UserService();

    void setup() {
        PowerMockito.mockStatic(IDNumberUtil.class)
    }

    def "getAge"() {

        given: "准备参数"
        def map = [birthday: "1992-08-09", age: "29"]

        and: "mock"
        PowerMockito.when(IDNumberUtil.getBirthAge(Mockito.anyString())).thenReturn(map)

        when: "方法调用"
        def respsnse = userService.getUserAgeByCardId("aa")

        then: "结果验证"
        with(respsnse){
            respsnse == 29
        }
    }
}
