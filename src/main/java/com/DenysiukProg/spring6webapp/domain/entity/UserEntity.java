package com.DenysiukProg.spring6webapp.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Entity class representing a user.
 */
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

    @OneToMany(mappedBy = "user")
    private Set<Review> reviews = new HashSet<>();

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "users_roles",
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id")}
    )
    private Set<Role> roles = new HashSet<>();

    @OneToMany(mappedBy = "userBuyer")
    private Set<UserOrder> userOrders = new HashSet<>();

    public void addRole(Role role) {
        this.roles.add(role);
    }

    public List<Book> getBooks(){
        List<Book> usersBooks = new ArrayList<>();
        for(UserOrder order : userOrders){
            while (order.getOrderedItems().iterator().hasNext()) {
                usersBooks.add(order.getOrderedItems().iterator().next().getBook());
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
