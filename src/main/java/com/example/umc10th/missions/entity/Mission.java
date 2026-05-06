package com.example.umc10th.missions.entity;

import com.example.umc10th.store.entity.Store;
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

    @Column(name = "store_id", nullable = false)
    private Long storeId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "store_id", insertable = false, updatable = false)
    private Store store;

    @Column(nullable = false)
    private Integer point;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String description;
}
