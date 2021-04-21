package com.young.blogus.user;

import com.young.blogus.exception.domain.EmailExistException;
import com.young.blogus.exception.domain.NameExistException;
import com.young.blogus.user.form.SignupForm;

public interface UserService {

    User signup(SignupForm signupForm) throws NameExistException, EmailExistException;
}
