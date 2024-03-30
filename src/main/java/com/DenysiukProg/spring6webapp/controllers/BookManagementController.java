package com.DenysiukProg.spring6webapp.controllers;

import com.DenysiukProg.spring6webapp.domain.dto.BookDto;
import com.DenysiukProg.spring6webapp.domain.dto.UserDto;
import com.DenysiukProg.spring6webapp.domain.entity.Author;
import com.DenysiukProg.spring6webapp.domain.entity.Publisher;
import com.DenysiukProg.spring6webapp.domain.entity.UserEntity;
import com.DenysiukProg.spring6webapp.security.SecurityUtil;
import com.DenysiukProg.spring6webapp.services.Interfaces.*;
import com.DenysiukProg.spring6webapp.services.UserServiceImpl;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
@Controller
public class BookManagementController {
    private final BookService bookService;
    private final AuthorService authorService;
    private final PublisherService publisherService;
    private final ReviewService reviewService;
    private final UserService userService;

    public BookManagementController(BookService bookService, AuthorService authorService, PublisherService publisherService, ReviewService reviewService, UserService userService) {
        this.bookService = bookService;
        this.authorService = authorService;
        this.publisherService = publisherService;
        this.reviewService = reviewService;
        this.userService = userService;
    }

    @RequestMapping("/home/book/{id}")
    public String getBook(Model model, @PathVariable Long id){
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
}
