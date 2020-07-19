package com.adi.tugas4.Service;

import com.adi.tugas4.Model.Address;
import com.adi.tugas4.Model.User;
import com.adi.tugas4.Repository.AddressRepository;
import com.adi.tugas4.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.HashMap;
import java.util.List;

@Service
public class AddressService {
    @Autowired
    AddressRepository addressRepository;

    @Autowired
    UserRepository userRepository;

    public List<Address> getAllAddress() {
        return addressRepository.findAll();
    }

    public Map insertAddress(Address address, int id) {
        User user = userRepository.findById(id);
        Map<String, Object> mapResult = new HashMap<>();

        if (user != null) {
            address.setUser(user);
            try {
                addressRepository.save(address);
                mapResult.put("success", true);
                mapResult.put("message", "berhasil insert address");
            } catch (Exception e) {
                mapResult.put("success", false);
                mapResult.put("message", "gagal insert address: " + e.getMessage());
            }
        }
        return mapResult;
    }

    public Map updateUser(Address body, int id) {
        Address result = addressRepository.findById(id);
        Map<String, Object> resultMap = new HashMap<>();

        if (result != null) {
            try {
                addressRepository.save(body);
                resultMap.put("success", true);
                resultMap.put("message", "berhasil edit address");
            } catch (Exception e) {
                resultMap.put("success", false);
                resultMap.put("message", "gagal edit address: " + e.getMessage());
            }
        } else {
            resultMap.put("success", false);
            resultMap.put("message", "gagal edit address");
        }

        return resultMap;
    }

    public Map<String, Object> getAddressById(int userId) {
        Address result = addressRepository.findById(userId);
        Map<String, Object> resultMap = new HashMap<>();
        if (result != null) {
            resultMap.put("success", true);
            resultMap.put("record", result);
        } else {
            resultMap.put("success", false);
            resultMap.put("message", "data address tidak di temukan");
        }
        return resultMap;
    }

    public Map deleteByUserId(int userId) {
        Address result = addressRepository.findById(userId);
        Map<String, Object> resultMap = new HashMap<>();
        if (result != null) {
            try {
                addressRepository.deleteById(userId);
                resultMap.put("success", true);
                resultMap.put("message", "address berhasil terhapus");
            } catch (Exception e) {
                resultMap.put("success", false);
                resultMap.put("record", "address gagal terhapus: " + e.getMessage());
            }
        } else {
            resultMap.put("success", false);
            resultMap.put("record", "address gagal terhapus");
        }
        return resultMap;
    }

    //get by type
    public List<Address> getAddressByType(String search, String type) {

        switch (type) {
            case "city":
                return addressRepository.findByCityContaining(search);
            case "province":
                return addressRepository.findByProvinceContaining(search);
            case "country":
                return addressRepository.findByCountryContaining(search);
            default:
                return null;
        }
    }


}
