package com.DenysiukProg.spring6webapp.services;

import com.DenysiukProg.spring6webapp.domain.dto.ShoppingCartItem;
import com.DenysiukProg.spring6webapp.domain.dto.UserDto;
import com.DenysiukProg.spring6webapp.domain.entity.Book;
import com.DenysiukProg.spring6webapp.domain.entity.UserOrder;
import com.DenysiukProg.spring6webapp.domain.entity.OrderItem;
import com.DenysiukProg.spring6webapp.domain.entity.UserEntity;
import com.DenysiukProg.spring6webapp.repositories.BookRepository;
import com.DenysiukProg.spring6webapp.repositories.OrderRepository;
import com.DenysiukProg.spring6webapp.repositories.UserRepository;
import com.DenysiukProg.spring6webapp.services.Interfaces.PurchaseService;
import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
@AllArgsConstructor
public class PurchaseServiceImpl implements PurchaseService {

    private final UserRepository userRepository;
    private final BookRepository bookRepository;
    private final OrderRepository orderRepository;

    @Override
    @Transactional
    public void createOrder(Long userId, UserDto userDto) {
        UserEntity user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        List<ShoppingCartItem> shoppingCart = userDto.getShoppingCart();

        UserOrder order = new UserOrder();
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
    public int putBookOnShoppingCart(Long bookId, int quantity, HttpSession session){
        Book book = bookRepository.getReferenceById(bookId);

        ShoppingCartItem shoppingCartItem = new ShoppingCartItem();
        shoppingCartItem.setBookId(book.getId());
        shoppingCartItem.setQuantity(quantity);

        ArrayList<ShoppingCartItem> shoppingCart;
        if(session.getAttribute("shoppingCart")==null){
            shoppingCart = new ArrayList<ShoppingCartItem>();
            session.setAttribute("shoppingCart", shoppingCart);
        }
        else{
            shoppingCart = (ArrayList<ShoppingCartItem>) session.getAttribute("shoppingCart");
        }
        if(shoppingCart.size()>20){
            System.out.println("cart full");
            return 2;
        }

        for (ShoppingCartItem item : shoppingCart) {
            if (item.getBookId() == book.getId()) {
                item.setQuantity(quantity);
                System.out.println("book quantity has been changed successfully");
                return 1;
            }
        }
        shoppingCart.add(shoppingCartItem);
        session.setAttribute("shoppingCart",shoppingCart);
        System.out.println("book was added to cart successfully");
        System.out.println(session.getAttribute("shoppingCart"));
        return 0;
    }
    @Override
    public boolean deleteBookFromCart(Long bookId, HttpSession session){
        ArrayList<ShoppingCartItem> shoppingCart = (ArrayList<ShoppingCartItem>) session.getAttribute("shoppingCart");
        Book book = bookRepository.getReferenceById(bookId);

        for (Iterator<ShoppingCartItem> iterator = shoppingCart.iterator(); iterator.hasNext();) {
            ShoppingCartItem item = iterator.next();
            if (item.getBookId().equals(book.getId())) {
                iterator.remove();
                System.out.println("Book removed from cart successfully");
                session.setAttribute("shoppingCart", shoppingCart);
                return true;
            }
        }

        System.out.println("Book not found in cart");
        return false;
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
