package com.example.umc10th.users.dto;

import com.example.umc10th.users.entity.User;
import com.example.umc10th.users.enums.UserRole;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class UserResDTO {

    @Schema(name = "UserGetInfoResponse")
    public record GetInfoResponse(
            Long id,
            String addressCode,
            String name,
            String email,
            Integer age,
            UserRole role,
            Integer point,
            LocalDateTime createdAt,
            LocalDateTime deletedAt
    ) {}
    @Schema(name = "UserAddressScore")
    public record AddressScoreResponse(
            Long userId,
            String addressCode,
            Integer score
    ) {}
}
