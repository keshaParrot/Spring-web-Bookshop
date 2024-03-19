package com.DenysiukProg.spring6webapp.controllers;

import com.DenysiukProg.spring6webapp.domain.Author;
import com.DenysiukProg.spring6webapp.domain.Book;
import com.DenysiukProg.spring6webapp.domain.Publisher;
import com.DenysiukProg.spring6webapp.domain.UserEntity;
import com.DenysiukProg.spring6webapp.dto.BookDto;
import com.DenysiukProg.spring6webapp.dto.UserDto;
import com.DenysiukProg.spring6webapp.security.SecurityUtil;
import com.DenysiukProg.spring6webapp.services.Interfaces.AuthorService;
import com.DenysiukProg.spring6webapp.services.Interfaces.BookService;
import com.DenysiukProg.spring6webapp.services.Interfaces.PublisherService;
import com.DenysiukProg.spring6webapp.services.Interfaces.UserService;
import com.DenysiukProg.spring6webapp.services.UserServiceImpl;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/personalAccount")
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
    @GetMapping()
    public String getAccountInformation(Model model){
        Map<String,String> shopData = new HashMap<>();
        UserDto user = UserServiceImpl.fromUserEntity(userService.findByUsername(SecurityUtil.getSessionUser()));

        shopData.put("registeredUsers",userService.count());
        shopData.put("availableBooks",bookService.count());
        shopData.put("numberOfPublisher",publisherService.count());
        shopData.put("numberOfAuthors",authorService.count());

        model.addAttribute("userData", user);
        model.addAttribute("userBooks", user.getBooks());
        model.addAttribute("userReview", user.getReviews());
        model.addAttribute("shopData", shopData);
        return "user-account";
    }
    @PostMapping("edit")
    public String changeAccountInformation(@Valid @ModelAttribute("userData") UserDto userDto,
                                           BindingResult bindingResult,
                                           Model model){
        if (bindingResult.hasErrors()){

            return "user-account";
        }

        return "user-account";
    }
    @GetMapping("createBook")
    public String CreateBookForm(Model model){
        Book book = new Book();
        Iterable<Author> authors = authorService.findAll();
        Iterable<Publisher> publishers = publisherService.findAll();

        model.addAttribute("book", book);
        model.addAttribute("authorsList", authors);
        model.addAttribute("publisherList", publishers);
        return "book-create";
    }
    @PostMapping("createBook")
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
    @GetMapping("user/{id}/edit")
    public String createUserForm(Model model,@PathVariable Long id){


        return "user-manage";
    }
    @PostMapping("user/{id}/edit")
    public String editUser(Model model,@PathVariable Long id){


        return "redirect:/personalAccount";
    }
    @GetMapping("user/search")
    public String searchUserForm() {
        return "user-manage";
    }
    @GetMapping("user/searchUser")
    public String searchUser(@RequestParam String username, RedirectAttributes redirectAttributes) {
        UserEntity user = userService.findByUsername(username);
        if (user != null) {
            return "redirect:/personalAccount/user/" + user.getId() + "/edit";
        } else {
            redirectAttributes.addFlashAttribute("errorMessage", "User not found");
            return "redirect:/personalAccount/user/search";
        }
    }
}
