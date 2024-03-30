package com.DenysiukProg.spring6webapp.services;

import com.DenysiukProg.spring6webapp.domain.dto.ShoppingCartItem;
import com.DenysiukProg.spring6webapp.domain.dto.UserDto;
import com.DenysiukProg.spring6webapp.domain.entity.Book;
import com.DenysiukProg.spring6webapp.domain.entity.Order;
import com.DenysiukProg.spring6webapp.domain.entity.OrderItem;
import com.DenysiukProg.spring6webapp.domain.entity.UserEntity;
import com.DenysiukProg.spring6webapp.repositories.BookRepository;
import com.DenysiukProg.spring6webapp.repositories.OrderRepository;
import com.DenysiukProg.spring6webapp.repositories.UserRepository;
import com.DenysiukProg.spring6webapp.services.Interfaces.PurchaseService;
import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class PurchaseServiceImpl implements PurchaseService {

    private final UserRepository userRepository;
    private final BookRepository bookRepository;
    private final OrderRepository orderRepository;

    public PurchaseServiceImpl(UserRepository userRepository, BookRepository bookRepository, OrderRepository orderRepository) {
        this.userRepository = userRepository;
        this.bookRepository = bookRepository;
        this.orderRepository = orderRepository;
    }

    @Override
    @Transactional
    public void addBookToUserShoppingCart(Long userId, Long bookId) {
        UserEntity user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        Book book = bookRepository.findById(bookId).orElseThrow(() -> new RuntimeException("Book not found"));


        userRepository.save(user);
    }

    @Override
    @Transactional
    public void createOrder(Long userId, UserDto userDto) {
        UserEntity user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        List<ShoppingCartItem> shoppingCart = userDto.getShoppingCart();

        Order order = new Order();
        order.setUserBuyer(user);
        order.setOrderTime(LocalDateTime.now());
        int price = 0;

        for (ShoppingCartItem sci : shoppingCart){
            OrderItem orderItem = new OrderItem();
            orderItem.setBook(bookRepository.getReferenceById(sci.getBookId()));
            orderItem.setQuantity(sci.getQuantity());
            orderItem.setOrder(order);
            order.getOrderedItems().add(orderItem);
            price += bookRepository.getReferenceById(sci.getBookId()).getPrice() * sci.getQuantity();
        }

        order.setOrderPrice(price);
        orderRepository.save(order);
    }
    @Override
    public void putBookOnShoppingCart(Book book, int quantity, HttpSession session){
        ShoppingCartItem shoppingCartItem = new ShoppingCartItem();
        shoppingCartItem.setBookId(book.getId());
        shoppingCartItem.setQuantity(quantity);

        ArrayList<ShoppingCartItem> shoppingCart;
        if(session.getAttribute("shoppingCart")==null){
            shoppingCart = new ArrayList<ShoppingCartItem>();
            session.setAttribute("shoppingCart", shoppingCart);
        }

        shoppingCart = (ArrayList<ShoppingCartItem>) session.getAttribute("shoppingCart");
        shoppingCart.add(shoppingCartItem);
    }
    @Override
    public List<ShoppingCartItem> getBooksFromShoppingCart(HttpSession session){
        List<ShoppingCartItem> cart = (List<ShoppingCartItem>) session.getAttribute("shoppingCart");
        if (cart == null) {
            cart = new ArrayList<>();
            session.setAttribute("shoppingCart", cart);
        }
        return cart;
    }
}
