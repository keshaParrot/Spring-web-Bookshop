package com.DenysiukProg.spring6webapp.dto;

import com.DenysiukProg.spring6webapp.domain.Book;
import com.DenysiukProg.spring6webapp.domain.Order;
import com.DenysiukProg.spring6webapp.domain.Review;
import com.DenysiukProg.spring6webapp.domain.Role;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Setter
@Getter
public class UserDto {
    private Long id;
    @NotEmpty(message = "Username should not be empty")
    private String username;
    @NotEmpty(message = "Firstname should not be empty")
    private String firstName;
    @NotEmpty(message = "Lastname should not be empty")
    private String lastName;
    @NotEmpty(message = "Email should not be empty")
    private String email;
    private String address;
    @Pattern(regexp="\\d{9}", message="Phone number should to contains 9 number")
    private String phoneNumber;
    private List<Book> shoppingCart = new ArrayList<>();
    private Set<Role> roles = new HashSet<>();
    private Set<Review> reviews = new HashSet<>();
    private Set<Order> userOrders = new HashSet<>();

    public boolean hasRole(String name){
        for (Role role : roles) {
            if (role.getName().equals(name)) return true;
        }
        return false;
    }
}

