package com.starterkit.springboot.brs.config.auth;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityServerConfig extends WebSecurityConfigurerAdapter {


    public static final String OAUTH_TOKEN_URL = "/oauth/token";
    public static final String FAKE_PICTURES_URL = "/fake-pictures/*";

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers(HttpMethod.OPTIONS, OAUTH_TOKEN_URL).permitAll()
                .antMatchers(HttpMethod.GET, FAKE_PICTURES_URL).permitAll();
    }
}
