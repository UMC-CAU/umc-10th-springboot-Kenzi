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

    public List<MissionResDTO.getMissionList> getMissionList(Long address) {
        return List.of(new MissionResDTO.getMissionList(address, List.of()));
    }

    public List<MissionResDTO.getMissionDoing> getMissionDoing(Long userId, Long address) {
        return List.of(new MissionResDTO.getMissionDoing(address, userId, List.of()));
    }

    public List<MissionResDTO.getMissionDone> getMissionDone(Long userId, Long address) {
        return List.of(new MissionResDTO.getMissionDone(address, userId, List.of()));
    }

    public MissionResDTO.setMissionDone completeMission(Long userId, Long missionId) {
        return new MissionResDTO.setMissionDone(userId, missionId, true);
    }

    public MissionResDTO.postMissionReview postMissionReview(Long userId, Long missionId, Review review) {
        return new MissionResDTO.postMissionReview(userId, missionId, null);
    }
}
