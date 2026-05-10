package com.example.umc10th.users.repository;

import com.example.umc10th.users.entity.AddressScore;
import com.example.umc10th.users.entity.AddressScoreId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AddressScoreRepository extends JpaRepository<AddressScore, AddressScoreId> {
    Optional<AddressScore> findByIdUserIdAndIdAddressCode(Long userId, String addressCode);
}
