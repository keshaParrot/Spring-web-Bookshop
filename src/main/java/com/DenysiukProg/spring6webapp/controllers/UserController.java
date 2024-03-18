package com.DenysiukProg.spring6webapp.controllers;

import com.DenysiukProg.spring6webapp.domain.Author;
import com.DenysiukProg.spring6webapp.domain.Book;
import com.DenysiukProg.spring6webapp.domain.Publisher;
import com.DenysiukProg.spring6webapp.dto.BookDto;
import com.DenysiukProg.spring6webapp.services.Interfaces.AuthorService;
import com.DenysiukProg.spring6webapp.services.Interfaces.BookService;
import com.DenysiukProg.spring6webapp.services.Interfaces.PublisherService;
import com.DenysiukProg.spring6webapp.services.Interfaces.UserService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.HashMap;
import java.util.Map;

@Controller
public class UserController {
    private final BookService bookService;
    private final AuthorService authorService;
    private final PublisherService publisherService;
    private final UserService userService;
    public UserController(BookService bookService, AuthorService authorService, PublisherService publisherService, UserService userService) {
        this.bookService = bookService;
        this.authorService = authorService;
        this.publisherService = publisherService;
        this.userService = userService;
    }
    @GetMapping("/personalAccount")
    public String getAccountInformation(Model model){
        Map<String,String> shopData = new HashMap<>();
        //UserEntity user = userService.

        shopData.put("registeredUsers",userService.count());
        shopData.put("availableBooks",bookService.count());
        shopData.put("numberOfPublisher",publisherService.count());
        shopData.put("numberOfAuthors",authorService.count());

        //model.addAttribute("userData", user);
        //model.addAttribute("userBooks", user.getBooks());
        //model.addAttribute("userReview", user.getReviews());
        //model.addAttribute("shopData", shopData);
        return "user-account";
    }
    @GetMapping("/personalAccount/createBook")
    public String CreateBookForm(Model model){
        Book book = new Book();
        Iterable<Author> authors = authorService.findAll();
        Iterable<Publisher> publishers = publisherService.findAll();

        model.addAttribute("book", book);
        model.addAttribute("authorsList", authors);
        model.addAttribute("publisherList", publishers);
        return "book-create";
    }
    @PostMapping("/personalAccount/createBook")
    public String CreateBook(@Valid @ModelAttribute("book") BookDto bookDto,
                             BindingResult bindingResult,
                             Model model){

        Iterable<Author> authors = authorService.findAll();
        Iterable<Publisher> publishers = publisherService.findAll();

        if (bindingResult.hasErrors()) {
            model.addAttribute("book", bookDto);
            model.addAttribute("authorsList", authors);
            model.addAttribute("publisherList", publishers);

            return "book-create";
        }

        bookService.saveBook(bookDto);
        return "redirect:/personalAccount";
    }
}
