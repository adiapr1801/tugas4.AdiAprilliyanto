package com.adi.tugas4.Controller;


import com.adi.tugas4.Model.Book;
import com.adi.tugas4.Model.User;
import com.adi.tugas4.Repository.BookRepository;
import com.adi.tugas4.Service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/book")
public class BookController {

    @Autowired
    BookService bookService;
    @Autowired
    BookRepository bookRepository;
    //---------------------------------------------------------------------------------------->>
    @GetMapping("")
    List<Book> getAllBooks(){return  bookRepository.findAll();}

    private Sort.Direction getSortDirection(String direction) {
        if (direction.equals("asc")) {
            return Sort.Direction.ASC;
        } else if (direction.equals("desc")) {
            return Sort.Direction.DESC;
        }
    //---------------------------------------------------------------------------------------->>
        return Sort.Direction.ASC;
    }
    //---------------------------------------------------------------------------------------->>
    //shorting secara descending
    @GetMapping("/desc")
    public ResponseEntity<List<User>> getAllTutorials(@RequestParam(defaultValue = "id,desc") String[] sort) {
        try {
            List<Sort.Order> orders = new ArrayList<Sort.Order>();
            if (sort[0].contains(",")) {
                for (String sortOrder : sort) {
                    String[] _sort = sortOrder.split(",");
                    orders.add(new Sort.Order(getSortDirection(_sort[1]), _sort[0]));
                }
            } else {
                orders.add(new Sort.Order(getSortDirection(sort[1]), sort[0]));
            }
            List<Book> tutorials = bookRepository.findAll(Sort.by(orders));
            if (tutorials.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity(tutorials, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    //---------------------------------------------------------------------------------------->>
    @PostMapping("/insert")
    public Map<String,Object> addNewBook(@RequestBody Book body){
        Map<String ,Object> result = new HashMap<>();
        if (bookService.saveBook(body)){
            result.put("success", true);
            result.put("message","Data buku berhasil ditambahkan");
        }else {
            result.put("successs",false);
            result.put("message","Data buku gagal ditambahkan");
        }
        return result;
    }
    //---------------------------------------------------------------------------------------->>
    @DeleteMapping("/delete")
        //id dr param postman
    Map<String,Object>deleteBook(@RequestParam int id){
        Map<String,Object> result = new HashMap<>();
        if (bookService.DeleteBook(id)) {
            result.put("success", true);
            result.put("message", "Buku berhasil dihapus!");
        } else{
            result.put("success", false);
            result.put("message", "Buku gagal dihapus!");
        } return result;
    }
    //---------------------------------------------------------------------------------------->>
    @PutMapping("/update")
    Map<String,Object>updateBook(@RequestBody Book body){
        System.out.println("body : " + body.toString());
        Map<String,Object> result = new HashMap<>();

        if (bookService.UpdateBook(body)) {
            result.put("success", true);
            result.put("message", "Data buku berhasil diperbaharui!");
        } else{
            result.put("success", false);
            result.put("message", "Data buku gagal diperbaharui!");
        }
        return result;
    }
    //---------------------------------------------------------------------------------------->>
    @GetMapping("/page")
    public ResponseEntity<Map<String ,Object>> getAllBooks(
            @RequestParam(required = false) String search,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "3") int size )
    { return bookService.getAllTitle(search,page,size); }
    //---------------------------------------------------------------------------------------->>
    @GetMapping("/byTitle")
    public List<Book> getUsersByTitle(@RequestParam(required = false)String title)
    { return bookService.getAllBookByTitle(title); }
    //---------------------------------------------------------------------------------------->>
    @GetMapping("/byCategory")
    public List<Book> getUsersByCategory(@RequestParam(required = false)int id )
    { return bookService.getAllBookByCategory(id);

    }

}


