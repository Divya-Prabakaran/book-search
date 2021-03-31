package com.sky.library.expose;
/**
 * Copyright © 2015 Sky plc All Rights reserved.
 * Please do not make your solution publicly available in any way e.g. post in forums or commit to GitHub.
 **/

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sky.library.domain.dao.BookRepository;
import com.sky.library.domain.dao.entity.Book;
import com.sky.library.exception.BookNotFoundException;
import com.sky.library.exception.GlobalExceptionHandler;
import com.sky.library.model.ApiError;
import com.sky.library.model.Summary;
import com.sky.library.service.BookService;
import com.sky.library.service.BookServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;

import java.util.ArrayList;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @Author: Divya Prabakaran
 **/

@Slf4j
@AutoConfigureMockMvc
@WebMvcTest
@ContextConfiguration(classes = { BookController.class,
        BookService.class, BookServiceImpl.class, BookNotFoundException.class,
        GlobalExceptionHandler.class
})
public class BookControllerTests {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    BookRepository bookRepository;

    @MockBean
    BookServiceImpl bookService;

    @Test
    void testGetBookOnSuccess() throws Exception {
        Book book = new Book();
        book.setBookReference("BOOK-Sample");
        book.setBookTitle("The Gruffalo");
        book.setId(1L);
        book.setBookSummary("A mouse taking a walk in the woods.");
        doReturn(book).when(bookService).retrieveBook(anyString());

        mockMvc.perform(get("/book/retrieve")
                .header("bookReference", "BOOK-Sample")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void testGetBookWithNoHeader() throws Exception {
        ResultActions resultActions = mockMvc.perform(get("/book/retrieve")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isInternalServerError());

        MvcResult result = resultActions.andReturn();
        String contentAsString = result.getResponse().getContentAsString();

        ApiError response = objectMapper.readValue(contentAsString, ApiError.class);

        assert(response.getMessage()).equals("Missing request header 'bookReference' for method parameter of type String");
    }

    @Test
    void testGetBookWithInvalidText() throws Exception {
        doReturn(false).when(bookService).isValidBookReference(Optional.of((String) anyString()));

        ResultActions resultActions = mockMvc.perform(get("/book/retrieve")
                .header("bookReference", anyString())
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void testGetBookWithWrongReference() throws Exception {
        doReturn(false).when(bookService).isValidBookReference(Optional.of(anyString()));
        doReturn(null).when(bookService).getAllBooks();
        doReturn(null).when(bookService).retrieveBook(anyString());

        ResultActions resultActions = mockMvc.perform(get("/book/retrieve")
                .header("bookReference", "BOOK-Sample")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void testGetBookSummaryOnSuccess() throws Exception {
        given(bookRepository.findAll()).willReturn(new ArrayList<Book>());
        given(bookService.getBookSummary(anyString())).willReturn(new Summary());

        mockMvc.perform(get("/book/summary")
                .header("bookReference", "BOOK-Sample")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void testGetBookSummaryWithNoHeader() throws Exception {
        ResultActions resultActions = mockMvc.perform(get("/book/summary")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isInternalServerError());

        MvcResult result = resultActions.andReturn();
        String contentAsString = result.getResponse().getContentAsString();

        ApiError response = objectMapper.readValue(contentAsString, ApiError.class);

        assert(response.getMessage()).equals("Missing request header 'bookReference' for method parameter of type String");
    }

}
