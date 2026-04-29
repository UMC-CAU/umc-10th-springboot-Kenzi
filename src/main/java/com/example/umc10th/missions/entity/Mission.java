package com.example.umc10th.missions.entity;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Table(name = "\"Mission\"")
@Getter
public class Mission {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(length = 36)
    private String id;

    @Column(nullable = false, length = 36)
    private String storeId;

    @Column(nullable = false)
    private Integer point;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String description;
}
