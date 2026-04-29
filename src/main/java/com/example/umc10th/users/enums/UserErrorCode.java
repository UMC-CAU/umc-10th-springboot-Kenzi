package com.example.umc10th.users.enums;

import com.example.umc10th.global.code.BaseErrorCode;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;


@Getter
@RequiredArgsConstructor
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum UserErrorCode implements BaseErrorCode {
    USER_NOT_FOUND(HttpStatus.NOT_FOUND , "사용자를 찾을 수 없습니다." , "U100"),
    ;

    private final HttpStatus httpStatus;
    private final String message;
    private final String code;
}
