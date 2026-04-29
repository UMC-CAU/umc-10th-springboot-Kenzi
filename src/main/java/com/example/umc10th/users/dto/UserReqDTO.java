package com.example.umc10th.users.dto;

import com.example.umc10th.users.enums.UserRole;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserReqDTO {
   public record GetInfo(Long userId) {}
}
