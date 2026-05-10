package com.example.umc10th.reference.entity;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Table(name = "address")
@Getter
public class Address {

    @Id
    @Column(nullable = false, length = 20)
    private String code;

    @Column(name = "parent_code", nullable = false, length = 20)
    private String parentCode;

    @Column(name = "sido_code", nullable = false, length = 20)
    private String sidoCode;

    @Column(name = "sigungu_code", nullable = false, length = 20)
    private String sigunguCode;

    @Column(name = "emd_code", nullable = false, length = 20)
    private String emdCode;

    @Column(name = "address_name", nullable = false, length = 100)
    private String addressName;
}
