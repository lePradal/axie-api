package com.prads.axie.dto;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import io.swagger.annotations.ApiModelProperty;

public class LoginRequestDTO {

  @ApiModelProperty(position = 0, example = "some.email@email.com")
  private String email;

  @ApiModelProperty(position = 1, example = "abcd1234")
  private String password;

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public UsernamePasswordAuthenticationToken convert() {
    return new UsernamePasswordAuthenticationToken(email, password);
  }
}
