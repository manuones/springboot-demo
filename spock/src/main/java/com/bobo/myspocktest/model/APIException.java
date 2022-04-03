package com.bobo.myspocktest.model;

import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class APIException extends RuntimeException{
    private String errorCode;
    private String errorMessage;
}
