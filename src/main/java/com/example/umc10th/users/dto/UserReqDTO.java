package com.example.umc10th.users.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserReqDTO {

   public record GetInfo(Long userId) {}

    public record Signup(
            String email,
            String name,
            Integer age,
            String sex,
            String password,
            String addressCode
    ) {}

    public record Login(
            String email,
            String password
    ) {}

}
