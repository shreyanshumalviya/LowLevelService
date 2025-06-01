package com.make73.annotation_processor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class AnnotationProcessor extends SpringBootServletInitializer {
    public static void main(String[] args) {
        SpringApplication.run(AnnotationProcessor.class, args);
    }
}
