package com.DenysiukProg.spring6webapp.bootstrap;

import com.DenysiukProg.spring6webapp.domain.*;
import com.DenysiukProg.spring6webapp.repositories.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;


@Component
public class BootstrapData implements CommandLineRunner {
    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public BootstrapData(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository, UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {
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

        userRepository.deleteAll();

        UserEntity user = new UserEntity();
        user.setUsername("1");
        user.setPassword(passwordEncoder.encode("1"));
        user.setEmail("1");
        Role role = roleRepository.findByName("USER");
        user.setRoles(role);

        UserEntity userSaved = userRepository.save(user);

        System.out.println("debug Info");
        System.out.println("Author Count: " + authorRepository.count());
        System.out.println("Book Count: " + bookRepository.count());
        System.out.println("user Count: " + userRepository.count());
        System.out.println("role Count: " + roleRepository.count());
        System.out.println("Publisher Count: " + publisherRepository.count());
    }
}
