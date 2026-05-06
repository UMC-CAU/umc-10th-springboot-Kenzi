package com.example.umc10th.missions.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;


@Getter
@NoArgsConstructor
public class MissionReqDTO {

    public record GetMissionListRequest(Long address, Long userId) {}

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
