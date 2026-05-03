package com.example.umc10th.store.entity;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Table(name = "\"Store\"")
@Getter
public class Store {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @Column(nullable = false, length = 20)
    private String addressCode;

    @Column(nullable = false)
    private Long ceoId;

    @Column(nullable = false)
    private Long foodId;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(nullable = false, length = 500)
    private String storePhotoUrl;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String introduction;
}
