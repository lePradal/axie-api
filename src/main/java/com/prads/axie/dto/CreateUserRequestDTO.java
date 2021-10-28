package com.prads.axie.dto;

import org.springframework.beans.BeanUtils;
import com.prads.axie.models.User;
import com.prads.axie.utils.ModelUtils;
import io.swagger.annotations.ApiModelProperty;

public class CreateUserRequestDTO {

  @ApiModelProperty(position = 0, example = "Some User")
  private String name;

  @ApiModelProperty(position = 1, example = "some.email@email.com")
  private String email;

  @ApiModelProperty(position = 2, example = "abcd1234")
  private String password;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

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

  public User toUser() {
    User user = new User();

    BeanUtils.copyProperties(this, user, ModelUtils.getNullPropertyNames(this));

    return user;
  }

}
