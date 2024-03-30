package com.DenysiukProg.spring6webapp.controllers;

import com.DenysiukProg.spring6webapp.services.Interfaces.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
public class HomeController {
    private final BookService bookService;

    public HomeController(BookService bookService) {
        this.bookService = bookService;
    }

    @RequestMapping("/home")
    public String getBooks(Model model){
        model.addAttribute("books", bookService.findAll());
        return "home";
    }
}
