package com.semi.gamespace.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@ServletComponentScan(basePackages = "com.semi.gamespace.member.filter")
@SpringBootApplication
public class GamespaceApplication {
    public static void main(String[] args) {
        SpringApplication.run(GamespaceApplication.class, args);
    }
    //This comment is the first footstep in gamespace world!
}

