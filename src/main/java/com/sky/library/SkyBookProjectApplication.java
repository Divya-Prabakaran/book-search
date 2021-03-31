package com.sky.library;
/**
 * Copyright © 2015 Sky plc All Rights reserved.
 * Please do not make your solution publicly available in any way e.g. post in forums or commit to GitHub.
 **/

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * @Author: Divya Prabakaran
 **/

@Slf4j
@SpringBootApplication
@ComponentScan("com.sky.library")
@EnableJpaRepositories("com.sky.library.domain.dao")
@EntityScan("com.sky.library.domain.dao.entity")
public class SkyBookProjectApplication {

	public static void main(String ...a) {
		SpringApplication.run(SkyBookProjectApplication.class);
	}

}
