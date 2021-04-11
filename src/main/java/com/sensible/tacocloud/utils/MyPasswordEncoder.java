package com.sensible.tacocloud.utils;

import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * 自定义的密码加密器
 */
public class MyPasswordEncoder implements PasswordEncoder {
    @Override
    public String encode(CharSequence rawPassword) {
        return rawPassword.toString();
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        return encodedPassword.equals(rawPassword);
    }
}
