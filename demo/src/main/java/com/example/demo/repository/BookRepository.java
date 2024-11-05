package com.example.demo.repository;
//repositories are like the code that allows you to perform CRUD applications
import com.example.demo.model.Book;
import com.example.demo.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findByCategoryName(String categoryName);
    List<Book> findByTitleContainingIgnoreCase(String title);
    List<Book> findByAuthorContainingIgnoreCase(String author);
}
