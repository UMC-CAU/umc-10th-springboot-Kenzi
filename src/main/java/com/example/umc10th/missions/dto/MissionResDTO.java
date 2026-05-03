package com.example.umc10th.missions.dto;

import java.util.List;

public class MissionResDTO {

    public record GetMissionListResponse(Long address, List<Long> missionIds) {}

    public record GetMissionDoneResponse(Long address, Long userId, List<Long> completedMissionIds) {}

    public record GetMissionDoingResponse(Long address, Long userId, List<Long> doingMissionIds) {}

    public record SetMissionDoneResponse(Long userId, Long missionId, boolean completed) {}

    public record PostMissionReviewResponse(Long userId, Long missionId, Long reviewId) {}
}
