package com.example.umc10th.missions.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;


@Getter
@NoArgsConstructor
public class MissionReqDTO {

    public record GetMissionListRequest(
            @Schema(description = "주소 ID", example = "1")
            Long address,
            @Schema(description = "유저 ID", example = "1")
            Long userId,
            @Schema(description = "페이지 번호(0부터 시작, 미입력 시 0)", example = "0")
            Integer page,
            @Schema(description = "페이지 크기(미입력 시 10)", example = "10")
            Integer size
    ) {
    }

    public record CompleteMissionsRequest(Long userId, Long missionId) {
    }

    public record PostMissionReviewRequest(Long userId, Long missionId, ReviewRequest review) {
    }

    public record ReviewRequest(
            @Schema(description = "미션 ID", example = "1") Long missionId,
            @Schema(description = "스토어 ID", example = "10") Long storeId,
            @Schema(description = "유저 ID", example = "2") Long userId,
            @Schema(description = "리뷰 내용", example = "좋은 경험이었습니다.") String description,
            @Schema(description = "점수", example = "4.5") BigDecimal score,
            @Schema(description = "사진 URL", example = "http://example.com/photo.jpg") String photoUrl
    ) {
    }
}
