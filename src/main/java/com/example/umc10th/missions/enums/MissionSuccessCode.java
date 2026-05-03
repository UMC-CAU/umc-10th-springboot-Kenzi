package com.example.umc10th.missions.enums;

import com.example.umc10th.global.code.BaseSuccessCode;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum MissionSuccessCode implements BaseSuccessCode {

    MISSION_LIST_FOUND(HttpStatus.OK, "M200", "미션 목록을 성공적으로 조회했습니다."),
    MISSION_DOING_FOUND(HttpStatus.OK, "M201", "진행 중 미션을 성공적으로 조회했습니다."),
    MISSION_DONE_FOUND(HttpStatus.OK, "M202", "완료 미션을 성공적으로 조회했습니다."),
    MISSION_COMPLETED(HttpStatus.OK, "M203", "미션 완료 처리에 성공했습니다."),
    MISSION_REVIEW_CREATED(HttpStatus.CREATED, "M204", "미션 리뷰 등록에 성공했습니다.");

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;
}
