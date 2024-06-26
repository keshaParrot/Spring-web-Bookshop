package com.DenysiukProg.spring6webapp.domain.dto;

import com.DenysiukProg.spring6webapp.domain.entity.Book;
import com.DenysiukProg.spring6webapp.domain.entity.UserEntity;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Data
@Setter
@Getter
public class OrderDto {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Set<Book> orderedBooks = new HashSet<>();
    private UserEntity UserBuyer;
    private LocalDateTime orderTime;
    private int OrderPrice;
}
