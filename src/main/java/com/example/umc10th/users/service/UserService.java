package com.example.umc10th.users.service;

import com.example.umc10th.auth.JwtUtil;
import com.example.umc10th.global.exception.ProjectException;
import com.example.umc10th.users.dto.UserReqDTO;
import com.example.umc10th.users.dto.UserResDTO;
import com.example.umc10th.users.entity.AuthMember;
import com.example.umc10th.users.entity.User;
import com.example.umc10th.users.enums.UserErrorCode;
import com.example.umc10th.users.enums.UserRole;
import com.example.umc10th.users.repository.AddressScoreRepository;
import com.example.umc10th.users.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final AddressScoreRepository addressScoreRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public UserResDTO.GetInfoResponse getMe(AuthMember authMember) {
        if (authMember == null || authMember.getUser() == null) {
            throw new ProjectException(UserErrorCode.USER_NOT_FOUND);
        }
        User user = authMember.getUser();
        return new UserResDTO.GetInfoResponse(
                user.getId(),
                user.getAddressCode(),
                user.getName(),
                user.getEmail(),
                user.getAge(),
                user.getRole(),
                user.getPoint(),
                user.getCreatedAt(),
                user.getDeletedAt()
        );
    }

    public UserResDTO.GetInfoResponse signUp(UserReqDTO.SignupRequest reqDTO) {
        if (userRepository.existsByEmailAndDeletedAtIsNull(reqDTO.email())) {
            throw new ProjectException(UserErrorCode.USER_ALREADY_EXISTS);
        }

        User user = userRepository.save(User.signUp(
                reqDTO.addressCode(),
                reqDTO.name(),
                reqDTO.email(),
                reqDTO.age(),
                passwordEncoder.encode(reqDTO.password())
        ));

        return new UserResDTO.GetInfoResponse(
                user.getId(),
                user.getAddressCode(),
                user.getName(),
                user.getEmail(),
                user.getAge(),
                user.getRole(),
                user.getPoint(),
                user.getCreatedAt(),
                user.getDeletedAt()
        );
    }

    public UserResDTO.LoginResponse login(UserReqDTO.LoginRequest reqDTO) {
        User user = userRepository.findByEmailAndDeletedAtIsNull(reqDTO.email())
                .orElseThrow(() -> new ProjectException(UserErrorCode.USER_NOT_FOUND));

        if (!passwordEncoder.matches(reqDTO.password(), user.getPassword())) {
            throw new ProjectException(UserErrorCode.USER_INVALID_PASSWORD);
        }

        String accessToken = jwtUtil.createAccessToken(new AuthMember(user));

        return new UserResDTO.LoginResponse(
                user.getId(),
                user.getEmail(),
                user.getRole(),
                accessToken
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
    public UserResDTO.AddressScoreResponse getAddressScore(Long userId , String addressCode) {
        if (!userRepository.existsById(userId)) {
            throw new ProjectException(UserErrorCode.USER_NOT_FOUND);
        }

        Integer score = addressScoreRepository.findByIdUserIdAndIdAddressCode(userId, addressCode)
                .map(addressScore -> addressScore.getScore())
                .orElse(0);

        return new UserResDTO.AddressScoreResponse(
                userId,
                addressCode,
                score
        );
    }
}
