package com.adi.tugas4.Repository;

import com.adi.tugas4.Model.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.transaction.TransactionScoped;
import java.util.List;

@Repository
@Transactional
public interface AddressRepository extends JpaRepository<Address,Integer> {
    Address findById(int id);
    List<Address> findByCityContaining(String search);
    List<Address> findByProvinceContaining(String search);
    List<Address> findByCountryContaining(String search);
    @Modifying
    @Query(value = "DELETE FROM address WHERE user_id = :id ", nativeQuery = true)
    Integer deleteById(@Param("id") int id);

}
