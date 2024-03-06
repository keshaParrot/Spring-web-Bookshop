package com.DenysiukProg.spring6webapp.controllers;


import com.DenysiukProg.spring6webapp.dto.BookResponse;
import com.DenysiukProg.spring6webapp.services.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class BookController {
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @RequestMapping("/home")
    public String getBooks(Model model){
        model.addAttribute("books", bookService.findAll());
        return "home";
    }
    @RequestMapping("/book/{id}")
    public String getBook(Model model,@PathVariable Long id){
        model.addAttribute("book", bookService.finByID(id));
        return "book";
    }
}
