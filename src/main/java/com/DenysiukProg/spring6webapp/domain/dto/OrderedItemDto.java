package com.DenysiukProg.spring6webapp.domain.dto;

import com.DenysiukProg.spring6webapp.domain.entity.Book;
import com.DenysiukProg.spring6webapp.domain.entity.Order;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderedItemDto {
    private Long id;
    private Book book;
    private int quantity;
    private Order order;
}
