-- ============================================================
--  MySQL Schema
--  지역 미션 수락 포인트 서비스
-- ============================================================

SET FOREIGN_KEY_CHECKS = 0;

-- ------------------------------------------------------------
-- Address (주소 - 법정동 기준)
-- ------------------------------------------------------------
CREATE TABLE `Address` (
                           `code`         VARCHAR(20)  NOT NULL COMMENT '법정동 코드',
                           `parent_code`  VARCHAR(20)  NOT NULL COMMENT '부모 주소 코드',
                           `sido_code`    VARCHAR(20)  NOT NULL COMMENT '시도 코드',
                           `sigungu_code` VARCHAR(20)  NOT NULL COMMENT '시군구 코드',
                           `emd_code`     VARCHAR(20)  NOT NULL COMMENT '읍면동 코드',
                           `address_name` VARCHAR(100) NOT NULL COMMENT '주소 이름',
                           PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ------------------------------------------------------------
-- food_category (음식 카테고리)
-- ------------------------------------------------------------
CREATE TABLE `food_category` (
                                 `id`        BIGINT       NOT NULL AUTO_INCREMENT,
                                 `food_name` VARCHAR(50)  NOT NULL,
                                 PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ------------------------------------------------------------
-- User (사용자)
-- ------------------------------------------------------------
CREATE TABLE `User` (
                        `id`           VARCHAR(36)  NOT NULL COMMENT 'UUID',
                        `address_code` VARCHAR(20)  NOT NULL COMMENT '주소의 법정동 코드',
                        `name`         VARCHAR(50)  NOT NULL,
                        `email`        VARCHAR(100) NOT NULL,
                        `age`          INT          NOT NULL,
                        `role`         ENUM('CEO','ADMIN','USER') NOT NULL DEFAULT 'USER',
                        `point`        INT          NOT NULL DEFAULT 0,
                        `created_at`   DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP,
                        `deleted_at`   DATETIME     NULL,
                        PRIMARY KEY (`id`),
                        UNIQUE KEY `UQ_USER_EMAIL` (`email`),
                        CONSTRAINT `FK_Address_TO_User`
                            FOREIGN KEY (`address_code`) REFERENCES `Address` (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ------------------------------------------------------------
-- User_Interest (사용자 관심 음식 카테고리 - 다대다)
-- ------------------------------------------------------------
CREATE TABLE `User_Interest` (
                                 `user_id` VARCHAR(36) NOT NULL,
                                 `food_id` BIGINT      NOT NULL,
                                 PRIMARY KEY (`user_id`, `food_id`),
                                 CONSTRAINT `FK_User_TO_User_Interest`
                                     FOREIGN KEY (`user_id`) REFERENCES `User` (`id`),
                                 CONSTRAINT `FK_food_category_TO_User_Interest`
                                     FOREIGN KEY (`food_id`) REFERENCES `food_category` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ------------------------------------------------------------
-- Store (가게)
-- ------------------------------------------------------------
CREATE TABLE `Store` (
                         `id`              VARCHAR(36)  NOT NULL COMMENT 'UUID',
                         `address_code`    VARCHAR(20)  NOT NULL,
                         `ceo_id`          VARCHAR(36)  NOT NULL COMMENT '가게 사장 아이디',
                         `food_id`         BIGINT       NOT NULL,
                         `name`            VARCHAR(100) NOT NULL,
                         `store_photo_url` VARCHAR(500) NOT NULL,
                         `introduction`    TEXT         NOT NULL,
                         PRIMARY KEY (`id`),
                         CONSTRAINT `FK_Address_TO_Store`
                             FOREIGN KEY (`address_code`) REFERENCES `Address` (`code`),
                         CONSTRAINT `FK_User_TO_Store`
                             FOREIGN KEY (`ceo_id`) REFERENCES `User` (`id`),
                         CONSTRAINT `FK_food_category_TO_Store`
                             FOREIGN KEY (`food_id`) REFERENCES `food_category` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ------------------------------------------------------------
-- Mission (미션)
-- ------------------------------------------------------------
CREATE TABLE `Mission` (
                           `id`          VARCHAR(36)  NOT NULL COMMENT 'UUID',
                           `store_id`    VARCHAR(36)  NOT NULL,
                           `point`       INT          NOT NULL COMMENT '미션 완료 시 지급 포인트',
                           `description` TEXT         NOT NULL,
                           PRIMARY KEY (`id`),
                           CONSTRAINT `FK_Store_TO_Mission`
                               FOREIGN KEY (`store_id`) REFERENCES `Store` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ------------------------------------------------------------
-- MissionAccepted (미션 수락)
-- ------------------------------------------------------------
CREATE TABLE `MissionAccepted` (
                                   `id`           VARCHAR(36) NOT NULL COMMENT 'UUID',
                                   `user_id`      VARCHAR(36) NOT NULL,
                                   `mission_id`   VARCHAR(36) NOT NULL,
                                   `created_at`   DATETIME    NOT NULL DEFAULT CURRENT_TIMESTAMP,
                                   `deleted_at`   DATETIME    NULL,
                                   `is_completed` BOOLEAN     NOT NULL DEFAULT FALSE COMMENT '미션 완료 여부',
                                   `completed_at` DATETIME    NULL,
                                   PRIMARY KEY (`id`),
                                   CONSTRAINT `FK_User_TO_MissionAccepted`
                                       FOREIGN KEY (`user_id`) REFERENCES `User` (`id`),
                                   CONSTRAINT `FK_Mission_TO_MissionAccepted`
                                       FOREIGN KEY (`mission_id`) REFERENCES `Mission` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ------------------------------------------------------------
-- Review (리뷰)
-- ------------------------------------------------------------
CREATE TABLE `Review` (
                          `id`          VARCHAR(36)   NOT NULL COMMENT 'UUID',
                          `mission_id`  VARCHAR(36)   NOT NULL,
                          `store_id`    VARCHAR(36)   NOT NULL,
                          `user_id`     VARCHAR(36)   NOT NULL,
                          `description` TEXT          NOT NULL COMMENT '리뷰 내용',
                          `score`       DECIMAL(2,1)  NOT NULL COMMENT '별점 (1.0 ~ 5.0)',
                          `photo_url`   VARCHAR(500)  NOT NULL,
                          `created_at`  DATETIME      NOT NULL DEFAULT CURRENT_TIMESTAMP,
                          `deleted_at`  DATETIME      NULL,
                          PRIMARY KEY (`id`),
                          CONSTRAINT `FK_Mission_TO_Review`
                              FOREIGN KEY (`mission_id`) REFERENCES `Mission` (`id`),
                          CONSTRAINT `FK_Store_TO_Review`
                              FOREIGN KEY (`store_id`) REFERENCES `Store` (`id`),
                          CONSTRAINT `FK_User_TO_Review`
                              FOREIGN KEY (`user_id`) REFERENCES `User` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ------------------------------------------------------------
-- ScoreAddress (지역별 점수)  ← SocreAddress 오타 수정
-- ------------------------------------------------------------
CREATE TABLE `ScoreAddress` (
                                `code`    VARCHAR(20) NOT NULL,
                                `user_id` VARCHAR(36) NOT NULL,
                                `score`   INT         NOT NULL DEFAULT 0,
                                PRIMARY KEY (`code`, `user_id`),
                                CONSTRAINT `FK_Address_TO_ScoreAddress`
                                    FOREIGN KEY (`code`) REFERENCES `Address` (`code`),
                                CONSTRAINT `FK_User_TO_ScoreAddress`
                                    FOREIGN KEY (`user_id`) REFERENCES `User` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ------------------------------------------------------------
-- Notification (알림)
-- ------------------------------------------------------------
CREATE TABLE `Notification` (
                                `id`          VARCHAR(36)  NOT NULL COMMENT 'UUID',
                                `user_id`     VARCHAR(36)  NOT NULL,
                                `type`        VARCHAR(50)  NOT NULL COMMENT '알림 유형',
                                `header`      VARCHAR(255) NOT NULL COMMENT '알림 제목',
                                `description` TEXT         NOT NULL COMMENT '알림 내용',
                                `created_at`  DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP,
                                `deleted_at`  DATETIME     NULL,
                                `is_read`     BOOLEAN      NOT NULL DEFAULT FALSE COMMENT '읽음 여부',
                                PRIMARY KEY (`id`),
                                CONSTRAINT `FK_User_TO_Notification`
                                    FOREIGN KEY (`user_id`) REFERENCES `User` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ------------------------------------------------------------
-- RefreshToken (리프레시 토큰)
-- ------------------------------------------------------------
CREATE TABLE `RefreshToken` (
                                `id`         VARCHAR(36)  NOT NULL COMMENT 'UUID',
                                `user_id`    VARCHAR(36)  NOT NULL,
                                `token_hash` VARCHAR(255) NOT NULL COMMENT '해시된 토큰 값',
                                `revoked_at` DATETIME     NULL     COMMENT '폐기 시각',
                                `expires_at` DATETIME     NOT NULL COMMENT '만료 시각',  -- expiresed_at 오타 수정
                                `created_at` DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '생성 시각',
                                PRIMARY KEY (`id`),
                                CONSTRAINT `FK_User_TO_RefreshToken`
                                    FOREIGN KEY (`user_id`) REFERENCES `User` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

SET FOREIGN_KEY_CHECKS = 1;