package com.example.umc10th.missions.converter;

import com.example.umc10th.global.exception.ProjectException;
import com.example.umc10th.missions.dto.MissionReqDTO;
import com.example.umc10th.missions.dto.MissionResDTO;
import com.example.umc10th.missions.entity.Mission;
import com.example.umc10th.missions.entity.Review;
import com.example.umc10th.missions.enums.MissionErrorCode;
import com.example.umc10th.missions.repository.MissionAcceptRepository;
import com.example.umc10th.missions.repository.MissionRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

import java.util.List;

public class MissionConverter {

    private MissionConverter() {
    }

    public static <T> MissionResDTO.Pagination<T> toPagination(
            List<T> data,
            Page<?> page
    ) {
        return new MissionResDTO.Pagination<>(
                data,
                page.getNumber(),
                page.getSize(),
                page.getTotalElements(),
                page.getTotalPages(),
                page.isLast()
        );
    }

    public static MissionResDTO.AvailableMission toAvailableMission(MissionRepository.AvailableMissionProjection mission) {
        return new MissionResDTO.AvailableMission(
                mission.getMissionId(),
                mission.getPoint(),
                mission.getDescription(),
                mission.getStoreName()
        );
    }

    public static MissionResDTO.AvailableMission toAvailableMission(MissionAcceptRepository.AcceptedMissionProjection mission) {
        return new MissionResDTO.AvailableMission(
                mission.getMissionId(),
                mission.getPoint(),
                mission.getDescription(),
                mission.getStoreName()
        );
    }

    public static MissionResDTO.GetMissionListResponse toMissionListResponse(
            String addressCode,
            Long userId,
            List<MissionResDTO.AvailableMission> missions
    ) {
        return new MissionResDTO.GetMissionListResponse(addressCode, userId, missions);
    }

    public static MissionResDTO.GetMissionDoingResponse toMissionDoingResponse(
            Long address,
            Long userId,
            MissionResDTO.Pagination<MissionResDTO.AvailableMission> missions
    ) {
        return new MissionResDTO.GetMissionDoingResponse(address, userId, missions);
    }

    public static MissionResDTO.GetMissionDoneResponse toMissionDoneResponse(
            Long address,
            Long userId,
            MissionResDTO.Pagination<MissionResDTO.AvailableMission> missions
    ) {
        return new MissionResDTO.GetMissionDoneResponse(address, userId, missions);
    }

    public static MissionResDTO.SetMissionDoneResponse toMissionDoneResponse(Long userId, Long missionId) {
        return new MissionResDTO.SetMissionDoneResponse(userId, missionId, true);
    }

    public static Review toReview(Long userId, Mission mission, MissionReqDTO.ReviewRequest review) {
        return Review.create(
                mission.getId(),
                mission.getStoreId(),
                userId,
                review.description(),
                review.score(),
                review.photoUrl()
        );
    }

    public static MissionResDTO.PostMissionReviewResponse toPostMissionReviewResponse(
            Long userId,
            Long missionId,
            Long reviewId
    ) {
        return new MissionResDTO.PostMissionReviewResponse(userId, missionId, reviewId);
    }

    public static Sort resolveMissionSort(String sort) {
        if (sort == null || sort.isBlank()) {
            return Sort.by(Sort.Direction.DESC, "id");
        }

        String property = switch (sort) {
            case "id" -> "id";
            case "missionId", "mission_id" -> "missionId";
            case "point" -> "point";
            case "description" -> "description";
            case "storeName", "store_name" -> "storeName";
            case "createdAt", "created_at" -> "createdAt";
            case "completedAt", "completed_at" -> "completedAt";
            default -> throw new ProjectException(MissionErrorCode.INVALID_PAGINATION_REQUEST);
        };

        return Sort.by(Sort.Direction.DESC, property);
    }
}
