package org.thws.bookhub;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class BookhubApplication {

    public static void main(String[] args) {
        SpringApplication.run(BookhubApplication.class, args);
    }

}
