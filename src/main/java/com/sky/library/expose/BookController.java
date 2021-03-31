package com.sky.library.expose;
/**
 * Copyright © 2015 Sky plc All Rights reserved.
 * Please do not make your solution publicly available in any way e.g. post in forums or commit to GitHub.
 **/

import com.sky.library.exception.BookNotFoundException;
import com.sky.library.model.ApiResponse;
import com.sky.library.service.BookServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: Divya Prabakaran
 **/

@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    private BookServiceImpl bookService;

    @GetMapping(path = "/retrieve" , produces = MediaType.APPLICATION_JSON_VALUE )
    public ResponseEntity<ApiResponse> getBook(@RequestHeader String bookReference) throws BookNotFoundException {
        ApiResponse apiResponse = new ApiResponse(bookService.retrieveBook(bookReference));

        if(bookService.retrieveBook(bookReference) != null){
            return new ResponseEntity<>(apiResponse , HttpStatus.OK);
        } else {
            return new ResponseEntity<>(apiResponse , HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(path = "/summary" , produces = MediaType.APPLICATION_JSON_VALUE )
    public ResponseEntity<ApiResponse> getBookSummary(@RequestHeader String bookReference) throws BookNotFoundException {
        ApiResponse apiResponse = new ApiResponse(bookService.getBookSummary(bookReference));

        if(bookService.getBookSummary(bookReference) != null){
            return new ResponseEntity<>(apiResponse , HttpStatus.OK);
        } else {
            return new ResponseEntity<>(apiResponse , HttpStatus.NOT_FOUND);
        }
    }

}
