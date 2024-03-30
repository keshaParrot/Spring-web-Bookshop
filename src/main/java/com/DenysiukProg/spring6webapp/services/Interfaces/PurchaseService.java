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
    void addBookToUserShoppingCart(Long userId, Long bookId);

    @Transactional
    void createOrder(Long userId, UserDto userDto);

    void putBookOnShoppingCart(Book book, int quantity, HttpSession session);

    List<ShoppingCartItem> getBooksFromShoppingCart(HttpSession session);
}
