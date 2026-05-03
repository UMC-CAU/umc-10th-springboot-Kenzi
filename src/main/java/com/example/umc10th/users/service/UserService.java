package com.example.umc10th.users.service;

import com.example.umc10th.global.exception.ProjectException;
import com.example.umc10th.users.dto.UserReqDTO;
import com.example.umc10th.users.dto.UserResDTO;
import com.example.umc10th.users.entity.User;
import com.example.umc10th.users.enums.UserErrorCode;
import com.example.umc10th.users.enums.UserRole;
import com.example.umc10th.users.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public UserResDTO.GetInfoResponse getMe(Long userId) {
        return userRepository.findById(userId).map(user -> new UserResDTO.GetInfoResponse(
                user.getId(),
                user.getAddressCode(),
                user.getName(),
                user.getEmail(),
                user.getAge(),
                user.getRole(),
                user.getPoint(),
                user.getCreatedAt(),
                user.getDeletedAt()
        )).orElseThrow(() -> new ProjectException(UserErrorCode.USER_NOT_FOUND));
    }

    public UserResDTO.GetInfoResponse signUp(UserReqDTO.SignupRequest reqDTO) {
        return new UserResDTO.GetInfoResponse(
                0L,
                reqDTO.addressCode(),
                reqDTO.name(),
                reqDTO.email(),
                reqDTO.age(),
                UserRole.USER,
                0,
                LocalDateTime.now(),
                null
        );
    }

    public UserResDTO.GetInfoResponse login(UserReqDTO.LoginRequest reqDTO) {
        return new UserResDTO.GetInfoResponse(
                0L,
                null,
                null,
                reqDTO.email(),
                null,
                UserRole.USER,
                0,
                LocalDateTime.now(),
                null
        );
    }

    public UserResDTO.GetInfoResponse withdraw(Long userId) {
        return new UserResDTO.GetInfoResponse(
                userId,
                null,
                null,
                null,
                null,
                UserRole.USER,
                0,
                LocalDateTime.now(),
                LocalDateTime.now()
        );
    }
}
