package com.example.demo.controller;
//for displaying books
import com.example.demo.model.Book;
import com.example.demo.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;

@Controller
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping("/")
    public String home(Model model) {
        List<Book> books = bookService.findAll();
        model.addAttribute("books", books);
        return "home";
    }

    @GetMapping("/books/category/{categoryName}")
    public String getBooksByCategory(@PathVariable String categoryName, Model model) {
        List<Book> books = bookService.findByCategoryName(categoryName);
        model.addAttribute("books", books);
        return "home";
    }

    @GetMapping("/books/search")
    public String searchBooks(@RequestParam("keyword") String keyword, Model model) {
        List<Book> books = bookService.searchBooks(keyword);
        model.addAttribute("books", books);
        return "home";
    }

    @GetMapping("/books/{id}")
    public String viewBookDetails(@PathVariable Long id, Model model) {
        Book book = bookService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid book Id:" + id));
        model.addAttribute("book", book);
        return "book_details";
    }

    // Additional methods for adding to cart, purchasing, etc.
}
