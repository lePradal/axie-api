package com.prads.axie.controller;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.prads.axie.config.security.TokenService;
import com.prads.axie.dto.LoginRequestDTO;
import com.prads.axie.dto.TokenDTO;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

  @Autowired
  private AuthenticationManager authManager;

  @Autowired
  private TokenService tokenService;

  @PostMapping
  public ResponseEntity authenticate(@RequestBody @Valid LoginRequestDTO loginRequestDTO) {
    UsernamePasswordAuthenticationToken loginData = loginRequestDTO.convert();
    try {
      Authentication authentication = authManager.authenticate(loginData);
      String token = tokenService.generateToken(authentication);

      return ResponseEntity.ok(new TokenDTO(token, "Bearer"));
    } catch (AuthenticationException exception) {
      return ResponseEntity.badRequest().build();
    }
  }
}
