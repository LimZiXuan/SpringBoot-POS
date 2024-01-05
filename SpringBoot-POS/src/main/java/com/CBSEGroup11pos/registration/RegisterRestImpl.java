package com.CBSEGroup11pos.registration;

import com.CBSEGroup11pos.auth.config.securityModel.ResetPasswordModel;
import com.CBSEGroup11pos.entity.User;
import com.CBSEGroup11pos.event.RegistrationCompleteEvent;
import com.CBSEGroup11pos.util.EmailUtil;
import com.CBSEGroup11pos.util.authUtil.RegistrationRequest;
import com.CBSEGroup11pos.util.authUtil.TokenUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class RegisterRestImpl implements RegisterRest{
    private final RegisterService registrationService;
    private final TokenUtil tokenUtil;
    private final ApplicationEventPublisher publisher;
    private final EmailUtil emailUtil;

    @Override
    public ResponseEntity<String> registerUser(RegistrationRequest registrationRequest, final HttpServletRequest request) {
        try {
            User user = registrationService.registerUser(registrationRequest).getBody();
            // publish registration event
            publisher.publishEvent(new RegistrationCompleteEvent(user, emailUtil.applicationUrl(request)));
            return new ResponseEntity<>("Registration Success! Please check your email to verify your email.", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>("Something Went Wrong!", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<String> resendVerificationCode(String oldToken, final HttpServletRequest request) {
        try {
            return registrationService.resendVerificationCode(oldToken, request);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>("Something Went Wrong!", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<String> resetPassword(ResetPasswordModel resetPasswordModel, final HttpServletRequest request) {
        try {
            return registrationService.resetPassword(resetPasswordModel, request);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>("Something Weng Wrong!", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<String> savePassword(String token, ResetPasswordModel resetPasswordModel) {
        try {
            return registrationService.savePassword(token, resetPasswordModel);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>("Something Went Wrong!", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<String> verifyEmail(String token) {
        try {
            return registrationService.verifyEmail(token);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>("Something Went Wrong!", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
