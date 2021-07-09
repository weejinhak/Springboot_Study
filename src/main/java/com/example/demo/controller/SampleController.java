package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.log4j.Log4j2;

@RestController
@Log4j2
public class SampleController {

    @GetMapping("/hello")
    public String[] hello() {
        System.out.println("here");
        return new String[]{
                "Hello", "World"
        };
    }
    
    

}
