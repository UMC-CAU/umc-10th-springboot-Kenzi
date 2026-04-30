package com.example.umc10th.global.code;

import org.springframework.http.HttpStatus;

public interface BaseSuccessCode {

    HttpStatus getHttpStatus();
    String getCode();
    String getMessage();

}
