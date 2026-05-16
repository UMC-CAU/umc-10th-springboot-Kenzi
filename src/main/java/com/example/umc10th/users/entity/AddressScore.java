package com.example.umc10th.users.entity;

import com.example.umc10th.reference.entity.Address;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "score_address")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AddressScore {

    @EmbeddedId
    private AddressScoreId id;

    @MapsId("addressCode")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "code", referencedColumnName = "code", nullable = false)
    private Address address;

    @MapsId("userId")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private User user;

    @Column(name = "score", nullable = false)
    private Integer score = 0;
}
