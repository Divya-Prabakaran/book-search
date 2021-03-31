package com.sky.library.exception;
/**
 * Copyright © 2015 Sky plc All Rights reserved.
 * Please do not make your solution publicly available in any way e.g. post in forums or commit to GitHub.
 **/

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @Author: Divya Prabakaran
 **/

@Slf4j
@NoArgsConstructor
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class BookNotFoundException extends Exception {
    private static final long serialVersionUID = 4360989537231823353L;

    @Getter
    @Setter
    private String errorMessage = "";

    public BookNotFoundException(String errorMessage) {
        super(errorMessage);
        log.error("Book reference should start with BOOK-");
    }

}
