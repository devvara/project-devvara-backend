package com.devvara.devvara.security.jwt.exception;

import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

@Slf4j
@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        String exception = (String) request.getAttribute("exception");
        log.error("Commence Get Exception : {}", exception);

        if (exception == null) {
            log.error("entry point >> exception is null");
            setResponse(response, JwtExceptionCode.NOT_FOUND_TOKEN);
        } else {
            JwtExceptionCode jwtException;
            if (exception.equals(JwtExceptionCode.INVALID_TOKEN.getCode())) {
                jwtException = JwtExceptionCode.INVALID_TOKEN;
                log.error("entry point >> invalid token");
            } else if (exception.equals(JwtExceptionCode.EXPIRED_TOKEN.getCode())) {
                jwtException = JwtExceptionCode.EXPIRED_TOKEN;
                log.error("entry point >> expired token");
            } else if (exception.equals(JwtExceptionCode.NOT_FOUND_TOKEN.getCode())) {
                jwtException = JwtExceptionCode.NOT_FOUND_TOKEN;
                log.error("entry point >> not found token");
            } else {
                jwtException = JwtExceptionCode.UNKNOWN_ERROR;
            }
            setResponse(response, jwtException);
        }
    }

    private void setResponse(HttpServletResponse response, JwtExceptionCode exceptionCode) throws IOException {
        response.setContentType("application/json;charset=UTF-8");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

        HashMap<String, Object> errorInfo = new HashMap<>();
        errorInfo.put("message", exceptionCode.getMessage());
        errorInfo.put("code", exceptionCode.getCode());
        Gson gson = new Gson();
        String responseJson = gson.toJson(errorInfo);
        response.getWriter().print(responseJson);
    }
}