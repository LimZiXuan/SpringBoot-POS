package com.CBSEGroup11pos.dao;

import com.CBSEGroup11pos.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserDao extends JpaRepository<User, Long> {

    Optional<User> findByEmail(@Param("email") String email);
}
