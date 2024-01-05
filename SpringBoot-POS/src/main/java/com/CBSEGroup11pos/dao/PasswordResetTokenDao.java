package com.CBSEGroup11pos.dao;

import com.CBSEGroup11pos.entity.PasswordResetToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PasswordResetTokenDao extends JpaRepository<PasswordResetToken, Long> {

    PasswordResetToken findByToken(String token);
}
