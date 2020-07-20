package com.adi.tugas4.Controller;

import com.adi.tugas4.Model.User;
import com.adi.tugas4.Repository.Pagination;
import com.adi.tugas4.Repository.UserRepository;
import com.adi.tugas4.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/user")
public class UserController {
    //---------------------------------------------------------------------------------------->>
    @Autowired
    UserRepository userRepository;
    @Autowired
    UserService userService;
    //---------------------------------------------------------------------------------------->>
    private Sort.Direction getSortDirection(String direction) {
        if (direction.equals("asc")) {
            return Sort.Direction.ASC;
        } else if (direction.equals("desc")) {
            return Sort.Direction.DESC;
        }

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
            List<User> tutorials = userRepository.findAll(Sort.by(orders));
            if (tutorials.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(tutorials, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    //---------------------------------------------------------------------------------------->>
    //ambil data dari userService
    @GetMapping("/ambil")
    public List<User> getAllUser(
            @RequestParam(value ="pageNo", defaultValue = "0") Integer pageNo,
            @RequestParam(value = "sortKey", defaultValue = "name") String sortKey)
    {
        return userService.getAllUser(pageNo, sortKey);
    }
    //---------------------------------------------------------------------------------------->>
    @PostMapping("/insert")
    public Map<String, Object> insertUser(@RequestBody User user){
        return  userService.insertNewUser(user);
    }
    //---------------------------------------------------------------------------------------->>
    @PutMapping("/update")
    public Map updateUser(@RequestBody User body) {
        Map<String, Object> resultMap = new HashMap<>();
        if (userService.updateData(body)) {
             resultMap.put("success", true);
                resultMap.put("message", "--= BERHASIL UPDATE DATA =--");
        } else {
            resultMap.put("success", false);
            resultMap.put("message", "--= GAGAL UPDATE DATA 2 =--");
        }
        return resultMap;
    }
    //---------------------------------------------------------------------------------------->>
    @DeleteMapping("/delete")
    public Map deleteByUserId(int id) {
        User result = userRepository.findById(id);
        Map<String, Object> resultMap = new HashMap<>();
        if (result != null) {
            try {
                userRepository.deleteById(id);
                resultMap.put("success", true);
                resultMap.put("message", "--- DATA BERHASIL DIHAPUS ---");
            } catch (Exception e) {
                resultMap.put("success", false);
                resultMap.put("record", "--- DATA GAGAL TERHAPUS --" + e.getMessage());
            }
        } else {
            resultMap.put("success", false);
            resultMap.put("record", "address gagal terhapus");
        }
        return resultMap;
    }
    //---------------------------------------------------------------------------------------->>
}
