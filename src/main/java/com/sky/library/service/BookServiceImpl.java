package com.sky.library.service;
/**
 * Copyright © 2015 Sky plc All Rights reserved.
 * Please do not make your solution publicly available in any way e.g. post in forums or commit to GitHub.
 **/

import com.google.common.annotations.VisibleForTesting;
import com.sky.library.domain.dao.entity.Book;
import com.sky.library.exception.BookNotFoundException;
import com.sky.library.domain.dao.BookRepository;
import com.sky.library.model.Summary;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * @Author: Divya Prabakaran
 **/

@Slf4j
@Service
public class BookServiceImpl implements BookService{

    private static final String BOOK_REFERENCE_PREFIX = "BOOK-";

    @Autowired
    BookRepository bookRepository;

    @Override
    public Book retrieveBook(String bookReference) throws BookNotFoundException {
        log.info("Entering method retrieveBook");
        Book result = null;

        if(isValidBookReference(Optional.ofNullable(bookReference))) {
            List<Book> allBooks = getAllBooks();
            if(allBooks != null) {
                result = allBooks.stream()
                            .filter(element -> Objects.equals(element.getBookReference(), bookReference))
                            .findAny().orElse(null);
                if(result == null)
                    throw new BookNotFoundException("Book not found with name " + bookReference);
            }
        } else {
            throw new BookNotFoundException("Book reference should start with BOOK-");
        }
        return result;
    }

    @Override
    public Summary getBookSummary(String bookReference) throws BookNotFoundException {
        log.info("Entering method getBookSummary");
        Summary summary = new Summary();

        if(isValidBookReference(Optional.ofNullable(bookReference))) {
            Book book = retrieveBook(bookReference);
            if(book != null){
                StringBuilder result = new
                        StringBuilder("");
                result.append("[" + bookReference + "] ")
                        .append(book.getBookTitle() + " - ")
                        .append(book.getBookSummary());
                summary.setSummary(result.toString());
            } else {
                throw new BookNotFoundException("Book not found with name " + bookReference);
            }
        } else {
            throw new BookNotFoundException("Book reference should start with BOOK-");
        }
        return summary;
    }

    public List<Book> getAllBooks() throws BookNotFoundException {
        log.info("Entering method getAllBooks");
        List<Book> allBooks = null;

        /* --- retrieve all books --- */
        final Iterable<Book> books = bookRepository.findAll();
        if ( books != null ) {
            allBooks = new ArrayList<>(5);
            books.forEach( allBooks :: add);
            log.info( allBooks.size() + " books retrieved successfully");
        } else {
            log.warn("No books found in the database");
            throw new BookNotFoundException("No books found in the database");
        }
        return allBooks;
    }

    public Boolean isValidBookReference(Optional<String> bookReference) {
        log.info("Entering method isValidBookReference");
        return bookReference.isPresent() && bookReference.get().startsWith(BOOK_REFERENCE_PREFIX);
    }

}
