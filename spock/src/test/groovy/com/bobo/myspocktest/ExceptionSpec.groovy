package com.bobo.myspocktest

import com.bobo.myspocktest.model.APIException
import com.bobo.myspocktest.model.UserInfo
import com.bobo.myspocktest.service.ValidateService
import spock.lang.Specification
import spock.lang.Unroll


class ExceptionSpec extends Specification {

    def validateService = new ValidateService()

    @Unroll
    def "验证UserInfo"() {

        when: "调用校验方法"
        validateService.validateUser(user)

        then: "捕获异常并设置需要验证的异常值"
        def exception = thrown(expectedException)
        exception.errorCode == expectedErrCode
        exception.errorMessage == expectedMessage

        where: "验证用户的合法性"
        user                || expectedException | expectedErrCode | expectedMessage
        null                || APIException      | "1001"          | "userInfo is null"
        new UserInfo(0, "") || APIException      | "1002"          | "id is not legal"
        new UserInfo(1, "") || APIException      | "1003"          | "name is not legal"
    }
}
