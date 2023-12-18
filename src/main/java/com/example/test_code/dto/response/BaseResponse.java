package com.example.test_code.dto.response;

import java.util.List;

import lombok.*;

@Getter
@Setter
public class BaseResponse<T> { 
    boolean status;
    String message;
    T data;
}
