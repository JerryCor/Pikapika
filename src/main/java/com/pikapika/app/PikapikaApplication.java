package com.pikapika.app;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan
@MapperScan("com.pikapika.app.**.mapper")
public class PikapikaApplication {

	public static void main(String[] args) {
		SpringApplication.run(PikapikaApplication.class, args);
	}
	
}
