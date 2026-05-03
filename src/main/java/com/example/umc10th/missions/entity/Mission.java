package com.example.umc10th.missions.entity;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Table(name = "\"Mission\"")
@Getter
public class Mission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @Column(nullable = false)
    private Long storeId;

    @Column(nullable = false)
    private Integer point;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String description;
}
