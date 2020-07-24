package com.adi.tugas4.Controller;

import com.adi.tugas4.Model.BookCategory;
import com.adi.tugas4.Repository.BookCategoryRepository;
import com.adi.tugas4.Service.BookCategoryService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/bookCategory")
public class BookCategoryController {

    @Autowired
    BookCategoryService bookCategoryService;

    @Autowired
    BookCategoryRepository bookCategoryRepository;


    @GetMapping("")
    List<BookCategory> getAllBookCategory(){
        return bookCategoryService.findAll();
    }

    @PostMapping("/insert")
    public Map<String,Object>addNewBookCat(@RequestBody BookCategory bodycatg){
        Map<String,Object> result = new HashMap<>();
        if (bookCategoryService.saveBookCatg(bodycatg)){
            result.put("success",true);
            result.put("message","bookcategory berhasil ditambahkan");
        }else {
            result.put("success",false);
            result.put("message","bookcategory gagal ditambahkan");
        }
        return result;
    }

    @PutMapping("/update")
    public Map<String,Object> updateBookCat(@RequestBody BookCategory body){
        Map<String,Object> result = new HashMap<>();
        if (bookCategoryService.UpdateBookCatg(body)){
            result.put("success",true);
            result.put("message","Update Bookcatg berhasil diubah");
        }else {
            result.put("success",false);
            result.put("message","Update BookCeg Data gagal diubah");
        }
        return result;
    }

    @DeleteMapping("/delete")
    Map<String,Object>deleteBookCat(@RequestParam int id){
        Map<String,Object> result = new HashMap<>();
        if (bookCategoryService.DeleteBookCatg(id)) {
            result.put("success", true);
            result.put("message", "Data berhasil dihapus");
        } else{
            result.put("success", false);
            result.put("message", "Data gagal dihapus");
        } return result;
    }

    @GetMapping("/page")
    public ResponseEntity<Map<String ,Object>> getAllBookCat(
            @RequestParam(required = false) String search,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "3") int size )
    { return bookCategoryService.getAllBookCategoryPage(search,page,size);


    }

}
