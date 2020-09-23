package com.elliot.acl.service.impl;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

/**
 * @author elliot
 * @since 2020-09-20
 */
@Component("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {
  @Override
  public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
    return null;
  }
}
