package com.adi.tugas4.Repository;

import com.adi.tugas4.Model.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {
    Book findById(int id);

    Page<Book> findByTitleContaining(String search, Pageable pageable);
    List<Book>findByTitleContaining(String title);

    @Query(value = "SELECT * FROM book WHERE bookcategory = :id ", nativeQuery = true)
    List<Book> findBycategoryidContaining(@Param("id") int id);
}