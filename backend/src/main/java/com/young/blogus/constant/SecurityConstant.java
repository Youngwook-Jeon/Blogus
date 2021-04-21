package com.young.blogus.constant;

public class SecurityConstant {
    public static final long EXPIRATION_TIME = 432_000_000; // 5 days expressed in milliseconds
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String JWT_TOKEN_HEADER = "token";
    public static final String TOKEN_CANNOT_BE_VERIFIED = "토큰이 검증되지 않았습니다.";
    public static final String FORBIDDEN_MESSAGE = "이 페이지에 접근하려면 로그인을 하셔야 합니다.";
    public static final String ACCESS_DENIED_MESSAGE = "이 페이지에 접근할 권한이 없습니다.";
    public static final String OPTIONS_HTTP_METHOD = "OPTIONS";
    public static final String[] PUBLIC_URLS = { "/signup/**", "/h2-console/**" };
    //public static final String[] PUBLIC_URLS = { "**" };
}
