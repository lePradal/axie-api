package com.prads.axie.config.security;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;
import com.prads.axie.models.User;
import com.prads.axie.repository.UserRepository;

public class AuthenticationTokenFilter extends OncePerRequestFilter {

  private TokenService tokenService;
  private UserRepository userRepository;

  public AuthenticationTokenFilter(TokenService tokenService, UserRepository userRepository) {
    this.tokenService = tokenService;
    this.userRepository = userRepository;
  }

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
      FilterChain filterChain) throws ServletException, IOException {
    String token = recoverToken(request);

    boolean valid = tokenService.isValid(token);

    if (valid) {
      authenticateClient(token);
    }

    filterChain.doFilter(request, response);
  }

  private void authenticateClient(String token) {
    Long userId = tokenService.getUserId(token);
    User user = userRepository.findById(userId).get();
    UsernamePasswordAuthenticationToken authentication =
        new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());

    SecurityContextHolder.getContext().setAuthentication(authentication);
  }

  private String recoverToken(HttpServletRequest request) {
    String authorizationHeader = request.getHeader("Authorization");

    if (authorizationHeader == null || authorizationHeader.isEmpty()
        || !authorizationHeader.startsWith("Bearer ")) {
      return null;
    }

    return authorizationHeader.substring(7, authorizationHeader.length());
  }
}
