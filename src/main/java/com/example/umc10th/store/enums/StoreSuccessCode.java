package com.example.umc10th.store.enums;

import com.example.umc10th.global.code.BaseSuccessCode;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum StoreSuccessCode implements BaseSuccessCode {

    STORE_LIST_FOUND(HttpStatus.OK, "S200", "가게 목록을 성공적으로 조회했습니다."),
    STORE_DETAIL_FOUND(HttpStatus.OK, "S201", "가게 상세 정보를 성공적으로 조회했습니다.");

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;
}

