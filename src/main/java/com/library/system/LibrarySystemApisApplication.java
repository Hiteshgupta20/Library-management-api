package com.library.system;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class LibrarySystemApisApplication {

	public static void main(String[] args) {
		SpringApplication.run(LibrarySystemApisApplication.class, args);
	}

	@Bean
	public ModelMapper modalMapper() {
		return new ModelMapper();
	}

}
