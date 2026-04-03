package com.example.umc10th.auth.service;

import com.example.umc10th.auth.entity.RefreshToken;
import com.example.umc10th.auth.repository.RefreshTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final RefreshTokenRepository refreshTokenRepository;

    public List<RefreshToken> getAll() {
        return refreshTokenRepository.findAll();
    }
}
