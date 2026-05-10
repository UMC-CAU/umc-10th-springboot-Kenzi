package com.example.umc10th.missions.repository;

import com.example.umc10th.missions.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ReviewRepository extends JpaRepository<Review,Long> {
    @Query("""
            SELECT CASE WHEN COUNT(r) > 0 THEN true ELSE false END
            FROM Review r
            WHERE r.missionId = :missionId
              AND r.userId = :userId
              AND r.deletedAt IS NULL
            """)
    boolean existsActiveReview(@Param("missionId") Long missionId, @Param("userId") Long userId);

    @Query("""
            SELECT r
            FROM Review r
            WHERE r.missionId = :missionId
              AND r.userId = :userId
              AND r.deletedAt IS NULL
            """)
    Optional<Review> findActiveReview(@Param("missionId") Long missionId, @Param("userId") Long userId);
}
