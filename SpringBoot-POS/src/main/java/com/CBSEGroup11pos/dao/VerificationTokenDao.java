package com.CBSEGroup11pos.dao;

import com.CBSEGroup11pos.entity.VerificationToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VerificationTokenDao extends JpaRepository<VerificationToken, Long> {

    VerificationToken findByToken(String token);
}
