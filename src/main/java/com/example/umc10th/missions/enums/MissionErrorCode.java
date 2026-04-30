package com.example.umc10th.missions.enums;

import com.example.umc10th.global.code.BaseErrorCode;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum MissionErrorCode implements BaseErrorCode {

    MISSION_NOT_FOUND(HttpStatus.NOT_FOUND, "M400", "미션을 찾을 수 없습니다."),
    MISSION_ALREADY_COMPLETED(HttpStatus.BAD_REQUEST, "M401", "이미 완료된 미션입니다."),
    MISSION_NOT_ACCEPTED(HttpStatus.BAD_REQUEST, "M402", "수락되지 않은 미션입니다."),
    REVIEW_ALREADY_EXISTS(HttpStatus.CONFLICT, "M403", "이미 리뷰가 등록된 미션입니다.");

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;
}
