package com.elliot.security.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author elliot
 * @since 2020-09-23
 */
public class SecurityUser implements UserDetails {

  private User user;

  private List<String> permissionListStr;

  public SecurityUser(User user, List<String> permissionListStr) {
    this.user = user;
    this.permissionListStr = permissionListStr;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    List<GrantedAuthority> authorityList = new ArrayList<>();
    permissionListStr.forEach(permission -> {
      SimpleGrantedAuthority authority = new SimpleGrantedAuthority(permission);
      authorityList.add(authority);
    });
    return authorityList;
  }

  @Override
  public String getPassword() {
    return user.getPassword();
  }

  @Override
  public String getUsername() {
    return user.getUsername();
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public List<String> getPermissionListStr() {
    return permissionListStr;
  }

  public void setPermissionListStr(List<String> permissionListStr) {
    this.permissionListStr = permissionListStr;
  }
}
