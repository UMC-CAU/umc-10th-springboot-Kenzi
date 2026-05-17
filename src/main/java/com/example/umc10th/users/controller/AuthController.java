package com.example.umc10th.users.controller;

import com.example.umc10th.global.response.ApiResponse;
import com.example.umc10th.users.dto.UserReqDTO;
import com.example.umc10th.users.dto.UserResDTO;
import com.example.umc10th.users.enums.UserSuccessCode;
import com.example.umc10th.users.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Auth", description = "인증 API")
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;

    @Operation(summary = "회원가입", description = "신규 유저를 회원가입 처리합니다.")
    @PostMapping("/sign-up")
    public ApiResponse<UserResDTO.GetInfoResponse> signUp(@RequestBody UserReqDTO.SignupRequest reqDTO) {
        return ApiResponse.success(UserSuccessCode.USER_SUCCESS_SINGUP, userService.signUp(reqDTO));
    }
}
