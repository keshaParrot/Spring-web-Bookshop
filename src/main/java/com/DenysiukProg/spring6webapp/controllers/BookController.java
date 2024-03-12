package com.DenysiukProg.spring6webapp.controllers;


import com.DenysiukProg.spring6webapp.domain.Book;
import com.DenysiukProg.spring6webapp.dto.BookDto;
import com.DenysiukProg.spring6webapp.services.Interfaces.BookService;
import com.DenysiukProg.spring6webapp.services.Interfaces.ReviewService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class BookController {
    private final BookService bookService;
    private final ReviewService reviewService;

    public BookController(BookService bookService, ReviewService reviewService) {
        this.bookService = bookService;
        this.reviewService = reviewService;
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
        BookDto book = bookService.finByID(id);

        model.addAttribute("usersReview", reviewService.findAllForBook(book));
        model.addAttribute("similarBooks", bookService.findBooksByGenreAndAgeGroup(book.getGenre(),book.getAgeGroup()));
        model.addAttribute("book", book);
        return "book";
    }
    @PutMapping("/book/{id}/update")
    public String BookPropertyForm(Model model, @PathVariable("id") long id){
        BookDto book = bookService.finByID(id);
        model.addAttribute("book",book);
        return "changePropertyBook";
    }
    /*@PutMapping("/book/{id}/update")
    public String changeBookProperty(){


    }*/

}
