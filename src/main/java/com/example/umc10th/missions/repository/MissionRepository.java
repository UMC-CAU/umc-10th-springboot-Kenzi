package com.example.umc10th.missions.repository;

import com.example.umc10th.missions.entity.Mission;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MissionRepository extends JpaRepository<Mission, Long> {
    List<Mission> findAllByStoreIdIn(List<Long> storeIds);
}
