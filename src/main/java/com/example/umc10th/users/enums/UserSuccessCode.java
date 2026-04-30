package com.example.umc10th.users.enums;

import com.example.umc10th.global.code.BaseSuccessCode;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum UserSuccessCode implements BaseSuccessCode {
    USER_SUCCESS_FOUND(HttpStatus.OK, "U200", "사용자 정보를 성공적으로 조회하였습니다."),
    ;

    private final HttpStatus httpStatus;
    private final String message;
    private final String code;
}
