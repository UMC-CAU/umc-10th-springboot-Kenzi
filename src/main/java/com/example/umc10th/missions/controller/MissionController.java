package com.example.umc10th.missions.controller;

import com.example.umc10th.global.response.ApiResponse;
import com.example.umc10th.missions.dto.MissionReqDTO;
import com.example.umc10th.missions.dto.MissionResDTO;
import com.example.umc10th.missions.entity.Mission;
import com.example.umc10th.missions.enums.MissionSuccessCode;
import com.example.umc10th.missions.service.MissionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Missions", description = "미션 API")
@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class MissionController {

    private final MissionService missionService;


    @Operation(summary = "미션 목록 조회", description = "주소 기준 미션 목록을 조회합니다.")
    @GetMapping("/missions")
    public ApiResponse<List<MissionResDTO.GetMissionListResponse>> getMissionList(@RequestBody MissionReqDTO.GetMissionListRequest missionReqDTO) {
        List<MissionResDTO.GetMissionListResponse> missions = missionService.getMissionList(missionReqDTO.address());
        return ApiResponse.success(MissionSuccessCode.MISSION_LIST_FOUND, missions);
    }

    @Operation(summary = "진행 중 미션 조회", description = "유저의 진행 중인 미션을 조회합니다. query parameter(page, size)로 페이지네이션합니다.")
    @GetMapping("/missions/doing")
    public ApiResponse<List<MissionResDTO.GetMissionDoingResponse>> getMissionDoing(
            @Parameter(description = "주소 ID", example = "1", required = true)
            @RequestParam Long address,
            @Parameter(description = "유저 ID", example = "1", required = true)
            @RequestParam Long userId,
            @Parameter(description = "페이지 번호(0부터 시작)", example = "0")
            @RequestParam(required = false) Integer page,
            @Parameter(description = "페이지 크기", example = "10")
            @RequestParam(required = false) Integer size
    ) {
        List<MissionResDTO.GetMissionDoingResponse> missions = missionService.getMissionDoing(
                userId,
                address,
                page,
                size
        );
        return ApiResponse.success(MissionSuccessCode.MISSION_DOING_FOUND, missions);
    }

    @Operation(summary = "완료 미션 조회", description = "유저의 완료한 미션을 조회합니다. query parameter(page, size)로 페이지네이션합니다.")
    @GetMapping("/missions/done")
    public ApiResponse<List<MissionResDTO.GetMissionDoneResponse>> getMissionDone(
            @Parameter(description = "주소 ID", example = "1", required = true)
            @RequestParam Long address,
            @Parameter(description = "유저 ID", example = "1", required = true)
            @RequestParam Long userId,
            @Parameter(description = "페이지 번호(0부터 시작)", example = "0")
            @RequestParam(required = false) Integer page,
            @Parameter(description = "페이지 크기", example = "10")
            @RequestParam(required = false) Integer size
    ) {
        List<MissionResDTO.GetMissionDoneResponse> missions = missionService.getMissionDone(
                userId,
                address,
                page,
                size
        );
        return ApiResponse.success(MissionSuccessCode.MISSION_DONE_FOUND, missions);
    }

    @Operation(summary = "미션 완료 처리", description = "미션 완료 상태로 변경합니다.")
    @PatchMapping("/missions/complete")
    public ApiResponse<MissionResDTO.SetMissionDoneResponse> completeMission(@RequestBody MissionReqDTO.CompleteMissionsRequest completeMission) {
        MissionResDTO.SetMissionDoneResponse mission = missionService.completeMission(completeMission.userId() , completeMission.missionId());
        return ApiResponse.success(MissionSuccessCode.MISSION_COMPLETED, mission);
    }

    @Operation(summary = "미션 리뷰 등록", description = "미션 리뷰를 등록합니다.")
    @PostMapping("/missions/review")
    public ApiResponse<MissionResDTO.PostMissionReviewResponse> postMissionReview(@RequestBody MissionReqDTO.PostMissionReviewRequest missionReview) {
        MissionResDTO.PostMissionReviewResponse mission = missionService.postMissionReview(missionReview.userId() , missionReview.missionId() , missionReview.review());
        return ApiResponse.success(MissionSuccessCode.MISSION_REVIEW_CREATED, mission);
    }
}
