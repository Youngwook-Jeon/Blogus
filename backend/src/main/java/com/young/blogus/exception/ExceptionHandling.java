package com.young.blogus.exception;

import com.young.blogus.exception.domain.EmailExistException;
import com.young.blogus.exception.domain.NameExistException;
import com.young.blogus.shared.HttpResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.http.HttpStatus.*;

@Slf4j
@RestControllerAdvice
public class ExceptionHandling implements ErrorController {

    public static final String NO_MAPPINGS_FOUND = "이 URL에 대응하는 매핑이 존재하지 않습니다.";
    public static final String INVALID_USER_INFO = "유효하지 않은 유저 정보입니다.";
    public static final String ERROR_PATH = "/error";

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<HttpResponse> methodArgumentNotValidException(MethodArgumentNotValidException exception) {
        BindingResult result = exception.getBindingResult();
        Map<String, String> validationErrors = new HashMap<>();
        for (FieldError fieldError: result.getFieldErrors()) {
            validationErrors.put(fieldError.getField(), fieldError.getDefaultMessage());
        }
        HttpResponse response = getHttpResponse(BAD_REQUEST, INVALID_USER_INFO);
        response.setValidationErrors(validationErrors);
        return createHttpResponse(response);
    }

    @ExceptionHandler(EmailExistException.class)
    public ResponseEntity<HttpResponse> emailExistException(EmailExistException exception) {
        return createHttpResponse(BAD_REQUEST, exception.getMessage());
    }

    @ExceptionHandler(NameExistException.class)
    public ResponseEntity<HttpResponse> nameExistException(NameExistException exception) {
        return createHttpResponse(BAD_REQUEST, exception.getMessage());
    }


    private ResponseEntity<HttpResponse> createHttpResponse(HttpResponse response) {
        return new ResponseEntity<>(response, response.getHttpStatus());
    }

    private ResponseEntity<HttpResponse> createHttpResponse(HttpStatus httpStatus, String message) {
        return new ResponseEntity<>(
                getHttpResponse(httpStatus, message),
                httpStatus
        );
    }

    private HttpResponse getHttpResponse(HttpStatus httpStatus, String message) {
        return new HttpResponse(
                httpStatus.value(),
                httpStatus,
                httpStatus.getReasonPhrase().toUpperCase(),
                message
        );
    }

    @RequestMapping(ERROR_PATH)
    public ResponseEntity<HttpResponse> notFound404() {
        return createHttpResponse(NOT_FOUND, NO_MAPPINGS_FOUND);
    }

    @Override
    public String getErrorPath() {
        return ERROR_PATH;
    }
}
