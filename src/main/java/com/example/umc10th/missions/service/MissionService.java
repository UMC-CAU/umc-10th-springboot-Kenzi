package com.example.umc10th.missions.service;

import com.example.umc10th.global.exception.ProjectException;
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
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
                .map(mission -> new MissionResDTO.AvailableMission(
                        mission.getMissionId(),
                        mission.getPoint(),
                        mission.getDescription(),
                        mission.getStoreName()
                ))
                .toList();
        if (availableMissions.isEmpty()) {
            throw new ProjectException(MissionErrorCode.AVAILABLE_MISSION_NOT_FOUND);
        }

        return List.of(new MissionResDTO.GetMissionListResponse(addressCode, userId, availableMissions));
    }

    public List<MissionResDTO.GetMissionDoingResponse> getMissionDoing(Long userId, Long address, Integer page, Integer size) {
        if (page != null && page < 0) {
            throw new ProjectException(MissionErrorCode.INVALID_PAGINATION_REQUEST);
        }
        if (size != null && size < 1) {
            throw new ProjectException(MissionErrorCode.INVALID_PAGINATION_REQUEST);
        }
        if (!userRepository.existsById(userId)) {
            throw new ProjectException(MissionErrorCode.MISSION_USER_NOT_FOUND);
        }

        Pageable pageable = PageRequest.of(page == null ? 0 : page, size == null ? 10 : size);
        List<MissionResDTO.AvailableMission> doingMissions = missionAcceptRepository.findDoingMissions(userId, pageable)
                .getContent()
                .stream()
                .map(mission -> new MissionResDTO.AvailableMission(
                        mission.getMissionId(),
                        mission.getPoint(),
                        mission.getDescription(),
                        mission.getStoreName()
                ))
                .toList();
        if (doingMissions.isEmpty()) {
            throw new ProjectException(MissionErrorCode.MISSION_DOING_NOT_FOUND);
        }
        return List.of(new MissionResDTO.GetMissionDoingResponse(address, userId, doingMissions));
    }

    public List<MissionResDTO.GetMissionDoneResponse> getMissionDone(Long userId, Long address, Integer page, Integer size) {
        if (page != null && page < 0) {
            throw new ProjectException(MissionErrorCode.INVALID_PAGINATION_REQUEST);
        }
        if (size != null && size < 1) {
            throw new ProjectException(MissionErrorCode.INVALID_PAGINATION_REQUEST);
        }
        if (!userRepository.existsById(userId)) {
            throw new ProjectException(MissionErrorCode.MISSION_USER_NOT_FOUND);
        }

        Pageable pageable = PageRequest.of(page == null ? 0 : page, size == null ? 10 : size);
        List<MissionResDTO.AvailableMission> doneMissions = missionAcceptRepository.findDoneMissions(userId, pageable)
                .getContent()
                .stream()
                .map(mission -> new MissionResDTO.AvailableMission(
                        mission.getMissionId(),
                        mission.getPoint(),
                        mission.getDescription(),
                        mission.getStoreName()
                ))
                .toList();
        if (doneMissions.isEmpty()) {
            throw new ProjectException(MissionErrorCode.MISSION_DONE_NOT_FOUND);
        }
        return List.of(new MissionResDTO.GetMissionDoneResponse(address, userId, doneMissions));
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
