package com.example.umc10th.missions.service;

import com.example.umc10th.missions.entity.Mission;
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
}
