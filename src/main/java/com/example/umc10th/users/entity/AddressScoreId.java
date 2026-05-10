package com.example.umc10th.users.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Embeddable
@Getter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AddressScoreId implements Serializable {

    @Column(name = "code", nullable = false, length = 20)
    private String addressCode;

    @Column(name = "user_id", nullable = false)
    private Long userId;
}
