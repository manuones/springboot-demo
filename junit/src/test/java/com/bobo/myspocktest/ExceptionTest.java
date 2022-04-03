package com.bobo.myspocktest;

import com.bobo.myspocktest.model.APIException;
import com.bobo.myspocktest.model.UserInfo;
import com.bobo.myspocktest.service.ValidateService;
import com.bobo.myspocktest.utils.IDNumberUtil;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.rules.ExpectedException;
import org.mockito.InjectMocks;

import java.util.Map;
import java.util.function.Predicate;


public class ExceptionTest {

    private ValidateService validateService = new ValidateService();

    /**
     * junit3
     */
    @Test
    public void testException1(){

        try {
            validateService.validateUser(null);
        }catch (APIException e){
            Assert.assertEquals(e.getErrorCode(),"1001");
            Assert.assertEquals(e.getErrorMessage(),"userInfo is null");
        }

        try {
            validateService.validateUser(new UserInfo(0,""));
        }catch (APIException e){
            Assert.assertEquals(e.getErrorCode(),"1002");
            Assert.assertEquals(e.getErrorMessage(),"id is not legal");
        }

        try {
            validateService.validateUser(new UserInfo(1,""));
        }catch (APIException e){
            Assert.assertEquals(e.getErrorCode(),"1003");
            Assert.assertEquals(e.getErrorMessage(),"name is not legal");
        }

    }

    /**
     * junit 4
     */
    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void testException2(){
        expectedException.expect(APIException.class);
        expectedException.expectMessage("userInfo is null");
        validateService.validateUser(null);
    }

    @Test(expected = APIException.class)
    public void testException3(){
        validateService.validateUser(null);
    }

    /**
     * junit5
     */
    @Test
    public void testException4(){
        Throwable throwable = Assertions.assertThrows(APIException.class,()->{
            validateService.validateUser(null);
        });
        Assertions.assertEquals("userInfo is null",throwable.getMessage());
    }
}
