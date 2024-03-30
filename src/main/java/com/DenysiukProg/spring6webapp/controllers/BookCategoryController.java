package com.DenysiukProg.spring6webapp.controllers;

import com.DenysiukProg.spring6webapp.domain.entity.Book;
import com.DenysiukProg.spring6webapp.services.Interfaces.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashSet;
import java.util.List;
@Controller
public class BookCategoryController {
    private final BookService bookService;

    public BookCategoryController(BookService bookService) {
        this.bookService = bookService;
    }
    @RequestMapping("/home/bookCategories")
    public String getBookCategories(Model model) {

        model.addAttribute("minPrice", bookService.findMinPrice().get());
        model.addAttribute("maxPrice", bookService.findMaxPrice().get());
        model.addAttribute("categories", new HashSet<>(bookService.findAllGenre()));
        model.addAttribute("books", bookService.findAll());
        return "book-categories";
    }
    @GetMapping("/filteredBooks")
    public String filterBooks(
            @RequestParam(required = false) String searchTerm,
            @RequestParam(required = false) Double inputMinPrice,
            @RequestParam(required = false) Double inputMaxPrice,
            @RequestParam(required = false) List<String> genres,
            Model model) {

        List<Book> filteredBooks = bookService.findFilteredBooks(searchTerm, inputMinPrice, inputMaxPrice, genres);
        HashSet<String> hashOfGenre = new HashSet<>(bookService.findAllGenre());

        model.addAttribute("minPrice", bookService.findMinPrice().get());
        model.addAttribute("maxPrice", bookService.findMaxPrice().get());
        model.addAttribute("categories", hashOfGenre);
        model.addAttribute("books", filteredBooks);

        return "book-categories";
    }
}
