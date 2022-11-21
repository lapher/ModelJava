package com;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.system.*")
public class NewModelApplication {

	public static void main(String[] args) {
		SpringApplication.run(NewModelApplication.class, args);
	}

}
