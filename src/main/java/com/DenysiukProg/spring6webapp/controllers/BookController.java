package com.DenysiukProg.spring6webapp.controllers;


import com.DenysiukProg.spring6webapp.domain.Author;
import com.DenysiukProg.spring6webapp.domain.Book;
import com.DenysiukProg.spring6webapp.domain.Publisher;
import com.DenysiukProg.spring6webapp.dto.BookDto;
import com.DenysiukProg.spring6webapp.services.Interfaces.AuthorService;
import com.DenysiukProg.spring6webapp.services.Interfaces.BookService;
import com.DenysiukProg.spring6webapp.services.Interfaces.PublisherService;
import com.DenysiukProg.spring6webapp.services.Interfaces.ReviewService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

@Controller
public class BookController {
    private final BookService bookService;
    private final ReviewService reviewService;
    private final AuthorService authorService;
    private final PublisherService publisherService;

    public BookController(BookService bookService, ReviewService reviewService, AuthorService authorService, PublisherService publisherService) {
        this.bookService = bookService;
        this.reviewService = reviewService;
        this.authorService = authorService;
        this.publisherService = publisherService;
    }

    @RequestMapping("/home")
    public String getBooks(Model model){
        model.addAttribute("books", bookService.findAll());
        return "home";
    }
    @RequestMapping("/home/bookCategories")
    public String getBookCategories(Model model){
        HashSet<String> hashOfGenre = new HashSet<>(bookService.findAllGenre());

        model.addAttribute("maxPrice",bookService.findMinPrice().get().intValue());
        model.addAttribute("minPrice",bookService.findMaxPrice().get().intValue());
        model.addAttribute("categories", hashOfGenre);
        return "book-categories";
    }
    @RequestMapping("/home/book/{id}")
    public String getBook(Model model,@PathVariable Long id){
        BookDto book = bookService.findByID(id);

        model.addAttribute("usersReview", reviewService.findAllForBook(book));
        model.addAttribute("similarBooks", bookService.getBooksByGenreAndAgeGroupIgnoringId(book.getGenre(),book.getAgeGroup(),book.getId()));
        model.addAttribute("book", book);
        return "book";
    }
    @GetMapping("/home/book/{id}/delete")
    public String deleteClub(@PathVariable Long id){
        bookService.delete(id);
        return "redirect:/home";
    }
    @GetMapping("/home/book/{id}/edit")
    public String BookPropertyForm(Model model, @PathVariable("id") long id){
        BookDto book = bookService.findByID(id);
        Iterable<Author> authors = authorService.findAll();
        Iterable<Publisher> publishers = publisherService.findAll();

        model.addAttribute("authorsList", authors);
        model.addAttribute("publisherList", publishers);
        model.addAttribute("book",book);
        return "book-edit";
    }
    @PostMapping("/home/book/{id}/edit")
    public String changeBookProperty(@PathVariable("id") long id,
                                     @Valid @ModelAttribute("book") BookDto bookDto,
                                     BindingResult bindingResult,
                                     Model model){

        if (bindingResult.hasErrors()){
            Iterable<Author> authors = authorService.findAll();
            Iterable<Publisher> publishers = publisherService.findAll();
            model.addAttribute("authorsList", authors);
            model.addAttribute("publisherList", publishers);

            return "book-edit";
        }

        bookDto.setId(id);
        System.out.println(bookDto);
        bookService.updateBook(bookDto);
        return "redirect:/home/book/"+id;
    }
}
