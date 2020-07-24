package com.adi.tugas4.Repository;


import com.adi.tugas4.Model.BookCategory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookCategoryRepository extends JpaRepository<BookCategory,Integer> {
    BookCategory findById(int id);
    Page<BookCategory> findByNameContaining(String search, Pageable pageable);
}