package com.CBSEGroup11pos.serviceImpl;

import com.CBSEGroup11pos.auth.config.securityModel.ResetPasswordModel;
import com.CBSEGroup11pos.dao.PasswordResetTokenDao;
import com.CBSEGroup11pos.dao.UserDao;
import com.CBSEGroup11pos.entity.PasswordResetToken;
import com.CBSEGroup11pos.entity.User;
import com.CBSEGroup11pos.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserDao userDao;
    private final PasswordResetTokenDao passwordResetTokenDao;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Optional<User> findUserByEmail(String email) {
        return userDao.findByEmail(email);
    }

    @Override
    public void createPasswordResetTokenForUser(Optional<User> user, String token) {
        PasswordResetToken passwordResetToken = new PasswordResetToken(token, user.get());
        passwordResetTokenDao.save(passwordResetToken);
    }

    @Override
    public Optional<User> getUserByPasswordResetToken(String token) {
        return Optional.ofNullable(passwordResetTokenDao.findByToken(token).getUser());
    }

    @Override
    public void resetPassword(User user, String newPassword) {
        user.setPassword(newPassword);
        userDao.save(user);
    }

    @Override
    public ResponseEntity<String> changePassword(ResetPasswordModel resetPasswordModel) {
        try {
            Optional<User> user = userDao.findByEmail(resetPasswordModel.getEmail());
            if (user.isPresent()) {
                if (!checkOldPassword(user.get(), resetPasswordModel.getOldPassword())) {
                    return new ResponseEntity<>("Invalid Old Password!", HttpStatus.BAD_REQUEST);
                } else {
                    resetPassword(user.get(), resetPasswordModel.getNewPassword());
                    return new ResponseEntity<>("Password Changed Successfully!", HttpStatus.OK);
                }
            } else {
                return new ResponseEntity<>("User does not exist!", HttpStatus.OK);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>("Something Went Wrong!", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private boolean checkOldPassword(User user, String oldPassword) {
        return passwordEncoder.matches(oldPassword, user.getPassword());
    }
}
