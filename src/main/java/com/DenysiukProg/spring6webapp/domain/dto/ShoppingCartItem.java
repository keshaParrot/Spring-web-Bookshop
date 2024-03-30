package com.DenysiukProg.spring6webapp.domain.dto;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

@Embeddable
@Setter
@Getter
public class ShoppingCartItem {
    private Long bookId;
    private int quantity;
}
