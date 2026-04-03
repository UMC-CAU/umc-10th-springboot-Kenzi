package com.example.umc10th.missions.controller;

import com.example.umc10th.missions.entity.Mission;
import com.example.umc10th.missions.service.MissionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "Missions", description = "미션 API")
@RestController
@RequestMapping("/missions")
@RequiredArgsConstructor
public class MissionController {

    private final MissionService missionService;

    @Operation(summary = "전체 미션 조회", description = "등록된 모든 미션을 조회합니다.")
    @GetMapping
    public ResponseEntity<List<Mission>> getAll() {
        return ResponseEntity.ok(missionService.getAll());
    }
}
