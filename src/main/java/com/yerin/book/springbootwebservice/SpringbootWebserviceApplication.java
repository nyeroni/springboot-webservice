package com.yerin.book.springbootwebservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
public class SpringbootWebserviceApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootWebserviceApplication.class, args);
    }

}
