package com.DenysiukProg.spring6webapp.bootstrap;

import com.DenysiukProg.spring6webapp.domain.*;
import com.DenysiukProg.spring6webapp.repositories.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Date;


@Component
public class BootstrapData implements CommandLineRunner {
    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final ReviewRepository reviewRepository;

    public BootstrapData(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository, UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder, ReviewRepository reviewRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.reviewRepository = reviewRepository;
    }

    @Override
    public void run(String... args) {
        /*bookRepository.deleteAll();
        //authorRepository.deleteAll();
        //publisherRepository.deleteAll();

        Author eric = new Author();
        eric.setFirstName("Eric");
        eric.setLastName("Evans");

        Book ddd = new Book();
        ddd.setTitle("Domain Driven Design");
        ddd.setIsbn("123456");
        ddd.setPrice("15");

        Author ericSaved = authorRepository.save(eric);
        Book dddSaved = bookRepository.save(ddd);

        Author rod = new Author();
        rod.setFirstName("Rod");
        rod.setLastName("Johnson");

        Book noEJB = new Book();
        noEJB.setTitle("J2EE Development without EJB");
        noEJB.setIsbn("54757585");

        Calendar calendar = Calendar.getInstance();
        calendar.set(2023, Calendar.JANUARY, 1, 0, 0, 0);
        calendar.set(Calendar.MILLISECOND, 0); // Clear milliseconds for consistency
        noEJB.setPublicationDate(calendar.getTime());
        noEJB.setPrice("25");

        Author rodSaved = authorRepository.save(rod);
        Book noEJBSaved = bookRepository.save(noEJB);

        ericSaved.getBooks().add(dddSaved);
        rodSaved.getBooks().add(noEJBSaved);
        dddSaved.getAuthors().add(ericSaved);
        noEJBSaved.getAuthors().add(rodSaved);


        Publisher ababagalamaga = new Publisher();
        ababagalamaga.setPublisherName("a-ba-ba-ga-la-ma-ga");
        ababagalamaga.setAddress("Baseyna st");
        ababagalamaga.setState("Ukraine");
        ababagalamaga.setCity("Kyiv");
        ababagalamaga.setZip("01004");
        Publisher savedPublisher = publisherRepository.save(ababagalamaga);

        dddSaved.setPublisher(savedPublisher);
        noEJBSaved.setPublisher(savedPublisher);

        authorRepository.save(ericSaved);
        authorRepository.save(rodSaved);
        bookRepository.save(dddSaved);
        bookRepository.save(noEJBSaved);*/

        reviewRepository.deleteAll();
        userRepository.deleteAll();


        UserEntity user = new UserEntity();
        user.setUsername("1");
        user.setPassword(passwordEncoder.encode("1"));
        user.setEmail("1");
        user.addRole(roleRepository.findByName("USER"));
        user.addRole(roleRepository.findByName("ADMIN"));
        userRepository.save(user);

        Review review = new Review();
        review.setUser(user);
        review.setBook(bookRepository.getReferenceById(802L));
        review.setContent("is good");
        review.setRating(5);
        review.setReviewDate(new Date());
        reviewRepository.save(review);


        UserEntity user2 = new UserEntity();
        user2.setUsername("21");
        user2.setPassword(passwordEncoder.encode("21"));
        user2.setEmail("21");
        user2.addRole(roleRepository.findByName("USER"));
        userRepository.save(user2);

        UserEntity user3 = new UserEntity();
        user3.setUsername("24");
        user3.setPassword(passwordEncoder.encode("24"));
        user3.setEmail("24");
        user3.addRole(roleRepository.findByName("USER"));
        userRepository.save(user3);

        System.out.println(user.getRoles());




        System.out.println(authorRepository.findAll());
        System.out.println(publisherRepository.findAll());

        System.out.println(user);
        System.out.println(review);

        System.out.println("debug Info");
        System.out.println("Author Count: " + authorRepository.count());
        System.out.println("Book Count: " + bookRepository.count());
        System.out.println("user2 Count: " + userRepository.count());
        System.out.println("role Count: " + roleRepository.count());
        System.out.println("Publisher Count: " + publisherRepository.count());
        System.out.println("\n" + userRepository.getAllUsernames());
    }
}
