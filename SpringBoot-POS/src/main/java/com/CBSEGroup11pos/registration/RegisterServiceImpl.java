package com.CBSEGroup11pos.registration;

import com.CBSEGroup11pos.auth.config.securityModel.ResetPasswordModel;
import com.CBSEGroup11pos.dao.UserDao;
import com.CBSEGroup11pos.dao.VerificationTokenDao;
import com.CBSEGroup11pos.entity.User;
import com.CBSEGroup11pos.entity.VerificationToken;
import com.CBSEGroup11pos.exception.UserAlreadyExistsException;
import com.CBSEGroup11pos.service.UserService;
import com.CBSEGroup11pos.util.EmailUtil;
import com.CBSEGroup11pos.util.authUtil.RegistrationRequest;
import com.CBSEGroup11pos.util.authUtil.TokenUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class RegisterServiceImpl implements RegisterService{

    private final UserDao userDao;
    private final PasswordEncoder passwordEncoder;
    private final VerificationTokenDao tokenDao;
    private final HttpServletRequest servletRequest;
    private final EmailUtil emailUtil;
    private final TokenUtil tokenUtil;
    private final UserService userService;

    @Override
    public ResponseEntity<User> registerUser(RegistrationRequest request) {
        try {
            Optional<User> user = this.userDao.findByEmail(request.email());
            if (user.isPresent()) {
                throw new UserAlreadyExistsException("User with email " + request.email() + " already exists");
            }
            User newUser = new User();
            newUser.setName(request.name());
            newUser.setAge(request.age());
            newUser.setGender(request.gender());
            newUser.setAddress(request.address());
            newUser.setPhone(request.phone());
            newUser.setEmail(request.email());
            newUser.setPassword(passwordEncoder.encode(request.password()));
            newUser.setRole(request.role());
            return new ResponseEntity<>(userDao.save(newUser), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(new User(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<String> verifyEmail(String token) {
        String url = emailUtil.applicationUrl(servletRequest) +
                "/register/resend-verification-token?token=" + token;

        VerificationToken verificationToken = tokenDao.findByToken(token);
        if (verificationToken.getUser().isEnabled()) {
            return new ResponseEntity<>("This account has already been verified, please login.", HttpStatus.OK);
        }
        String verificationResult = tokenUtil.validateToken(token);
        if (verificationResult.equalsIgnoreCase("Valid")) {
            log.info("verificationResult {}", verificationResult);
            return new ResponseEntity<>("Email verified successfully. Now you can login to your account.", HttpStatus.OK);
        }
        return new ResponseEntity<>("Invalid verification link, " +
                "<a href=\"" + url + "\"> Get a new verification link. </a>", HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> resendVerificationCode(String oldToken, HttpServletRequest request) {
        VerificationToken verificationToken = tokenUtil.generateNewVerificationToken(oldToken);
        User user = verificationToken.getUser();
        String url = emailUtil.applicationUrl(request);
        resendVerificationTokenEmail(user, url, verificationToken);
        return new ResponseEntity<>("A new verification link has been sent to your email, " +
                "check to activate your registration", HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> resetPassword(ResetPasswordModel resetPasswordModel, HttpServletRequest request) {
        Optional<User> user = userService.findUserByEmail(resetPasswordModel.getEmail());
        String url = "";
        if (user.isEmpty()) {
            return new ResponseEntity<>("User does not exist!", HttpStatus.OK);
        } else {
            String token = UUID.randomUUID().toString();
            userService.createPasswordResetTokenForUser(user, token);
            url = passwordResetTokenMail(emailUtil.applicationUrl(request), token);
            return new ResponseEntity<>(url, HttpStatus.OK);
        }
    }

    @Override
    public ResponseEntity<String> savePassword(String token, ResetPasswordModel resetPasswordModel) {
        String result = tokenUtil.validatePasswordResetToken(token);
        if (!result.equalsIgnoreCase("Valid")) {
            return new ResponseEntity<>("Invalid Reset Password Token", HttpStatus.OK);
        }
        Optional<User> user = userService.getUserByPasswordResetToken(token);
        if (user.isPresent()) {
            userService.resetPassword(user.get(), resetPasswordModel.getNewPassword());
            return new ResponseEntity<>("Password Reset Successfully!", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Invalid Reset Password Token!", HttpStatus.OK);
        }
    }

    private void resendVerificationTokenEmail(User user, String applicationUrl, VerificationToken verificationToken) {
        try {
            String url = applicationUrl + "/register/verifyEmail?token=" + verificationToken.getToken();
            emailUtil.sendVerificationEmail(url, user);
            log.info("Click the link to verify your registration: {}", url);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String passwordResetTokenMail(String applicationUrl, String token) {
        String url = applicationUrl + "/register/savePassword?token=" + token;
        log.info("Click the link to reset your password: {}", url);
        return url;
    }
}
