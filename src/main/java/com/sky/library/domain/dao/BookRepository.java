package com.sky.library.domain.dao;
/**
 * Copyright © 2015 Sky plc All Rights reserved.
 * Please do not make your solution publicly available in any way e.g. post in forums or commit to GitHub.
 **/

import com.sky.library.domain.dao.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

/**
 * @Author: Divya Prabakaran
 **/

@Component
@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
}
