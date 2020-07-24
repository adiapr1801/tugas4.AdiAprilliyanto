package com.adi.tugas4.Service;

import com.adi.tugas4.Model.BookCategory;
import com.adi.tugas4.Repository.BookCategoryRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BookCategoryService {

    @Autowired
    BookCategoryRepository bookCategoryRepository;


    public List<BookCategory> findAll() {
        return bookCategoryRepository.findAll();
    }


    public boolean saveBookCatg(BookCategory bodycatg) {
        BookCategory bookCategory;
        try {
            bookCategory = bookCategoryRepository.save(bodycatg);
            return true;
        } catch (Exception e) {
            return false;
        }

    }

    public boolean UpdateBookCatg(BookCategory body) {
        BookCategory bookCat = bookCategoryRepository.findById(body.getId());
        if (bookCat != null) {
            try {
                bookCategoryRepository.save(body);
                return true;
            } catch (Exception e) {
                return false;
            }
        } else {
            return false;
        }
    }


    public boolean DeleteBookCatg(int id) {
        BookCategory result = bookCategoryRepository.findById(id);
        if (result != null) {
            try {
                bookCategoryRepository.delete(result);
                return true;
            } catch (Exception e) {
                return false;
            }
        } else {
            return false;
        }
    }

    public ResponseEntity<Map<String, Object>> getAllBookCategoryPage(String search, int page, int size) {
        try {
            List<BookCategory> bookCategories;
            PageRequest pageable = PageRequest.of(page, size);

            Page<BookCategory> bookCategoryPage;
            if (search == null) {
                bookCategoryPage = bookCategoryRepository.findAll(pageable);
            } else {
                bookCategoryPage = bookCategoryRepository.findByNameContaining(search, pageable);
            }
            bookCategories = bookCategoryPage.getContent();

            if (bookCategories.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            Map<String, Object> response = new HashMap<>();
            response.put("bookCategory", bookCategories);
            response.put("currentPage", bookCategoryPage.getNumber());
            response.put("totalItems", bookCategoryPage.getTotalElements());
            response.put("totalPages", bookCategoryPage.getTotalPages());
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }


    }
}



