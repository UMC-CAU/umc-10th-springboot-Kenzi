package com.example.umc10th.missions.dto;

import java.util.List;

public class MissionResDTO {

    public record getMissionList(Long address, List<Long> missionIds) {}

    public record getMissionDone(Long address, Long userId, List<Long> completedMissionIds) {}

    public record getMissionDoing(Long address, Long userId, List<Long> doingMissionIds) {}

    public record setMissionDone(Long userId, Long missionId, boolean completed) {}

    public record postMissionReview(Long userId, Long missionId, Long reviewId) {}
}
