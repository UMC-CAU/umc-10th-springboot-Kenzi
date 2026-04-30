package com.example.umc10th.users.controller;

import com.example.umc10th.global.code.BaseSuccessCode;
import com.example.umc10th.global.code.GeneralSuccessCode;
import com.example.umc10th.global.response.ApiResponse;
import com.example.umc10th.users.dto.UserReqDTO;
import com.example.umc10th.users.dto.UserResDTO;
import com.example.umc10th.users.enums.UserSuccessCode;
import com.example.umc10th.users.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Tag(name = "Users", description = "유저 API")
@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/me")
    public ApiResponse<UserResDTO.GetInfo> me(@RequestBody UserReqDTO.GetInfo reqDTO) {
        return ApiResponse.success(UserSuccessCode.USER_SUCCESS_FOUND, userService.getMe(reqDTO.userId()));
    }

    @PostMapping("/signup")
    public ApiResponse<UserResDTO.GetInfo> signup(@RequestBody UserReqDTO.Signup reqDTO) {
        return ApiResponse.success(UserSuccessCode.USER_SUCCESS_SINGUP, userService.signUp(reqDTO));
    }

    @PostMapping("/login")
    public ApiResponse<UserResDTO.GetInfo> login(@RequestBody UserReqDTO.Login reqDTO) {
        return ApiResponse.success(UserSuccessCode.USER_SUCCESS_LOGIN, userService.login(reqDTO));
    }

    @PatchMapping("/witdraw")
    public ApiResponse<UserResDTO.GetInfo> withdraw(@RequestBody UserReqDTO.GetInfo reqDTO) {
        return ApiResponse.success(UserSuccessCode.USER_SUCCESS_WITHDRAW, userService.withdraw(reqDTO.userId()));
    }
}
