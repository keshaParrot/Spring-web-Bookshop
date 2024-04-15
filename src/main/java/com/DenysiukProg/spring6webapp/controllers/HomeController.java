package com.DenysiukProg.spring6webapp.controllers;

import com.DenysiukProg.spring6webapp.services.Interfaces.BookService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * controller to process method and forms on the home page
 */
@Controller
@AllArgsConstructor
public class HomeController {
    private final BookService bookService;

    /**
     * Display home page with all books
     *
     * @return the redirection URL to the book page
     */
    @RequestMapping("/home")
    public String getBooks(Model model){
        model.addAttribute("books", bookService.findAll());
        return "home";
    }
}
