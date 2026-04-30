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
    USER_SUCCESS_FOUND(HttpStatus.OK, "사용자 정보를 성공적으로 조회하였습니다." , "U200"),
    USER_SUCCESS_SINGUP(HttpStatus.OK , "사용자 회원가입이 성공적으로 마무리되었습니다." ,"U300"),
    USER_SUCCESS_LOGIN(HttpStatus.OK, "로그인이 성공적으로 처리되었습니다.", "U301"),
    USER_SUCCESS_WITHDRAW(HttpStatus.OK, "회원 탈퇴가 성공적으로 처리되었습니다.", "U302");

    private final HttpStatus httpStatus;
    private final String message;
    private final String code;
}
