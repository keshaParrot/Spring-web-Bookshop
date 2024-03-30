package com.DenysiukProg.spring6webapp.domain.dto;

import com.DenysiukProg.spring6webapp.domain.entity.Book;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PurchaseBookDto {
    private Long userId;
    private List<OrderedItemDto> orderedItems;

    public PurchaseBookDto(Long userId) {
        this.userId = userId;
    }
    public void addBookToOrder(Book book,int quantity){
        OrderedItemDto orderedItemDto = new OrderedItemDto();
        orderedItemDto.setBook(book);
        orderedItemDto.setQuantity(quantity);
        orderedItems.add(orderedItemDto);
    }
}
