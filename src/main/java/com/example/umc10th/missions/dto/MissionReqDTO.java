package com.example.umc10th.missions.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;


@Getter
@NoArgsConstructor
public class MissionReqDTO {

    public record GetMissionListRequest(
            @Schema(description = "주소 코드", example = "A1")
            @NotBlank
            String addressCode,
            @Schema(description = "유저 ID", example = "1")
            @NotNull
            @Positive
            Long userId
    ) {
    }

    public record GetMissionDoingRequest(
            @Schema(description = "주소 ID", example = "1")
            @NotNull
            @Positive
            Long address,
            @Schema(description = "유저 ID", example = "1")
            @NotNull
            @Positive
            Long userId,
            @Schema(description = "페이지 번호(0부터 시작, 미입력 시 0)", example = "0")
            @PositiveOrZero
            Integer page,
            @Schema(description = "페이지 크기(미입력 시 10)", example = "10")
            @Positive
            Integer size,
            @Schema(description = "내림차순 정렬 기준", example = "id")
            String sort
    ) {
    }

    public record GetMissionDoneRequest(
            @Schema(description = "주소 ID", example = "1")
            @NotNull
            @Positive
            Long address,
            @Schema(description = "유저 ID", example = "1")
            @NotNull
            @Positive
            Long userId,
            @Schema(description = "페이지 번호(0부터 시작, 미입력 시 0)", example = "0")
            @PositiveOrZero
            Integer page,
            @Schema(description = "페이지 크기(미입력 시 10)", example = "10")
            @Positive
            Integer size,
            @Schema(description = "내림차순 정렬 기준", example = "id")
            String sort
    ) {
    }

    public record GetUserReviewRequest(
            @Schema(description = "유저 ID", example = "1")
            @NotNull
            @Positive
            Long userId,
            @Schema(description = "커서(sort=id: 리뷰 ID, sort=score: 점수)", example = "5.0")
            String cursor,
            @Schema(description = "조회 크기(미입력 시 10)", example = "10")
            @Positive
            Integer size,
            @Schema(description = "정렬 기준(id, score)", example = "score", allowableValues = {"id", "score"})
            String sort
    ) {
    }

    public record CompleteMissionsRequest(
            @NotNull
            @Positive
            Long userId,
            @NotNull
            @Positive
            Long missionId
    ) {
    }

    public record PostMissionReviewRequest(
            @NotNull
            @Positive
            Long userId,
            @NotNull
            @Positive
            Long missionId,
            @NotNull
            @Valid
            ReviewRequest review
    ) {
    }

    public record ReviewRequest(
            @Schema(description = "미션 ID", example = "1")
            @Positive
            Long missionId,
            @Schema(description = "스토어 ID", example = "10")
            @Positive
            Long storeId,
            @Schema(description = "유저 ID", example = "2")
            @Positive
            Long userId,
            @Schema(description = "리뷰 내용", example = "좋은 경험이었습니다.")
            @NotBlank
            String description,
            @Schema(description = "점수", example = "4.5")
            @NotNull
            @DecimalMin("0.0")
            @DecimalMax("5.0")
            BigDecimal score,
            @Schema(description = "사진 URL", example = "http://example.com/photo.jpg")
            @NotBlank
            @Size(max = 500)
            String photoUrl
    ) {
    }
}
