package com.example.umc10th.reference.entity;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Table(name = "\"Address\"")
@Getter
public class Address {

    @Id
    @Column(length = 20)
    private String code;

    @Column(nullable = false, length = 20)
    private String parentCode;

    @Column(nullable = false, length = 20)
    private String sidoCode;

    @Column(nullable = false, length = 20)
    private String sigunguCode;

    @Column(nullable = false, length = 20)
    private String emdCode;

    @Column(nullable = false, length = 100)
    private String addressName;
}
