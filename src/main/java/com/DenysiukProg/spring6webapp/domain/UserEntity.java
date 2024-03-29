package com.DenysiukProg.spring6webapp.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.aspectj.weaver.ast.Or;
import org.springframework.web.bind.annotation.SessionAttribute;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String firstName;
    private String lastName;
    private String address;
    private String phoneNumber;
    private String email;
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_shopping_cart",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "book_id"))
    private List<Book> shoppingCart = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private Set<Review> reviews = new HashSet<>();

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "users_roles", joinColumns = {@JoinColumn(name = "user_id",referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id")}
    )
    private Set<Role> roles = new HashSet<>();

    @OneToMany(mappedBy = "userbuyer_id")
    private Set<Order> userOrders = new HashSet<>();

    public void addRole(Role role) {
        this.roles.add(role);
    }
    public void addToShoppingCart(Book book) {
        this.shoppingCart.add(book);
    }

    public void removeFromShoppingCart(Book book) {
        this.shoppingCart.remove(book);
    }

    public List<Book> getBooks(){
        List<Book> usersBooks = new ArrayList<>();
        for(Order order : userOrders){
            while (order.getOrderedBooks().iterator().hasNext()) {
                usersBooks.add(order.getOrderedBooks().iterator().next());
            }
        }
        return usersBooks;
    }
    @Override
    public String toString() {

        return "UserEntity{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
