package com.DenysiukProg.spring6webapp.controllers;

import com.DenysiukProg.spring6webapp.services.Interfaces.BookService;
import com.DenysiukProg.spring6webapp.services.Interfaces.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
@Controller
public class ShoppingCartController {

    private final BookService bookService;
    private final UserService userService;

    public ShoppingCartController(BookService bookService, UserService userService) {
        this.bookService = bookService;
        this.userService = userService;
    }

    @GetMapping("/home/shoppingCart")
    public String getShoppingCart(Model model) {



        return "user-shopcart";
    }
    @PostMapping("/home/shoppingCart/buy")
    public String buyBooks(Model model) {



        return "";
    }
}
