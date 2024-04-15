package com.DenysiukProg.spring6webapp.controllers;

import com.DenysiukProg.spring6webapp.domain.dto.BookDto;
import com.DenysiukProg.spring6webapp.domain.dto.ShoppingCartItem;
import com.DenysiukProg.spring6webapp.services.Interfaces.BookService;
import com.DenysiukProg.spring6webapp.services.Interfaces.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
/**
 * Controller for managing shopping cart operations, such as displaying the shopping cart, adding books to the cart,
 * deleting books from the cart, and purchasing books in the cart.
 */
@Controller
@AllArgsConstructor
public class ShoppingCartController {
    private final BookService bookService;
    /**
     * Displaying the shopping cart page.
     *
     * @param session the HTTP session to store shopping cart information
     * @return the name of the view template
     */
    @GetMapping("/home/shoppingCart")
    public String getShoppingCart(Model model, HttpSession session) {
        ArrayList<ShoppingCartItem> shoppingCart = (ArrayList<ShoppingCartItem>) session.getAttribute("shoppingCart");
        Map<BookDto,Integer> shoppingCartBooks = new HashMap<>();

        for(ShoppingCartItem sci: shoppingCart){
            shoppingCartBooks.put(bookService.findByID(sci.getBookId()), sci.getQuantity());
        }

        model.addAttribute("shoppingCartBooks", shoppingCartBooks);
        return "user-shopcart";
    }
    /**
     * Deleting a book from the shopping cart.
     *
     * @param bookId the ID of the book to delete
     * @param session the HTTP session containing the shopping cart
     * @return the redirection URL to the shopping cart page
     */
    @GetMapping("/home/shoppingCart/deleteBook")
    public String deleteBookFromCart(@RequestParam("bookId") Long bookId,
                                     HttpSession session) {

        //get user shopping cart from session attribute
        ArrayList<ShoppingCartItem> shoppingCart = (ArrayList<ShoppingCartItem>) session.getAttribute("shoppingCart");
        Iterator<ShoppingCartItem> iterator = shoppingCart.iterator();

        while (iterator.hasNext()) {
            ShoppingCartItem item = iterator.next();
            if (item.getBookId().equals(bookId)) {
                iterator.remove();
                break;
            }
        }
        return "redirect:/home/shoppingCart";
    }
    /**
     * Purchasing books in the shopping cart.
     *
     * @param shoppingCartBooks the map containing book details and quantities
     * @param session the HTTP session containing the shopping cart
     * @return the redirection URL to the shopping cart page
     */
    @PostMapping("/home/shoppingCart/buy")
    public String buyBooks(@ModelAttribute("shoppingCartBooks") Map<BookDto, Integer> shoppingCartBooks,
                           HttpSession session) {
        for (Map.Entry<BookDto, Integer> entry : shoppingCartBooks.entrySet()) {
            System.out.println("Book: " + entry.getKey().getTitle() + ", Quantity: " + entry.getValue());
        }

        return "redirect:/home/shoppingCart";
    }
    /*@PostMapping("/home/shoppingCart/printMap")
    public String printMap(@RequestParam Map<String, String> allParams) {
        for (Map.Entry<String, String> entry : allParams.entrySet()) {
            System.out.println("Key: " + entry.getKey() + ", Value: " + entry.getValue());
        }

        return "redirect:/home";
    }*/
}
