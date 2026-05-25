package com.example.umc10th.users.controller;

import com.example.umc10th.global.response.ApiResponse;
import com.example.umc10th.users.dto.UserReqDTO;
import com.example.umc10th.users.dto.UserResDTO;
import com.example.umc10th.users.entity.AuthMember;
import com.example.umc10th.users.enums.UserSuccessCode;
import com.example.umc10th.users.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Users", description = "유저 API")
@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
@SecurityRequirement(name = "JWT TOKEN")
public class UserController {

    private final UserService userService;

    @Operation(summary = "내 정보 조회", description = "JWT 토큰으로 내 정보를 조회합니다.")
    @GetMapping("/me")
    public ApiResponse<UserResDTO.GetInfoResponse> me(@AuthenticationPrincipal AuthMember authMember) {
        return ApiResponse.success(UserSuccessCode.USER_SUCCESS_FOUND, userService.getMe(authMember));
    }

    @Operation(summary = "회원가입", description = "신규 유저를 회원가입 처리합니다.")
    @PostMapping("/signup")
    public ApiResponse<UserResDTO.GetInfoResponse> signup(@RequestBody UserReqDTO.SignupRequest reqDTO) {
        return ApiResponse.success(UserSuccessCode.USER_SUCCESS_SINGUP, userService.signUp(reqDTO));
    }

    @Operation(summary = "로그인", description = "이메일과 비밀번호로 로그인합니다.")
    @PostMapping("/login")
    public ApiResponse<UserResDTO.LoginResponse> login(@RequestBody UserReqDTO.LoginRequest reqDTO) {
        return ApiResponse.success(UserSuccessCode.USER_SUCCESS_LOGIN, userService.login(reqDTO));
    }

    @Operation(summary = "회원 탈퇴", description = "유저를 탈퇴 처리합니다.")
    @PatchMapping("/witdraw")
    public ApiResponse<UserResDTO.GetInfoResponse> withdraw(@RequestBody UserReqDTO.GetInfoRequest reqDTO) {
        return ApiResponse.success(UserSuccessCode.USER_SUCCESS_WITHDRAW, userService.withdraw(reqDTO.userId()));
    }

    @Operation(summary="해당 주소의 나의 점수 조회", description = "유저의 해당 주소에서의 점수를 조회합니다.")
    @GetMapping("/addressscore")
    public ApiResponse<UserResDTO.AddressScoreResponse> getAddressScore(@RequestParam("userId") Long userId , @RequestParam("addresscode") String addresscode) {
        return ApiResponse.success(UserSuccessCode.USER_SUCCESS_ADRESSSCORE_CODE, userService.getAddressScore(userId, addresscode));
    }
}
