package com.example.umc10th.store.controller;

import com.example.umc10th.global.response.ApiResponse;
import com.example.umc10th.store.dto.StoreResDTO;
import com.example.umc10th.store.enums.StoreSuccessCode;
import com.example.umc10th.store.service.StoreService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "Store", description = "가게 API")
@RestController
@RequestMapping("/api/v1/stores")
@RequiredArgsConstructor
public class StoreController {

    private final StoreService storeService;

    @Operation(summary = "가게 목록 조회", description = "주소 코드로 가게 목록을 조회합니다.")
    @GetMapping
    public ApiResponse<List<StoreResDTO.GetStoreList>> getStoreList(@RequestParam String addressCode) {
        return ApiResponse.success(StoreSuccessCode.STORE_LIST_FOUND, storeService.getStoresByAddressCode(addressCode));
    }

    @Operation(summary = "가게 상세 조회", description = "가게 ID로 상세 정보를 조회합니다.")
    @GetMapping("/detail")
    public ApiResponse<StoreResDTO.GetStoreDetail> getStoreDetail(@RequestParam String storeId) {
        return ApiResponse.success(StoreSuccessCode.STORE_DETAIL_FOUND, storeService.getStoreDetail(storeId));
    }
}
