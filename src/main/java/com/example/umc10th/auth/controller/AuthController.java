package com.example.umc10th.auth.controller;

import com.example.umc10th.auth.entity.RefreshToken;
import com.example.umc10th.auth.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "Auth", description = "인증 API")
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @Operation(summary = "전체 리프레시 토큰 조회", description = "등록된 모든 리프레시 토큰을 조회합니다.")
    @GetMapping
    public ResponseEntity<List<RefreshToken>> getAll() {
        return ResponseEntity.ok(authService.getAll());
    }
}
