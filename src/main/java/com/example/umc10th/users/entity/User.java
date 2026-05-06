package com.example.umc10th.users.entity;

import com.example.umc10th.reference.entity.Address;
import com.example.umc10th.users.enums.UserRole;
import jakarta.persistence.*;
import lombok.Getter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "user")
@Getter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @Column(name = "address_code", nullable = false, length = 20)
    private String addressCode;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "address_code", referencedColumnName = "code", insertable = false, updatable = false)
    private Address address;

    @Column(nullable = false, length = 50)
    private String name;

    @Column(nullable = false, unique = true, length = 100)
    private String email;

    @Column(nullable = false)
    private Integer age;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 10)
    private UserRole role = UserRole.USER;

    @Column(nullable = false, columnDefinition = "INT DEFAULT 0")
    private Integer point = 0;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;

    @Column(nullable = false)
    private String password;
}
