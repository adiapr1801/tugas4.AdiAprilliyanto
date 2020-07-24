package com.adi.tugas4.Service;

import com.adi.tugas4.Model.Book;
import com.adi.tugas4.Model.BookCategory;
import com.adi.tugas4.Repository.BookCategoryRepository;
import com.adi.tugas4.Repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class BookService {

    @Autowired
    BookRepository bookRepository;

    @Autowired
    BookCategoryRepository bookCategoryRepository;


    public boolean saveBook(Book body) {

        try {
            bookRepository.save(body);
            return true;
        }catch (Exception e){
            return false;
        }
    }



    public boolean DeleteBook(int id) {
        Book result = bookRepository.findById(id);
        if (result != null) {
            try {
                bookRepository.delete(result);
                return true;
            } catch (Exception e) {
                return false;
            }

        } else {
            return false;
        }
    }

    public boolean UpdateBook(Book body) {
        Book bookResult = bookRepository.findById(body.getId());
        BookCategory bc = body.getBookcategory();
        if (bookResult != null) {
            try {
                bookRepository.save(body);
                bookCategoryRepository.save(bc);
                return true;
            } catch (Exception e) {
                return false;
            }
        } else {
        }
        return false;
    }


    public ResponseEntity<Map<String, Object>> getAllTitle(String search, int page, int size) {
        try {
            List<Book> books = new ArrayList<>();
            Pageable pageable = PageRequest.of(page,size);

            Page<Book> bookPage;
            if (search == null) {
                bookPage = bookRepository.findAll(pageable);
            }else {
                bookPage = bookRepository.findByTitleContaining(search, pageable);
            }
            books = bookPage.getContent();

            if (books.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            Map<String, Object> response = new HashMap<>();
            response.put("books", books);
            response.put("currentPage", bookPage.getNumber());
            response.put("totalItems", bookPage.getTotalElements());
            response.put("totalPages", bookPage.getTotalPages());
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    public List<Book> getAllBookByTitle(String title) {
        return bookRepository.findByTitleContaining(title);
    }

    public List<Book> getAllBookByCategory(int id) {
        return bookRepository.findBycategoryidContaining(id);
    }
}
