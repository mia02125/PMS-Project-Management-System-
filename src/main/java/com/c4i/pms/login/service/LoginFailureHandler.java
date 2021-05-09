package com.c4i.pms.login.service;

import java.io.IOException;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import lombok.Getter;

@Getter
public class LoginFailureHandler implements AuthenticationFailureHandler {
    private static final Logger log = LoggerFactory.getLogger(LoginFailureHandler.class);

    private final String loginIdName;     // login form id tag name
    private final String loginPwdName;    // login form password tag name
    private final String defaultFailureUrl;

    @Autowired
    private MessageSource messageSource;

    public LoginFailureHandler(String loginIdName, String loginPwdName, String defaultTargetUrl) {
        this.loginIdName = loginIdName;
        this.loginPwdName = loginPwdName;
        this.defaultFailureUrl = defaultTargetUrl;
    }

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        String loginId = request.getParameter(loginIdName);
        String loginPw = request.getParameter(loginPwdName);
        String errorMsg = "";
        String errorTarget = "";

        if(exception instanceof InternalAuthenticationServiceException) {
            errorMsg = messageSource.getMessage("error.login.notFoundId", null, Locale.getDefault());
            errorTarget = this.loginIdName;
        }else if(exception instanceof BadCredentialsException) {
            errorMsg = messageSource.getMessage("error.login.notMatchPassword", null, Locale.getDefault());
            errorTarget = this.loginPwdName;
        }else if(exception instanceof DisabledException) {     //
            errorMsg = messageSource.getMessage("error.login.disabledAccount", null, Locale.getDefault());
            errorTarget = this.loginIdName;
        }else if(exception instanceof Exception){
            errorMsg = messageSource.getMessage("error.login.etc", null, Locale.getDefault());
        }

        request.setAttribute("ERROR_TARGET", errorTarget);      // 화면상 focus를 줄 부분
        request.setAttribute("ERROR_MSG", errorMsg);            // 에러메시지
        request.setAttribute(loginIdName, loginId);                   // login form id value
        request.setAttribute(loginPwdName, loginPw);                  // login form pwd value

        request.getRequestDispatcher(this.getDefaultFailureUrl()).forward(request, response);
    }
}
