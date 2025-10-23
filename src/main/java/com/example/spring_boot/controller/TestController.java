package com.example.spring_boot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller  // Changed from @RestController
@RequestMapping("/")
public class TestController
{

    @GetMapping
    public String index()
    {
        return "test/index";  // Returns index.html or index.jsp from templates folder
    }
}