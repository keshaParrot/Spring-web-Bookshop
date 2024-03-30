package com.DenysiukProg.spring6webapp.controllers;


import com.DenysiukProg.spring6webapp.domain.dto.ShoppingCartItem;
import com.DenysiukProg.spring6webapp.domain.entity.Author;
import com.DenysiukProg.spring6webapp.domain.entity.Book;
import com.DenysiukProg.spring6webapp.domain.entity.Publisher;
import com.DenysiukProg.spring6webapp.domain.entity.UserEntity;
import com.DenysiukProg.spring6webapp.domain.dto.BookDto;
import com.DenysiukProg.spring6webapp.domain.dto.UserDto;
import com.DenysiukProg.spring6webapp.security.SecurityUtil;
import com.DenysiukProg.spring6webapp.services.BookServiceImpl;
import com.DenysiukProg.spring6webapp.services.Interfaces.*;
import com.DenysiukProg.spring6webapp.services.UserServiceImpl;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Controller
public class BookController {
    private final BookService bookService;
    private final ReviewService reviewService;
    private final AuthorService authorService;
    private final PublisherService publisherService;
    private final UserService userService;

    public BookController(BookService bookService, ReviewService reviewService, AuthorService authorService, PublisherService publisherService, UserService userService) {
        this.bookService = bookService;
        this.reviewService = reviewService;
        this.authorService = authorService;
        this.publisherService = publisherService;
        this.userService = userService;
    }

    @RequestMapping("/home")
    public String getBooks(Model model){
        model.addAttribute("books", bookService.findAll());
        return "home";
    }
    @RequestMapping("/home/book/{id}")
    public String getBook(Model model,@PathVariable Long id){
        BookDto book = bookService.findByID(id);
        UserEntity user = userService.findByUsername(SecurityUtil.getSessionUser());

        System.out.println("semilunar book"+reviewService.findAllForBook(book));
        model.addAttribute("userData",user ==null? new UserDto(): UserServiceImpl.entityToDto(user));
        model.addAttribute("booksReview", reviewService.findAllForBook(book));
        model.addAttribute("similarBooks", bookService.getBooksByGenreAndAgeGroupIgnoringId(book.getGenre(),book.getAgeGroup(),book.getId()));
        model.addAttribute("book", book);
        return "book";
    }
    @PostMapping("/home/book/{id}/addToCart")
    public String addBookToCart(Model model, @PathVariable Long id, HttpSession session){
        BookDto book = bookService.findByID(id);
        UserEntity user = userService.findByUsername(SecurityUtil.getSessionUser());
        if(user!=null){

            return "redirect:/home/book/" + id + "?successAdded";
        }

        return "redirect:/home/book/" + id + "?failed";
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
    @GetMapping("/home/book/{id}/delete")
    public String deleteClub(@PathVariable Long id){
        bookService.delete(id);
        return "redirect:/home";
    }
    @RequestMapping("/home/bookCategories")
    public String getBookCategories(Model model) {

        model.addAttribute("minPrice", bookService.findMinPrice().get());
        model.addAttribute("maxPrice", bookService.findMaxPrice().get());
        model.addAttribute("categories", new HashSet<>(bookService.findAllGenre()));
        model.addAttribute("books", bookService.findAll());
        return "book-categories";
    }
    @GetMapping("/home/shoppingCart")
    public String getShoppingCart(Model model) {



        return "user-shopcart";
    }
    @PostMapping("/home/shoppingCart/buy")
    public String buyBooks(Model model) {



        return "";
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
