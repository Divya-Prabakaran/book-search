package com.sky.library.domain.dao.entity;
/**
 * Copyright © 2015 Sky plc All Rights reserved.
 * Please do not make your solution publicly available in any way e.g. post in forums or commit to GitHub.
 **/

import lombok.*;

import javax.persistence.*;

/**
 * @Author: Divya Prabakaran
 **/

@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@EqualsAndHashCode(callSuper = false)
@ToString(callSuper=true)
@Table( name = "BOOK")
public class Book {
    @Id
    @GeneratedValue (strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    private Long id;

    @Column(name = "bookReference", nullable = false, unique = true)
    private String bookReference;

    @Column(name = "bookTitle")
    private String bookTitle;

    @Column(name = "bookSummary")
    private String bookSummary;

}
