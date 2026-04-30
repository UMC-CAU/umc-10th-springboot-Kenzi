package com.example.umc10th.users.repository;

import com.example.umc10th.users.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
