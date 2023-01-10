package com.igse2.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.LogoutFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private AuthFilter authFilter;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.antMatcher("/api/**")
                .addFilterBefore(authFilter, LogoutFilter.class)
                .authorizeRequests()
                .antMatchers("/css/**","/js/**","/images/**","/lib/**","/login").permitAll()
                .anyRequest().authenticated()
                .and()
                //没有权限会自动跳转到默认登录界面
                .formLogin()
                .loginPage("/login").permitAll()
                .defaultSuccessUrl("/index");
    }




}