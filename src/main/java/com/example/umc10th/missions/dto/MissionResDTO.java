package com.example.umc10th.missions.dto;

import java.util.List;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class MissionResDTO {

    public record Pagination<T>(
            List<T> data,
            Integer pageNumber,
            Integer pageSize,
            Long totalElements,
            Integer totalPages,
            Boolean last
    ) {}

    public record SlicePagination<T>(
            List<T> data,
            Integer pageSize,
            String nextCursor,
            Boolean hasNext
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

    public record ReviewResponse(
            Long reviewId,
            Long missionId,
            Long storeId,
            Long userId,
            String description,
            BigDecimal score,
            String photoUrl,
            LocalDateTime createdAt
    ) {}

    public record GetUserReviewResponse(Long userId, SlicePagination<ReviewResponse> reviews) {}
}
