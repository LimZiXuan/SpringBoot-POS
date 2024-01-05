package com.CBSEGroup11pos.util.authUtil;

import com.CBSEGroup11pos.dao.PasswordResetTokenDao;
import com.CBSEGroup11pos.dao.UserDao;
import com.CBSEGroup11pos.dao.VerificationTokenDao;
import com.CBSEGroup11pos.entity.PasswordResetToken;
import com.CBSEGroup11pos.entity.User;
import com.CBSEGroup11pos.entity.VerificationToken;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class TokenUtil {
    private final UserDao userDao;

    private final PasswordResetTokenDao passwordResetTokenDao;

    private final VerificationTokenDao tokenDao;

    public void saveUserVerificationToken(User user, String verificationToken) {
        VerificationToken token = new VerificationToken(verificationToken, user);
        tokenDao.save(token);
    }

    public VerificationToken generateNewVerificationToken(String oldToken) {
        VerificationToken verificationToken = tokenDao.findByToken(oldToken);
        log.info("Verification Token: {}", verificationToken);
        VerificationToken newVerificationToken = new VerificationToken();
        verificationToken.setToken(UUID.randomUUID().toString());
        verificationToken.setExpirationTime(newVerificationToken.getTokenExpirationTime());
        return tokenDao.save(verificationToken);
    }

    public String validateToken(String token) {
        log.info("Inside validateToken");
        VerificationToken verificationToken = tokenDao.findByToken(token);
        if (token == null) {
            return "Invalid verification token";
        }
        User user = verificationToken.getUser();
        Calendar calendar = Calendar.getInstance();
        if ((verificationToken.getExpirationTime().getTime() - calendar.getTime().getTime()) <= 0) {
            return "Token already expired.";
        }
        user.setEnabled(true);
        userDao.save(user);
        return "Valid";
    }

    public String validatePasswordResetToken(String token) {
        log.info("Inside validatePasswordResetToken");
        PasswordResetToken passwordResetToken = passwordResetTokenDao.findByToken(token);
        if (token == null) {
            return "Invalid verification token";
        }
        Calendar calendar = Calendar.getInstance();
        if ((passwordResetToken.getExpirationTime().getTime() - calendar.getTime().getTime()) <= 0) {
            return "Token already expired.";
        }
        return "Valid";
    }
}
