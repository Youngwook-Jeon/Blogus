package com.young.blogus.main;

import com.young.blogus.exception.domain.EmailExistException;
import com.young.blogus.exception.domain.NameExistException;
import com.young.blogus.shared.HttpResponse;
import com.young.blogus.user.User;
import com.young.blogus.user.UserService;
import com.young.blogus.user.form.SignupForm;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class MainController {

    public static final String SIGNUP_SUCCESS = "사용자 등록에 성공했습니다. 로그인을 하세요.";

    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<HttpResponse> signup(@Valid @RequestBody SignupForm signupForm)
            throws NameExistException, EmailExistException {
        userService.signup(signupForm);
        return response(OK, SIGNUP_SUCCESS);
    }

    private ResponseEntity<HttpResponse> response(HttpStatus httpStatus, String message) {
        return new ResponseEntity<>(
                new HttpResponse(httpStatus.value(), httpStatus,
                        httpStatus.getReasonPhrase().toUpperCase(), message),
                httpStatus
        );
    }
}
