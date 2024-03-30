package com.DenysiukProg.spring6webapp.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
public class UserOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @OneToMany(mappedBy = "order")
    private Set<OrderItem> orderedItems = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "userbuyer_id")
    private UserEntity userBuyer;
    private LocalDateTime orderTime;
    private int OrderPrice;

}
