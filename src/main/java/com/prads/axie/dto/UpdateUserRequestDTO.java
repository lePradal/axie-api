package com.prads.axie.dto;

import io.swagger.annotations.ApiModelProperty;

public class UpdateUserRequestDTO {

  @ApiModelProperty(position = 0, example = "Renamed User")
  private String name;

  @ApiModelProperty(position = 1, example = "changed.email@email.com")
  private String email;

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

}
