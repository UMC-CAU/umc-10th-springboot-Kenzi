package com.example.umc10th.missions.repository;

import com.example.umc10th.missions.entity.Review;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
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

    Optional<Review> findByIdAndUserIdAndDeletedAtIsNull(Long id, Long userId);

    @Query("""
            SELECT r
            FROM Review r
            WHERE r.userId = :userId
              AND r.deletedAt IS NULL
              AND (:cursor IS NULL OR r.id <= :cursor)
            ORDER BY r.id DESC
            """)
    Slice<Review> findUserReviewsOrderById(
            @Param("userId") Long userId,
            @Param("cursor") Long cursor,
            Pageable pageable
    );

    @Query("""
            SELECT r
            FROM Review r
            WHERE r.userId = :userId
              AND r.deletedAt IS NULL
              AND (
                    :cursorScore IS NULL
                    OR r.score <= :cursorScore
              )
            ORDER BY r.score DESC, r.id DESC
            """)
    Slice<Review> findUserReviewsOrderByScore(
            @Param("userId") Long userId,
            @Param("cursorScore") BigDecimal cursorScore,
            Pageable pageable
    );
}
