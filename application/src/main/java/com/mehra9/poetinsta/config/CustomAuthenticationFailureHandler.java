package com.mehra9.poetinsta.config;

import java.io.IOException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import javax.naming.AuthenticationException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import com.fasterxml.jackson.databind.ObjectMapper;

public class CustomAuthenticationFailureHandler 
implements AuthenticationFailureHandler {

  private ObjectMapper objectMapper = new ObjectMapper();

@Override
public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
		org.springframework.security.core.AuthenticationException exception) throws IOException, ServletException {
	// TODO Auto-generated method stub
    response.setStatus(HttpStatus.UNAUTHORIZED.value());
    response.setContentType("application/json");
    Map<String, Object> data = new HashMap<>();
    data.put(
      "timestamp", 
      Calendar.getInstance().getTime());
    data.put(
      "exception", 
      "No user with provided email found");

    response.getOutputStream()
      .println(objectMapper.writeValueAsString(data));
	
}
}