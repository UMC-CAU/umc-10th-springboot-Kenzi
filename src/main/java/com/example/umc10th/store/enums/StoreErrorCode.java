package com.example.umc10th.store.enums;

import com.example.umc10th.global.code.BaseErrorCode;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum StoreErrorCode implements BaseErrorCode {

    STORE_NOT_FOUND(HttpStatus.NOT_FOUND, "S400", "가게 정보를 찾을 수 없습니다."),
    STORE_LIST_NOT_FOUND(HttpStatus.NOT_FOUND, "S401", "해당 주소의 가게 목록을 찾을 수 없습니다.");

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;
}

