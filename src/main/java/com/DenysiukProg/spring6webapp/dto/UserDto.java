package com.DenysiukProg.spring6webapp.dto;

import com.DenysiukProg.spring6webapp.domain.Book;
import com.DenysiukProg.spring6webapp.domain.Review;
import com.DenysiukProg.spring6webapp.domain.Role;
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
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private Set<Role> roles = new HashSet<>();
    private Set<Review> reviews = new HashSet<>();
    private List<Book> books = new ArrayList<>();
}

