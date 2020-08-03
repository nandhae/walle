package com.tw.walle;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.tw")
public class WallEApplication {
    public static void main(String[] args) {
        SpringApplication.run(WallEApplication.class, args);
    }
}
