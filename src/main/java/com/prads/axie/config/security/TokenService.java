package com.prads.axie.config.security;

import java.util.Date;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import com.prads.axie.exception.AuthenticationException;
import com.prads.axie.models.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class TokenService {

  @Value("${jwt.expiration}")
  private String expiration;

  @Value("${jwt.secret}")
  private String secret;

  public String generateToken(Authentication authentication) {
    User user = (User) authentication.getPrincipal();
    Date now = new Date();
    Date expirationDate = new Date(now.getTime() + Long.parseLong(expiration));

    return Jwts.builder().setIssuer("Api Prads Axie").setSubject(user.getUserId().toString())
        .setIssuedAt(now).setExpiration(expirationDate).signWith(SignatureAlgorithm.HS256, secret)
        .compact();
  }

  public boolean isValid(String token) {
    try {
      Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
      return true;
    } catch (Exception ex) {
      return false;
    }
  }

  public void validateUserIdMatchByTokenAndByPathParam(String token, Long id) {
    Long userIdByToken = this.getUserId(this.recoverToken(token));

    if (!id.equals(userIdByToken)) {
      throw new AuthenticationException("Usuário não autorizado.");
    }
  }

  public Long getUserId(String token) {
    Claims claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
    return Long.parseLong(claims.getSubject());
  }

  private String recoverToken(String bearerToken) {

    if (bearerToken == null || bearerToken.isEmpty() || !bearerToken.startsWith("Bearer ")) {
      return null;
    }

    return bearerToken.substring(7, bearerToken.length());
  }
}
