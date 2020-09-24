package com.elliot.security.security;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;

/**
 * @author elliot
 * @since 2020-09-24
 */
@Component
public class PasswordEncodeMatch implements PasswordEncoder {
  @Override
  public String encode(CharSequence charSequence) {
    return DigestUtils.md5DigestAsHex(charSequence.toString().getBytes());
  }

  @Override
  public boolean matches(CharSequence charSequence, String encodePassword) {
    boolean isSuccess = encodePassword.equals(DigestUtils.md5DigestAsHex(charSequence.toString().getBytes()));
    if (!isSuccess) {
      throw new BadCredentialsException("账号或密码错误");
    }
    return isSuccess;
  }
}
