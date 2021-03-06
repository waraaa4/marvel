package com.choi.marvel.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {


    @GetMapping("/index")
    public String index() {
        return "index";
    }

    @GetMapping("/character")
    public String character() {
        return "character";
    }

    @GetMapping("/movie")
    public String movie() {
        return "movie";
    }

    @GetMapping("/community")
    public String community() {
        return "community";
    }


}