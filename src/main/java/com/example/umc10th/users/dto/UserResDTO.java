package com.example.umc10th.users.dto;

import com.example.umc10th.users.entity.User;
import com.example.umc10th.users.enums.UserRole;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class UserResDTO {

    public record GetInfo(
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
}
