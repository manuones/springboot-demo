package com.bobo.myspocktest

import com.bobo.myspocktest.utils.IDNumberUtil
import spock.lang.Specification
import spock.lang.Unroll


class IDNumberUtilSpec extends Specification {

    @Unroll
    def "身份证号：#idNO 的生日，年龄是：#result"() {
        expect:
        IDNumberUtil.getBirthAge(idNO) == result
        where:
        idNO                 || result
        "410225199208091234" || ["birthday": "1992-08-09", "age": "28"]
        "410225199308091234" || ["birthday": "1993-08-09", "age": "28"]
        "410225199408091234" || ["birthday": "1994-08-09", "age": "27"]
        "410225199508091234" || ["birthday": "1995-08-09", "age": "26"]
        "410225199608091234" || ["birthday": "1996-08-09", "age": "25"]
    }
}
