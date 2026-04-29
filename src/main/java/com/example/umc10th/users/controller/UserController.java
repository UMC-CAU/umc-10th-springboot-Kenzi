package com.example.umc10th.users.controller;

import com.example.umc10th.global.code.BaseSuccessCode;
import com.example.umc10th.global.code.GeneralSuccessCode;
import com.example.umc10th.global.response.ApiResponse;
import com.example.umc10th.users.dto.UserReqDTO;
import com.example.umc10th.users.dto.UserResDTO;
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
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/me")
    public ApiResponse<UserResDTO.GetInfo> me( @RequestBody UserReqDTO.GetInfo reqDTO ) {
        BaseSuccessCode code = GeneralSuccessCode.OK;
        return ApiResponse.success( code , userService.getMe(reqDTO.userId().toString()));
    }
}
