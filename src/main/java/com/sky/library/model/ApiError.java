package com.sky.library.model;
/**
 * Copyright © 2015 Sky plc All Rights reserved.
 * Please do not make your solution publicly available in any way e.g. post in forums or commit to GitHub.
 **/

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * @Author: Divya Prabakaran
 **/

@AllArgsConstructor()
@Getter
@Setter
public class ApiError {
    private Date timestamp;
    private String message;
    private String details;
}
