package com.DenysiukProg.spring6webapp.domain.dto;

import com.DenysiukProg.spring6webapp.domain.entity.Book;
import com.DenysiukProg.spring6webapp.domain.entity.UserOrder;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderedItemDto {
    private Long id;
    private Book book;
    private int quantity;
    private UserOrder order;
}
