package com.DenysiukProg.spring6webapp.controllers;

import com.DenysiukProg.spring6webapp.domain.Book;
import com.DenysiukProg.spring6webapp.repositories.BookRepository;
import com.DenysiukProg.spring6webapp.services.Interfaces.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class UserController {
    private final BookRepository bookRepository;
    private final BookService bookService;
    public UserController(BookRepository bookRepository, BookService bookService) {
        this.bookRepository = bookRepository;
        this.bookService = bookService;
    }

    @GetMapping("/personalAccount")
    public String getAccountInformation(Model model){
        return "personalAccount";
    }

    @GetMapping("/personalAccount/createBook")
    public String CreateBookForm(Model model){
        Book book = new Book();
        model.addAttribute("book", book);
        return "manageBook";
    }
    @GetMapping("/personalAccount/createBook")
    public String CreateBook(@ModelAttribute("book") Book book){
        bookService.saveBook(book);
        return "manageBook";
    }
}
