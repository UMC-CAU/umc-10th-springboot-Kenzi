package com.example.umc10th.missions.service;

import com.example.umc10th.missions.dto.MissionResDTO;
import com.example.umc10th.missions.entity.Mission;
import com.example.umc10th.missions.entity.Review;
import com.example.umc10th.missions.repository.MissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MissionService {

    private final MissionRepository missionRepository;

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

    public MissionResDTO.PostMissionReviewResponse postMissionReview(Long userId, Long missionId, Review review) {
        return new MissionResDTO.PostMissionReviewResponse(userId, missionId, null);
    }
}
