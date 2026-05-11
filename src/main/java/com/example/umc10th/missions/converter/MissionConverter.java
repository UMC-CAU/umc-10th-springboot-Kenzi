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
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;

import java.math.BigDecimal;
import java.util.List;

public class MissionConverter {

    private MissionConverter() {
    }

    public record ReviewCursor(Long reviewId, BigDecimal score) {
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

    public static <T> MissionResDTO.SlicePagination<T> toSlicePagination(
            List<T> data,
            Slice<?> slice,
            String nextCursor
    ) {
        return new MissionResDTO.SlicePagination<>(
                data,
                slice.getSize(),
                nextCursor,
                slice.hasNext()
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

    public static MissionResDTO.ReviewResponse toReviewResponse(Review review) {
        return new MissionResDTO.ReviewResponse(
                review.getId(),
                review.getMissionId(),
                review.getStoreId(),
                review.getUserId(),
                review.getDescription(),
                review.getScore(),
                review.getPhotoUrl(),
                review.getCreatedAt()
        );
    }

    public static MissionResDTO.GetUserReviewResponse toUserReviewResponse(
            Long userId,
            MissionResDTO.SlicePagination<MissionResDTO.ReviewResponse> reviews
    ) {
        return new MissionResDTO.GetUserReviewResponse(userId, reviews);
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

    public static String resolveReviewSort(String sort) {
        if (sort == null || sort.isBlank()) {
            return "id";
        }

        return switch (sort) {
            case "id" -> "id";
            case "score", "rating" -> "score";
            default -> throw new ProjectException(MissionErrorCode.INVALID_PAGINATION_REQUEST);
        };
    }

    public static ReviewCursor resolveReviewCursor(String cursor, String sort) {
        if (cursor == null || cursor.isBlank()) {
            return new ReviewCursor(null, null);
        }

        if ("id".equals(sort)) {
            return new ReviewCursor(parseCursorId(cursor), null);
        }

        try {
            return new ReviewCursor(null, new BigDecimal(cursor));
        } catch (NumberFormatException e) {
            throw new ProjectException(MissionErrorCode.INVALID_PAGINATION_REQUEST);
        }
    }

    public static String resolveNextReviewCursor(String sort, List<MissionResDTO.ReviewResponse> reviews, boolean hasNext) {
        if (!hasNext) {
            return null;
        }

        MissionResDTO.ReviewResponse lastReview = reviews.getLast();
        if ("score".equals(sort)) {
            return lastReview.score().toPlainString();
        }

        return String.valueOf(lastReview.reviewId());
    }

    private static Long parseCursorId(String cursor) {
        try {
            return Long.parseLong(cursor);
        } catch (NumberFormatException e) {
            throw new ProjectException(MissionErrorCode.INVALID_PAGINATION_REQUEST);
        }
    }
}
