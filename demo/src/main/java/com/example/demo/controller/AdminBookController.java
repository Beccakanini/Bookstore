package com.example.demo.controller;
import com.example.demo.model.Book;
import com.example.demo.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.BindingResult;

@Controller
@RequestMapping("/admin/books")
public class AdminBookController {
    @Autowired
    private BookRepository bookRepository;

    // Display list of books
    @GetMapping
    public String listBooks(Model model) {
        model.addAttribute("books", bookRepository.findAll());
        return "admin/books/list";
    }

    // Show form to add a new book
    @GetMapping("/new")
    public String showAddBookForm(Model model) {
        model.addAttribute("book", new Book());
        return "admin/books/new";
    }

    // Handle adding a new book
    @PostMapping
    public String addBook(@ModelAttribute("book") Book book, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "admin/books/new";
        }
        bookRepository.save(book);
        return "redirect:/admin/books";
    }

    // Show form to edit a book
    @GetMapping("/edit/{id}")
    public String showEditBookForm(@PathVariable("id") Long id, Model model) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid book Id:" + id));
        model.addAttribute("book", book);
        return "admin/books/edit";
    }

    // Handle editing a book
    @PostMapping("/edit/{id}")
    public String editBook(@PathVariable("id") Long id, @ModelAttribute("book") Book book,
                           BindingResult result, Model model) {
        if (result.hasErrors()) {
            book.setId(id);
            return "admin/books/edit";
        }
        bookRepository.save(book);
        return "redirect:/admin/books";
    }

    // Handle deleting a book
    @GetMapping("/delete/{id}")
    public String deleteBook(@PathVariable("id") Long id, Model model) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid book Id:" + id));
        bookRepository.delete(book);
        return "redirect:/admin/books";
    }
}
