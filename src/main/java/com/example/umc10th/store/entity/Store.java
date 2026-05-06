package com.example.umc10th.store.entity;

import com.example.umc10th.reference.entity.Address;
import com.example.umc10th.users.entity.User;
import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Table(name = "store")
@Getter
public class Store {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @Column(name = "address_code", nullable = false, length = 20)
    private String addressCode;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "address_code", referencedColumnName = "code", insertable = false, updatable = false)
    private Address address;

    @Column(name = "ceo_id", nullable = false)
    private Long ceoId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ceo_id", insertable = false, updatable = false)
    private User ceo;

    @Column(name = "food_id", nullable = false)
    private Long foodId;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(name = "store_photo_url", nullable = false, length = 500)
    private String storePhotoUrl;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String introduction;
}
