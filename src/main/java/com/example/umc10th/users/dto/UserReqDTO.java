package com.example.umc10th.users.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserReqDTO {

    @Schema(name = "UserGetInfoRequest")
    public record GetInfo(Long userId) {}

    @Schema(name = "UserSignupRequest")
    public record Signup(
            String email,
            String name,
            Integer age,
            String sex,
            String password,
            String addressCode
    ) {}

    @Schema(name = "UserLoginRequest")
    public record Login(
            String email,
            String password
    ) {}

}
