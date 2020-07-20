package com.adi.tugas4.Repository;

import com.adi.tugas4.Model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Repository
public interface Pagination extends JpaRepository<User, String> {

    List<User> findByUsername(User username);
}

