package com.example.umc10th.missions.dto;

import com.example.umc10th.missions.entity.Review;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor
public class MissionReqDTO {

    public record getMissionList(Long address , Long userId) {}

    public record setMissionComplete(Long userId, Long missionId) {}

    public record postMissionReview(Long userId, Long missionId , Review review) {}
}
