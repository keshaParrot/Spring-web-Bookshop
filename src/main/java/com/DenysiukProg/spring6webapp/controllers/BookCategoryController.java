package com.DenysiukProg.spring6webapp.controllers;

import com.DenysiukProg.spring6webapp.domain.entity.Book;
import com.DenysiukProg.spring6webapp.services.Interfaces.BookService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashSet;
import java.util.List;

/**
 * a controller that is responsible for displaying a page
 * with books and displaying books with selected filters
 */
@Controller
@AllArgsConstructor
public class BookCategoryController {
    private final BookService bookService;

    /**
     * Display the book category page.
     *
     * @return the name of the login view template
     */
    @RequestMapping("/home/bookCategories")
    public String getBookCategories(Model model) {

        model.addAttribute("minPrice", bookService.findMinPrice().get());
        model.addAttribute("maxPrice", bookService.findMaxPrice().get());
        model.addAttribute("categories", new HashSet<>(bookService.findAllGenre()));
        model.addAttribute("books", bookService.findAll());
        return "book-categories";
    }
    /**
     * Process filtering books based on search term, price range, and genres,
     * then displaying a page with filtered books.
     *
     * @param searchTerm the search term to filter books
     * @param inputMinPrice the minimum price range
     * @param inputMaxPrice the maximum price range
     * @param genres the genres to filter books
     * @return the name of the view template
     */
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
