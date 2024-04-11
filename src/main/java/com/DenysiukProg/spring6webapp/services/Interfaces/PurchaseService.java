package com.DenysiukProg.spring6webapp.services.Interfaces;

import com.DenysiukProg.spring6webapp.domain.dto.ShoppingCartItem;
import com.DenysiukProg.spring6webapp.domain.dto.UserDto;
import com.DenysiukProg.spring6webapp.domain.entity.Book;
import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PurchaseService {
    @Transactional
    void createOrder(Long userId, UserDto userDto);

    int putBookOnShoppingCart(Long bookId, int quantity, HttpSession session);

    boolean deleteBookFromCart(Long bookId, HttpSession session);

    List<ShoppingCartItem> getBooksFromShoppingCart(HttpSession session);
}
