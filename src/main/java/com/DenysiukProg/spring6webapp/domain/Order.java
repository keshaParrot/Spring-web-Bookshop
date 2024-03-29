package com.DenysiukProg.spring6webapp.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToMany
    @JoinTable(
            name = "order_books",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "book_id"))
    private Set<Book> orderedBooks = new HashSet<>();
    @ManyToOne
    @JoinColumn(name = "userbuyer_id")
    private UserEntity UserBuyer;
    private LocalDateTime orderTime;
    private int OrderPrice;

}
