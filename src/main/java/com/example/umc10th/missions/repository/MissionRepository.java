package com.example.umc10th.missions.repository;

import com.example.umc10th.missions.entity.Mission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MissionRepository extends JpaRepository<Mission, Long> {
    interface AvailableMissionProjection {
        Long getMissionId();
        Integer getPoint();
        String getDescription();
        String getStoreName();
    }

    @Query(value = """
            SELECT m.id AS missionId,
                   m.point AS point,
                   m.description AS description,
                   s.name AS storeName
            FROM mission m
            JOIN store s ON s.id = m.store_id
            LEFT JOIN mission_accepted ma
                   ON ma.mission_id = m.id
                  AND ma.user_id = :userId
            WHERE s.address_code = :addressCode
              AND ma.id IS NULL
            """, nativeQuery = true)
    List<AvailableMissionProjection> findAvailableMissions(@Param("addressCode") String addressCode, @Param("userId") Long userId);
}
