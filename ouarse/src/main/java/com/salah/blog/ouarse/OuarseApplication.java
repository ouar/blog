package com.salah.blog.ouarse;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@Slf4j
public class OuarseApplication {

	public static void main(String[] args) {
		log.info("start");
		SpringApplication.run(OuarseApplication.class, args);
	}

}
