package com.example.umc10th.missions.repository;

import com.example.umc10th.missions.entity.Mission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MissionRepository extends JpaRepository<Mission, String> {
}
