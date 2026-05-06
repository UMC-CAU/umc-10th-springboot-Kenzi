package com.example.umc10th.missions.service;

import com.example.umc10th.global.exception.ProjectException;
import com.example.umc10th.missions.dto.MissionReqDTO;
import com.example.umc10th.missions.dto.MissionResDTO;
import com.example.umc10th.missions.entity.Mission;
import com.example.umc10th.missions.entity.Review;
import com.example.umc10th.missions.enums.MissionErrorCode;
import com.example.umc10th.missions.repository.MissionRepository;
import com.example.umc10th.missions.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MissionService {

    private final MissionRepository missionRepository;
    private final ReviewRepository reviewRepository;

    public List<Mission> getAll() {
        return missionRepository.findAll();
    }

    public List<MissionResDTO.GetMissionListResponse> getMissionList(Long address) {
        return List.of(new MissionResDTO.GetMissionListResponse(address, List.of()));
    }

    public List<MissionResDTO.GetMissionDoingResponse> getMissionDoing(Long userId, Long address) {
        return List.of(new MissionResDTO.GetMissionDoingResponse(address, userId, List.of()));
    }

    public List<MissionResDTO.GetMissionDoneResponse> getMissionDone(Long userId, Long address) {
        return List.of(new MissionResDTO.GetMissionDoneResponse(address, userId, List.of()));
    }

    public MissionResDTO.SetMissionDoneResponse completeMission(Long userId, Long missionId) {
        return new MissionResDTO.SetMissionDoneResponse(userId, missionId, true);
    }

    @Transactional
    public MissionResDTO.PostMissionReviewResponse postMissionReview(Long userId, Long missionId, MissionReqDTO.ReviewRequest review) {
        Mission mission = missionRepository.findById(missionId)
                .orElseThrow(() -> new ProjectException(MissionErrorCode.MISSION_NOT_FOUND));

        if (reviewRepository.existsActiveReview(missionId, userId)) {
            throw new ProjectException(MissionErrorCode.REVIEW_ALREADY_EXISTS);
        }

        Review newReview = Review.create(
                missionId,
                mission.getStoreId(),
                userId,
                review.description(),
                review.score(),
                review.photoUrl()
        );
        Review savedReview = reviewRepository.save(newReview);

        return new MissionResDTO.PostMissionReviewResponse(userId, missionId, savedReview.getId());
    }
}
