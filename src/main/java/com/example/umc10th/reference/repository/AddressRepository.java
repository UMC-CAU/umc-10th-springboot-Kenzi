package com.example.umc10th.reference.repository;

import com.example.umc10th.reference.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, String> {
}
