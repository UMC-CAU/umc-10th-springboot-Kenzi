package com.example.umc10th.missions.dto;

import java.util.List;

public class MissionResDTO {

    public record Pagination<T>(
            List<T> data,
            Integer pageNumber,
            Integer pageSize,
            Long totalElements,
            Integer totalPages,
            Boolean last
    ) {}

    public record AvailableMission(
            Long missionId,
            Integer point,
            String description,
            String storeName
    ) {}

    public record GetMissionListResponse(String addressCode, Long userId, List<AvailableMission> missions) {}

    public record GetMissionDoneResponse(Long address, Long userId, Pagination<AvailableMission> completedMissions) {}

    public record GetMissionDoingResponse(Long address, Long userId, Pagination<AvailableMission> doingMissions) {}

    public record SetMissionDoneResponse(Long userId, Long missionId, boolean completed) {}

    public record PostMissionReviewResponse(Long userId, Long missionId, Long reviewId) {}
}
