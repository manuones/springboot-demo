package com.bobo.myspocktest

import com.bobo.myspocktest.utils.TypeCodeUtil
import spock.lang.Specification
import spock.lang.Unroll


class TypeCodeUtilSpec extends Specification {

    @Unroll
    def "code:#code,name:#name"() {
        expect:
        TypeCodeUtil.getTypeNameByCode(code) == name
        where:
        code || name
        1    || "name1"
        2    || "name2"
        3    || "name3"
        4    || "name4"
        5    || "name5"
        6    || ""
    }
}
