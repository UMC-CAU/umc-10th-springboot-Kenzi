package com.example.umc10th.missions.repository;

import com.example.umc10th.missions.entity.MissionAccept;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MissionAcceptRepository extends JpaRepository<MissionAccept, Long> {

    @Query(
            value = """
                    SELECT ma.missionId
                    FROM MissionAccept ma
                    WHERE ma.userId = :userId
                      AND ma.isCompleted IS FALSE 
                    """,
            countQuery = """
                    SELECT COUNT(ma)
                    FROM MissionAccept ma
                    WHERE ma.userId = :userId
                      AND ma.isCompleted IS FALSE 
                    """
    )
    Page<Long> findDoingMissionIds(@Param("userId") Long userId, Pageable pageable);

    @Query(
            value = """
                    SELECT ma.missionId
                    FROM MissionAccept ma
                    WHERE ma.userId = :userId
                      AND ma.isCompleted = true
                    """,
            countQuery = """
                    SELECT COUNT(ma)
                    FROM MissionAccept ma
                    WHERE ma.userId = :userId
                      AND ma.isCompleted = true
                    """
    )
    Page<Long> findDoneMissionIds(@Param("userId") Long userId, Pageable pageable);
}
