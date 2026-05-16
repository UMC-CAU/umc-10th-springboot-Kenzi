package com.example.umc10th.missions.repository;

import com.example.umc10th.missions.entity.MissionAccept;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MissionAcceptRepository extends JpaRepository<MissionAccept, Long> {
    interface AcceptedMissionProjection {
        Long getMissionId();
        Integer getPoint();
        String getDescription();
        String getStoreName();
    }

    @Query(
            value = """
                    SELECT m.id AS missionId,
                           ma.id AS id,
                           ma.created_at AS createdAt,
                           ma.completed_at AS completedAt,
                           m.point AS point,
                           m.description AS description,
                           s.name AS storeName
                    FROM mission_accepted ma
                    JOIN mission m ON m.id = ma.mission_id
                    JOIN store s ON s.id = m.store_id
                    WHERE ma.user_id = :userId
                      AND ma.is_completed = false
                    """,
            countQuery = """
                    SELECT COUNT(*)
                    FROM mission_accepted ma
                    WHERE ma.user_id = :userId
                      AND ma.is_completed = false
                    """
            ,
            nativeQuery = true
    )
    Page<AcceptedMissionProjection> findDoingMissions(@Param("userId") Long userId, Pageable pageable);

    @Query(
            value = """
                    SELECT m.id AS missionId,
                           ma.id AS id,
                           ma.created_at AS createdAt,
                           ma.completed_at AS completedAt,
                           m.point AS point,
                           m.description AS description,
                           s.name AS storeName
                    FROM mission_accepted ma
                    JOIN mission m ON m.id = ma.mission_id
                    JOIN store s ON s.id = m.store_id
                    WHERE ma.user_id = :userId
                      AND ma.is_completed = true
                    """,
            countQuery = """
                    SELECT COUNT(*)
                    FROM mission_accepted ma
                    WHERE ma.user_id = :userId
                      AND ma.is_completed = true
                    """
            ,
            nativeQuery = true
    )
    Page<AcceptedMissionProjection> findDoneMissions(@Param("userId") Long userId, Pageable pageable);
}
