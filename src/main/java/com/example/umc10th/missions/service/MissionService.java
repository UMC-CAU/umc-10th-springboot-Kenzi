package com.example.umc10th.missions.service;

import com.example.umc10th.global.exception.ProjectException;
import com.example.umc10th.missions.converter.MissionConverter;
import com.example.umc10th.missions.dto.MissionReqDTO;
import com.example.umc10th.missions.dto.MissionResDTO;
import com.example.umc10th.missions.entity.Mission;
import com.example.umc10th.missions.entity.Review;
import com.example.umc10th.missions.enums.MissionErrorCode;
import com.example.umc10th.missions.repository.MissionAcceptRepository;
import com.example.umc10th.missions.repository.MissionRepository;
import com.example.umc10th.missions.repository.ReviewRepository;
import com.example.umc10th.users.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MissionService {

    private final MissionRepository missionRepository;
    private final ReviewRepository reviewRepository;
    private final MissionAcceptRepository missionAcceptRepository;
    private final UserRepository userRepository;

    public List<Mission> getAll() {
        return missionRepository.findAll();
    }

    public List<MissionResDTO.GetMissionListResponse> getMissionList(String addressCode, Long userId) {
        if (!userRepository.existsById(userId)) {
            throw new ProjectException(MissionErrorCode.MISSION_USER_NOT_FOUND);
        }

        List<MissionResDTO.AvailableMission> availableMissions = missionRepository.findAvailableMissions(addressCode, userId)
                .stream()
                .map(MissionConverter::toAvailableMission)
                .toList();
        if (availableMissions.isEmpty()) {
            throw new ProjectException(MissionErrorCode.AVAILABLE_MISSION_NOT_FOUND);
        }

        return List.of(MissionConverter.toMissionListResponse(addressCode, userId, availableMissions));
    }

    public List<MissionResDTO.GetMissionDoingResponse> getMissionDoing(Long userId, Long address, Integer page, Integer size, String sort) {
        if (page != null && page < 0) {
            throw new ProjectException(MissionErrorCode.INVALID_PAGINATION_REQUEST);
        }
        if (size != null && size < 1) {
            throw new ProjectException(MissionErrorCode.INVALID_PAGINATION_REQUEST);
        }
        if (!userRepository.existsById(userId)) {
            throw new ProjectException(MissionErrorCode.MISSION_USER_NOT_FOUND);
        }

        Pageable pageable = PageRequest.of(page == null ? 0 : page, size == null ? 10 : size, MissionConverter.resolveMissionSort(sort));
        Page<MissionAcceptRepository.AcceptedMissionProjection> doingMissionPage = missionAcceptRepository.findDoingMissions(userId, pageable);
        List<MissionResDTO.AvailableMission> doingMissions = doingMissionPage.getContent()
                .stream()
                .map(MissionConverter::toAvailableMission)
                .toList();
        if (doingMissions.isEmpty()) {
            throw new ProjectException(MissionErrorCode.MISSION_DOING_NOT_FOUND);
        }
        MissionResDTO.Pagination<MissionResDTO.AvailableMission> pagination = MissionConverter.toPagination(doingMissions, doingMissionPage);
        return List.of(MissionConverter.toMissionDoingResponse(address, userId, pagination));
    }

    public List<MissionResDTO.GetMissionDoneResponse> getMissionDone(Long userId, Long address, Integer page, Integer size, String sort) {
        if (page != null && page < 0) {
            throw new ProjectException(MissionErrorCode.INVALID_PAGINATION_REQUEST);
        }
        if (size != null && size < 1) {
            throw new ProjectException(MissionErrorCode.INVALID_PAGINATION_REQUEST);
        }
        if (!userRepository.existsById(userId)) {
            throw new ProjectException(MissionErrorCode.MISSION_USER_NOT_FOUND);
        }

        Pageable pageable = PageRequest.of(page == null ? 0 : page, size == null ? 10 : size, MissionConverter.resolveMissionSort(sort));
        Page<MissionAcceptRepository.AcceptedMissionProjection> doneMissionPage = missionAcceptRepository.findDoneMissions(userId, pageable);
        List<MissionResDTO.AvailableMission> doneMissions = doneMissionPage.getContent()
                .stream()
                .map(MissionConverter::toAvailableMission)
                .toList();
        if (doneMissions.isEmpty()) {
            throw new ProjectException(MissionErrorCode.MISSION_DONE_NOT_FOUND);
        }
        MissionResDTO.Pagination<MissionResDTO.AvailableMission> pagination = MissionConverter.toPagination(doneMissions, doneMissionPage);
        return List.of(MissionConverter.toMissionDoneResponse(address, userId, pagination));
    }

    public MissionResDTO.SetMissionDoneResponse completeMission(Long userId, Long missionId) {
        return MissionConverter.toMissionDoneResponse(userId, missionId);
    }

    public List<MissionResDTO.GetUserReviewResponse> getUserReviews(Long userId, String cursor, Integer size, String sort) {
        if (size != null && size < 1) {
            throw new ProjectException(MissionErrorCode.INVALID_PAGINATION_REQUEST);
        }
        if (!userRepository.existsById(userId)) {
            throw new ProjectException(MissionErrorCode.MISSION_USER_NOT_FOUND);
        }

        String reviewSort = MissionConverter.resolveReviewSort(sort);
        MissionConverter.ReviewCursor reviewCursor = MissionConverter.resolveReviewCursor(cursor, reviewSort);
        if (reviewCursor.reviewId() != null && reviewCursor.reviewId() < 1) {
            throw new ProjectException(MissionErrorCode.INVALID_PAGINATION_REQUEST);
        }
        if (reviewCursor.reviewId() != null) {
            Review cursorReview = reviewRepository.findByIdAndUserIdAndDeletedAtIsNull(reviewCursor.reviewId(), userId)
                    .orElseThrow(() -> new ProjectException(MissionErrorCode.INVALID_PAGINATION_REQUEST));
            if ("score".equals(reviewSort) && cursorReview.getScore().compareTo(reviewCursor.score()) != 0) {
                throw new ProjectException(MissionErrorCode.INVALID_PAGINATION_REQUEST);
            }
        }

        Pageable pageable = PageRequest.of(0, size == null ? 10 : size);
        Slice<Review> reviewSlice = switch (reviewSort) {
            case "id" -> reviewRepository.findUserReviewsOrderById(userId, reviewCursor.reviewId(), pageable);
            case "score" -> reviewRepository.findUserReviewsOrderByScore(userId, reviewCursor.score(), pageable);
            default -> throw new ProjectException(MissionErrorCode.INVALID_PAGINATION_REQUEST);
        };
        List<MissionResDTO.ReviewResponse> reviews = reviewSlice.getContent()
                .stream()
                .map(MissionConverter::toReviewResponse)
                .toList();
        if (reviews.isEmpty()) {
            throw new ProjectException(MissionErrorCode.REVIEW_NOT_FOUND);
        }

        String nextCursor = MissionConverter.resolveNextReviewCursor(reviewSort, reviews, reviewSlice.hasNext());
        MissionResDTO.SlicePagination<MissionResDTO.ReviewResponse> pagination = MissionConverter.toSlicePagination(reviews, reviewSlice, nextCursor);

        return List.of(MissionConverter.toUserReviewResponse(userId, pagination));
    }

    @Transactional
    public MissionResDTO.PostMissionReviewResponse postMissionReview(Long userId, Long missionId, MissionReqDTO.ReviewRequest review) {
        Mission mission = missionRepository.findById(missionId)
                .orElseThrow(() -> new ProjectException(MissionErrorCode.MISSION_NOT_FOUND));

        if (reviewRepository.existsActiveReview(missionId, userId)) {
            throw new ProjectException(MissionErrorCode.REVIEW_ALREADY_EXISTS);
        }

        Review newReview = MissionConverter.toReview(userId, mission, review);
        Review savedReview = reviewRepository.save(newReview);

        return MissionConverter.toPostMissionReviewResponse(userId, missionId, savedReview.getId());
    }
}
