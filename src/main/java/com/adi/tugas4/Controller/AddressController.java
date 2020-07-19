//package com.adi.tugas4.Controller;
//
//import com.adi.tugas4.Model.Address;
//import com.adi.tugas4.Service.AddressService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//@RestController
//@RequestMapping("api/address")
//public class AddressController {
//    @Autowired
//    AddressService addressService;
//
//    @GetMapping("")
//    List<Address> getAllAddress(){
//        return addressService.getAllAddress();
//    }
//
//    public  Address getAddressByAddress(@RequestParam String address){
//        Address result = addressService.findByAddress(address);
//        return  result;
//    }
//
//    @PostMapping("/insert")
//    public Address insertAddress(@RequestBody Address body){
//        return addressService.insertAddress(body);
//    }
//
//    @PutMapping("/update")
//    public Map<String, Object> updateAddress(@RequestBody Address body) {
//        System.out.println("body : " + body.toString());
//        Map<String, Object> result = new HashMap<>();
//
//        if (addressService.updateData(body)) {
//            result.put("success", true);
//            result.put("message", "DATA  BERHASIL DIUBAH");
//        } else {
//            result.put("success", false);
//            result.put("message", "DATA HGAGAL DIUBAH");
//        }
//        return result;
//    }
//
//    @DeleteMapping("/detele")
//    public Map<String, Object> hapusData(@RequestBody Address body) {
//        System.out.println("body : " + body.toString());
//        Map<String, Object> result = new HashMap<>();
//
//        if (addressService.updateData(body)) {
//            result.put("success", true);
//            result.put("message", "DATA  BERHASIL DIHAPUS");
//        } else {
//            result.put("success", false);
//            result.put("message", "DATA HGAGAL DIHAPUS");
//        }
//        return result;
//    }
//}
