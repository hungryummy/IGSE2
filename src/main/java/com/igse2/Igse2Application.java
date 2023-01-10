package com.igse2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

//@SpringBootApplication(scanBasePackages = {"com.igse2"})
@SpringBootApplication()
@MapperScan(basePackages = "com.igse2.mapper")
public class Igse2Application {

    public static void main(String[] args) {
        SpringApplication.run(Igse2Application.class, args);
    }

}
