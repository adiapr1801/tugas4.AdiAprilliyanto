package com.adi.tugas4.Controller;

import com.adi.tugas4.Model.User;
import com.adi.tugas4.Repository.UserRepository;
import com.adi.tugas4.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserService userService;
    @GetMapping("")
    List<User>getAllUser(){
    return userService.getAllUser();
    }


    @PostMapping("/insert")
    public Map<String, Object> insertUser(@RequestBody User user){
        return  userService.insertNewUser(user);
    }

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
}
