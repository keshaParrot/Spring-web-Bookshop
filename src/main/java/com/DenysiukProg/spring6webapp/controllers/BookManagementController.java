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
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

/**
 * Controller for managing book-related operations, such as displaying book details, adding books to the shopping cart,
 * editing book properties, and deleting books.
 */
@Controller
@AllArgsConstructor
public class BookManagementController {
    private final BookService bookService;
    private final AuthorService authorService;
    private final PublisherService publisherService;
    private final ReviewService reviewService;
    private final UserService userService;
    private final PurchaseService purchaseService;

    /**
     * Display the Book page.
     *
     * @param id the ID of the book to display
     * @return the name of the view template
     */
    @RequestMapping("/home/book/{id}")
    public String getBook(Model model, @PathVariable Long id){
        BookDto book = bookService.findByID(id);
        UserEntity user = userService.findByUsername(SecurityUtil.getSessionUser());

        model.addAttribute("userData",user ==null? new UserDto(): UserServiceImpl.entityToDto(user));
        model.addAttribute("booksReview", reviewService.findAllForBook(book));
        model.addAttribute("similarBooks", bookService.getBooksByGenreAndAgeGroupIgnoringId(book.getGenre(),book.getAgeGroup(),book.getId()));
        model.addAttribute("book", book);
        return "book";
    }
    /**
     * Adding a book to the shopping cart.
     *
     * @param model the model to add attributes for rendering the view
     * @param id the ID of the book to add to the cart
     * @param session the HTTP session to store cart information
     * @return the redirection URL based on success or failure
     */
    @PostMapping("/home/book/{id}/addToCart")
    public String addBookToCart(Model model, @PathVariable Long id, HttpSession session){
        UserEntity user = userService.findByUsername(SecurityUtil.getSessionUser());

        //check if user is logged in
        if(user!=null){
            purchaseService.putBookOnShoppingCart(id,1, session);
            return "redirect:/home/book/" + id + "?successAdded";
        }

        return "redirect:/home/book/" + id + "?failed";
    }
    /**
     * Display book edit page with form.
     *
     * @param id the ID of the book to edit
     * @return the name of the view template
     */
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
    /**
     * Save changing book properties.
     *
     * @param id the ID of the book to edit
     * @param bookDto the data transfer object containing updated book information
     * @param bindingResult the result of the validation
     * @return the redirection URL to the book page
     */
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
        bookService.updateBook(bookDto);
        return "redirect:/home/book/"+id;
    }
    /**
     * Delete book.
     *
     * @param id the ID of the book to edit
     * @return the redirection URL to the home page
     */
    @GetMapping("/home/book/{id}/delete")
    public String deleteClub(@PathVariable Long id){
        bookService.delete(id);
        return "redirect:/home";
    }
}
