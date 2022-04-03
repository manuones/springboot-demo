package com.bobo.myspocktest.service;

import com.bobo.myspocktest.model.APIException;
import com.bobo.myspocktest.model.UserInfo;
import org.springframework.stereotype.Service;


@Service
public class ValidateService {

    public void validateUser(UserInfo userInfo){
        if (userInfo == null){
            throw new APIException("1001","userInfo is null");
        }else if (userInfo.getId()==0){
            throw new APIException("1002","id is not legal");
        }else if (userInfo.getName() == null || "".equals(userInfo.getName())){
            throw new APIException("1003","name is not legal");
        }
    }
}
