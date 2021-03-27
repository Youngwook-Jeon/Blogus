package com.young.blogus;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Playground {

    @Test
    void uuid() {
        String uuid = UUID.randomUUID().toString();
        System.out.println(uuid);
    }

    @Test
    void check_email_regex() {
        List<String> emails = new ArrayList();
        emails.add("user@domain.com");
        emails.add("user@domain.co.in");
        emails.add("user.name@domain.com");
        emails.add("user?name@domain.co.in");
        emails.add("user'name@domain.co.in");

        emails.add("@naver.com");

        String regex = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";

        Pattern pattern = Pattern.compile(regex);

        for(String email : emails){
            Matcher matcher = pattern.matcher(email);
            System.out.println(email +" : "+ matcher.matches());
        }
    }
}
