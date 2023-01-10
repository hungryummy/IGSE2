package com.igse2.service.impl;

import com.igse2.entity.AdminUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class UserDetailsServiceImpl {
    public UserDetails loadUserByUsername(String username) {
        return new AdminUser("YM");
    }
}
