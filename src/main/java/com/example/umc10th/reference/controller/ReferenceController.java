package com.example.umc10th.reference.controller;

import com.example.umc10th.reference.entity.Address;
import com.example.umc10th.reference.service.ReferenceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "Reference", description = "레퍼런스 API")
@RestController
@RequestMapping("/reference")
@RequiredArgsConstructor
public class ReferenceController {

    private final ReferenceService referenceService;

    @Operation(summary = "전체 주소 조회", description = "등록된 모든 주소(법정동) 정보를 조회합니다.")
    @GetMapping
    public ResponseEntity<List<Address>> getAll() {
        return ResponseEntity.ok(referenceService.getAll());
    }
}
