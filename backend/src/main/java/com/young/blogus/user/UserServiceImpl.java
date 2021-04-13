package com.young.blogus.user;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static java.time.LocalDateTime.*;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User signup(String name, String password, String email) {
        validateNewNameAndEmail(name, email);
        String username = generateUsername();
        String profile = "";
        User user = User.builder()
                .name(name)
                .email(email)
                .password(encodePassword(password))
                .username(username)
                .joinedAt(now())
                .profile(profile)
                .role(0)
                .build();
        return userRepository.save(null);
    }

    private void validateNewNameAndEmail(String name, String email) {
    }

    private String encodePassword(String password) {
        return passwordEncoder.encode(password);
    }

    private String generateUsername() {
        return RandomStringUtils.randomAlphanumeric(10);
    }
}
