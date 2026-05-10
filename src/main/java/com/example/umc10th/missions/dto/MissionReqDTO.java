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
    ) {}

    public record CompleteMissionsRequest(Long userId, Long missionId) {}

    public record PostMissionReviewRequest(Long userId, Long missionId, ReviewRequest review) {}

    public record ReviewRequest(
            Long id,
            Long missionId,
            Long storeId,
            Long userId,
            String description,
            BigDecimal score,
            String photoUrl,
            LocalDateTime createdAt,
            LocalDateTime deletedAt
    ) {}
}
