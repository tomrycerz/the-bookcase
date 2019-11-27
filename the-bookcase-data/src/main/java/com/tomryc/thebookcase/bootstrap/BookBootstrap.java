package com.tomryc.thebookcase.bootstrap;

import com.tomryc.thebookcase.model.Author;
import com.tomryc.thebookcase.model.Book;
import com.tomryc.thebookcase.model.Category;
import com.tomryc.thebookcase.model.Location;
import com.tomryc.thebookcase.repositories.AuthorRepository;
import com.tomryc.thebookcase.repositories.BookRepository;
import com.tomryc.thebookcase.repositories.CategoryRepository;
import com.tomryc.thebookcase.repositories.LocationRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Slf4j
@Component
public class BookBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private final BookRepository bookRepository;
    private final CategoryRepository categoryRepository;
    private final LocationRepository locationRepository;
    private final AuthorRepository authorRepository;

    public BookBootstrap(BookRepository bookRepository, CategoryRepository categoryRepository, LocationRepository locationRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.categoryRepository = categoryRepository;
        this.locationRepository = locationRepository;
        this.authorRepository = authorRepository;
    }

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent event) {
        saveBooks();
        log.debug("Loading Bootstrap Data");
    }

    private void saveBooks() {

        Optional<Category> actionOptional = categoryRepository.findByDescription("Action");
        if (!actionOptional.isPresent())
            throw new RuntimeException("Expected category not found!");

        Optional<Category> dramaOptional = categoryRepository.findByDescription("Drama");
        if (!dramaOptional.isPresent())
            throw new RuntimeException("Expected category not found!");

        Category action = actionOptional.get();
        Category drama = dramaOptional.get();

        Optional<Location> myRoomOptional = locationRepository.findByDescription("My Room");
        if (!myRoomOptional.isPresent())
            throw new RuntimeException("Expected location not found!");

        Optional<Location> livingRoomOptional = locationRepository.findByDescription("Living Room");
        if (!myRoomOptional.isPresent())
            throw new RuntimeException("Expected location not found!");

        Location myRoom = myRoomOptional.get();
        Location livingRoom = livingRoomOptional.get();

        //locationRepository.save(livingRoom);
        //locationRepository.save(myRoom);

        Author author1 = new Author();
        author1.setName("Jaroslaw Iwaszkiewicz");

        Author author2 = new Author();
        author2.setName("Erich Gamma");

        Author author3 = new Author();
        author3.setName("Richard Helm");

        authorRepository.save(author1);
        authorRepository.save(author2);
        authorRepository.save(author3);

        Book book1 = new Book();
        book1.setTitle("Panny z Wilka");
        book1.setCategory(drama);
        book1.setIsbn("xxxxx");
        book1.setAuthor(author1);
        book1.setUrl("https://lubimyczytac.pl/ksiazka/256153/panny-z-wilka");
        book1.setLocation(myRoom);

        Book book2 = new Book();
        book2.setTitle("Wzorce projektowe");
        book2.setCategory(action);
        book2.setIsbn("xxxxx");
        book2.setAuthor(author2);
        book2.setUrl("https://helion.pl/ksiazki/wzorce-projektowe-elementy-oprogramowania-obiektowego-" +
                "wielokrotnego-uzytku-erich-gamma-richard-helm-ralph-johnson-john-vli,wzoelv.htm#format/d");
        book2.setLocation(livingRoom);

        livingRoom.getBooks().add(book1);
        myRoom.getBooks().add(book2);
        bookRepository.save(book1);
        bookRepository.save(book2);

    }

}
