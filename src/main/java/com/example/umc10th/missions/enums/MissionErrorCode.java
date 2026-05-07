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
    REVIEW_ALREADY_EXISTS(HttpStatus.CONFLICT, "M403", "이미 리뷰가 등록된 미션입니다."),
    INVALID_PAGINATION_REQUEST(HttpStatus.BAD_REQUEST, "M404", "유효하지 않은 페이지 요청입니다."),
    MISSION_DOING_NOT_FOUND(HttpStatus.NOT_FOUND, "M405", "진행 중인 미션이 없습니다."),
    MISSION_DONE_NOT_FOUND(HttpStatus.NOT_FOUND, "M406", "완료한 미션이 없습니다."),
    MISSION_USER_NOT_FOUND(HttpStatus.NOT_FOUND, "M407", "유저를 찾을 수 없습니다."),
    AVAILABLE_MISSION_NOT_FOUND(HttpStatus.NOT_FOUND, "M408", "수락 가능한 미션이 없습니다.");

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;
}
