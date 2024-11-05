package com.example.demo.controller;
import com.example.demo.model.Book;
import com.example.demo.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller

public class HomeController {
    @Autowired
    private BookRepository bookRepository;

    @GetMapping("/")
    public String landingPage(Model model) {
        List<Book> featuredBooks = bookRepository.findAll(); // Replace with actual featured logic
        model.addAttribute("featuredBooks", featuredBooks);
        return "landing";
    }
}
