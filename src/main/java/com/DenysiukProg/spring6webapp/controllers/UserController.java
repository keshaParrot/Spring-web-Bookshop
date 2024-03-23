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
import org.apache.catalina.User;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping()
public class UserController {
    private final BookService bookService;
    private final AuthorService authorService;
    private final PublisherService publisherService;
    private final UserService userService;
    private Map<String,String> shopData = new HashMap<>();

    public UserController(BookService bookService, AuthorService authorService, PublisherService publisherService, UserService userService) {
        this.bookService = bookService;
        this.authorService = authorService;
        this.publisherService = publisherService;
        this.userService = userService;

        shopData.put("registeredUsers",userService.count());
        shopData.put("availableBooks",bookService.count());
        shopData.put("numberOfPublisher",publisherService.count());
        shopData.put("numberOfAuthors",authorService.count());
    }

    @GetMapping("/personalAccount")
    public String getAccountInformation(Model model){
        UserDto user = UserServiceImpl.entityToDto(userService.findByUsername(SecurityUtil.getSessionUser()));

        model.addAttribute("userData", user);
        model.addAttribute("userBooks", user.getBooks());
        model.addAttribute("userReview", user.getReviews());
        model.addAttribute("shopData", shopData);

        return "user-account";
    }
    @PostMapping("/personalAccount")
    public String changeAccountInformation(@Valid @ModelAttribute("userData") UserDto userDto,
                                           BindingResult bindingResult,
                                           Model model){

        UserDto user = UserServiceImpl.entityToDto(userService.findByUsername(SecurityUtil.getSessionUser()));
        List<String> usernamesList =  userService.getAllUsernames();
        usernamesList.remove(userDto.getUsername());
        List<String> emailList = userService.getAllEmails();
        emailList.remove(userDto.getEmail());

        if (usernamesList.contains(userDto.getUsername().toLowerCase())) {
            bindingResult.addError(new FieldError("userData","username","user with this username is alredy exist"));
        }
        if (emailList.contains(userDto.getEmail().toLowerCase())) {
            bindingResult.addError(new FieldError("userData","email","user with this email is alredy exist"));
        }

        if (bindingResult.hasErrors()){
            System.out.println("znaleziono bledy" + bindingResult.getFieldErrors());

            model.addAttribute("userData", userDto);
            model.addAttribute("userBooks", user.getBooks());
            model.addAttribute("userReview", user.getReviews());
            model.addAttribute("shopData", shopData);

            return "user-account";
        }
        else {
            userDto.setId(user.getId());
            userService.updateUserData(userDto);
            return "redirect:/personalAccount";
        }
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
    @GetMapping("/personalAccount/user/{id}/edit")
    public String createUserForm(Model model,@PathVariable String id){


        return "user-manage";
    }
    @PostMapping("/personalAccount/user/{id}/edit")
    public String editUser(Model model,@PathVariable String id){


        return "redirect:/personalAccount";
    }
    @GetMapping("/search")
    public ResponseEntity<List<UserDto>> searchUsers(@RequestParam("user") String username) {
        List<UserEntity> userEntities = userService.findUsersByUsernameContaining(username);
        List<UserDto> userDTOs = new ArrayList<>();
        for(UserEntity userEntity: userEntities) {
            UserDto userDTO = UserServiceImpl.entityToDto(userEntity);
            userDTOs.add(userDTO);
        }
        return ResponseEntity.ok(userDTOs);
    }

    @GetMapping("/searchAuthor")
    public ResponseEntity<List<Author>> searchAuthors(@RequestParam("author") String authorName) {
        List<Author> authors = authorService.findAuthorsByNameContaining(authorName);
        return ResponseEntity.ok(authors);
    }

    @GetMapping("/searchPublisher")
    public ResponseEntity<List<Publisher>> searchPublishers(@RequestParam("publisher") String publisherName) {
        List<Publisher> publishers = publisherService.findPublishersByNameContaining(publisherName);
        return ResponseEntity.ok(publishers);
    }
}
