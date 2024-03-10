package com.DenysiukProg.spring6webapp.controllers;


import com.DenysiukProg.spring6webapp.domain.Book;
import com.DenysiukProg.spring6webapp.services.Interfaces.BookService;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
    @RequestMapping("/bookCategories")
    public String getBookCategories(Model model){
        return "categoriesBook";
    }
    @RequestMapping("/book/{id}")
    public String getBook(Model model,@PathVariable Long id){
        model.addAttribute("book", bookService.finByID(id));
        return "book";
    }

}
