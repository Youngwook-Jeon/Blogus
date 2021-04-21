package com.young.blogus.user;

import com.young.blogus.exception.domain.EmailExistException;
import com.young.blogus.exception.domain.NameExistException;
import com.young.blogus.user.form.SignupForm;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static com.young.blogus.constant.UserConstant.*;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;

    @Override
    public User signup(SignupForm signupForm) throws NameExistException, EmailExistException {
        validateNewNameAndEmail(signupForm.getName(), signupForm.getEmail());
        signupForm.setPassword(encodePassword(signupForm.getPassword()));
        User newUser = modelMapper.map(signupForm, User.class);
        newUser.completeSignup();
        return userRepository.save(newUser);
    }

    private void validateNewNameAndEmail(String name, String email)
            throws NameExistException, EmailExistException {
        User userByName = userRepository.findByName(name);
        if (userByName != null) {
            throw new NameExistException(NAME_ALREADY_EXISTS);
        }
        User userByEmail = userRepository.findByEmail(email);
        if (userByEmail != null) {
            throw new EmailExistException(EMAIL_ALREADY_EXISTS);
        }
    }

    private String encodePassword(String password) {
        return passwordEncoder.encode(password);
    }

}
