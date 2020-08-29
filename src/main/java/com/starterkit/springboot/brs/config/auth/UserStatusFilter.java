package com.starterkit.springboot.brs.config.auth;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.starterkit.springboot.brs.config.controller.MainExceptionHandler;
import com.starterkit.springboot.brs.exception.InactiveUserException;
import com.starterkit.springboot.brs.exception.InvalidUserException;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.google.gson.Gson;

public class UserStatusFilter extends OncePerRequestFilter {

    private final Gson gson = new Gson();

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {

        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (authentication instanceof OAuth2Authentication) {
                OAuth2AuthenticationDetails details = (OAuth2AuthenticationDetails) authentication.getDetails();
                TokenInfo tokenInfo = (TokenInfo) details.getDecodedDetails();

                if (!tokenInfo.isValidated()) {
                    throw new InvalidUserException("User needs to be validated by administrators");
                }

                if (!tokenInfo.isEnabled()) {
                    throw new InactiveUserException("Inactive user");
                }
            }
        } catch (InactiveUserException | InvalidUserException ex) {
            prepareErrorResponse(ex, httpServletResponse);
            return;
        }

        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }

    private void prepareErrorResponse(Exception ex, HttpServletResponse response) throws IOException {
        response.setContentType(MimeTypeUtils.APPLICATION_JSON_VALUE);
        response.setStatus(HttpStatus.FORBIDDEN.value());

        try (OutputStream os = response.getOutputStream()) {
            MainExceptionHandler.Error error = new MainExceptionHandler.Error()
                    .message(ex.getMessage())
                    .exception(ex.getClass().getCanonicalName());

            os.write(gson.toJson(error).getBytes(StandardCharsets.UTF_8));
        }
    }
}
