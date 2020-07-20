package com.adi.tugas4.Service;

import com.adi.tugas4.Model.Address;
import com.adi.tugas4.Model.User;
import com.adi.tugas4.Repository.AddressRepository;
import com.adi.tugas4.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    AddressRepository addressRepository;

    @Autowired
    AddressService addressService;



    public List<User> getAllUser(Integer pageNo, String sortKey){
        int noOfRecord = 4;//mengambil
        Pageable page = PageRequest.of(pageNo, noOfRecord, Sort.by(sortKey));
        Page <User> pagedResult = userRepository.findAll(page);
        return pagedResult.getContent();
    }

    public List<User> getAllUserByAddress(String search, String type) {
        switch (type) {
            case "city":
                return userRepository.findByAddress_CityContaining(search);
            case "province":
                return userRepository.findByAddress_ProvinceContaining(search);
            case "country":
                return userRepository.findByAddress_CountryContaining(search);
            default:
                return null;
        }
    }

    public Map<String, Object> insertNewUser(User body) {
        User result;
        Address address = body.getAddress();
        Map<String, Object> resultMap = new HashMap<>();
        try {
            body.setAddress(null);
            result = userRepository.save(body);
            //insert address
            if (address != null) {
                address.setUser(result);
                addressRepository.save(address);
            }
            resultMap.put("success", true);
            resultMap.put("message", "--- DATA BERHASIL DITAMBAHKAN ---");
        } catch (Exception e) {
            resultMap.put("success", false);
            resultMap.put("message", "--- GAGAL MENAMBAH DATA ---" + e.getMessage());
        }
        return resultMap;
    }

    public Page<User> getByPage(String search, Pageable pageable) {
        return userRepository.findByUsernameContaining(search, pageable);
    }

    public boolean updateData(User data){
        User hasil = userRepository.findById(data.getId());
        if (hasil !=null){
            try {
                userRepository.save(data);
                return true;
            } catch (Exception E){
                return false;
            }
        } else{
            return false;
        }
    }


    public Map deleteByUserId(int userId) {
        User result = userRepository.findById(userId);
        Map<String, Object> resultMap = new HashMap<>();
        if (result != null) {
            try {
                userRepository.deleteById(userId);
                resultMap.put("success", true);
                resultMap.put("message", "--= DATA BERHASIL DIHAPUS =--");
            } catch (Exception e) {
                resultMap.put("success", false);
                resultMap.put("record", "--= DATA GAGAL DIHAPUS =--" + e.getMessage());
            }
        } else {
            resultMap.put("success", false);
            resultMap.put("record", "address gagal terhapus");
        }
        return resultMap;
    }

}
