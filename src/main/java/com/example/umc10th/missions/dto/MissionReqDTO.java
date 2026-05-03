package com.example.umc10th.missions.dto;

import com.example.umc10th.missions.entity.Review;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor
public class MissionReqDTO {

    public record GetMissionListRequest(Long address, Long userId) {}

    public record CompleteMissionsRequest(Long userId, Long missionId) {}

    public record PostMissionReviewRequest(Long userId, Long missionId, Review review) {}
}
