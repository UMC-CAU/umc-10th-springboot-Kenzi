package com.example.umc10th.users.service;

import com.example.umc10th.global.exception.ProjectException;
import com.example.umc10th.users.dto.UserResDTO;
import com.example.umc10th.users.entity.User;
import com.example.umc10th.users.enums.UserErrorCode;
import com.example.umc10th.users.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public UserResDTO.GetInfo getMe(String userId) {
        return userRepository.findById(userId).map(user -> new UserResDTO.GetInfo(
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
}
