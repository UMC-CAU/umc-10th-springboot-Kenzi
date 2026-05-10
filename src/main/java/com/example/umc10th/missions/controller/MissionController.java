package com.example.umc10th.missions.controller;

import com.example.umc10th.global.response.ApiResponse;
import com.example.umc10th.missions.dto.MissionReqDTO;
import com.example.umc10th.missions.dto.MissionResDTO;
import com.example.umc10th.missions.entity.Mission;
import com.example.umc10th.missions.enums.MissionSuccessCode;
import com.example.umc10th.missions.service.MissionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Missions", description = "미션 API")
@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class MissionController {

    private final MissionService missionService;


    @Operation(summary = "수락 가능한 미션 목록 조회", description = "addressCode에 해당하는 store들의 mission 중, 해당 user가 아직 수락하지 않은 미션만 조회합니다.")
    @PostMapping("/missions")
    public ApiResponse<List<MissionResDTO.GetMissionListResponse>> getMissionList(
            @RequestBody @Valid MissionReqDTO.GetMissionListRequest request
    ) {
        List<MissionResDTO.GetMissionListResponse> missions = missionService.getMissionList(request.addressCode(), request.userId());
        return ApiResponse.success(MissionSuccessCode.MISSION_LIST_FOUND, missions);
    }

    @Operation(summary = "진행 중 미션 조회", description = "유저의 진행 중인 미션을 조회합니다. request body(page, size, sort)로 페이지네이션 및 정렬합니다.")
    @PostMapping("/missions/doing")
    public ApiResponse<List<MissionResDTO.GetMissionDoingResponse>> getMissionDoing(
            @RequestBody @Valid MissionReqDTO.GetMissionDoingRequest request
    ) {
        List<MissionResDTO.GetMissionDoingResponse> missions = missionService.getMissionDoing(
                request.userId(),
                request.address(),
                request.page(),
                request.size(),
                request.sort()
        );
        return ApiResponse.success(MissionSuccessCode.MISSION_DOING_FOUND, missions);
    }

    @Operation(summary = "완료 미션 조회", description = "유저의 완료한 미션을 조회합니다. request body(page, size, sort)로 페이지네이션 및 정렬합니다.")
    @PostMapping("/missions/done")
    public ApiResponse<List<MissionResDTO.GetMissionDoneResponse>> getMissionDone(
            @RequestBody @Valid MissionReqDTO.GetMissionDoneRequest request
    ) {
        List<MissionResDTO.GetMissionDoneResponse> missions = missionService.getMissionDone(
                request.userId(),
                request.address(),
                request.page(),
                request.size(),
                request.sort()
        );
        return ApiResponse.success(MissionSuccessCode.MISSION_DONE_FOUND, missions);
    }

    @Operation(summary = "미션 완료 처리", description = "미션 완료 상태로 변경합니다.")
    @PatchMapping("/missions/complete")
    public ApiResponse<MissionResDTO.SetMissionDoneResponse> completeMission(@RequestBody @Valid MissionReqDTO.CompleteMissionsRequest completeMission) {
        MissionResDTO.SetMissionDoneResponse mission = missionService.completeMission(completeMission.userId() , completeMission.missionId());
        return ApiResponse.success(MissionSuccessCode.MISSION_COMPLETED, mission);
    }

    @Operation(summary = "미션 리뷰 등록", description = "미션 리뷰를 등록합니다.")
    @PostMapping("/missions/review")
    public ApiResponse<MissionResDTO.PostMissionReviewResponse> postMissionReview(@RequestBody @Valid MissionReqDTO.PostMissionReviewRequest missionReview) {
        MissionResDTO.PostMissionReviewResponse mission = missionService.postMissionReview(missionReview.userId() , missionReview.missionId() , missionReview.review());
        return ApiResponse.success(MissionSuccessCode.MISSION_REVIEW_CREATED, mission);
    }

    @Operation(summary = "유저 리뷰 조회", description = "userId에 해당하는 리뷰를 request body와 cursor 기반 Slice로 조회합니다. sort=id 또는 sort=score를 지원합니다.")
    @PostMapping("/missions/reviews")
    public ApiResponse<List<MissionResDTO.GetUserReviewResponse>> getUserReviews(
            @RequestBody @Valid MissionReqDTO.GetUserReviewRequest request
    ) {
        List<MissionResDTO.GetUserReviewResponse> reviews = missionService.getUserReviews(
                request.userId(),
                request.cursor(),
                request.size(),
                request.sort()
        );
        return ApiResponse.success(MissionSuccessCode.MISSION_REVIEW_FOUND, reviews);
    }
}
