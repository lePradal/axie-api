package com.prads.axie.dto;

import io.swagger.annotations.ApiModelProperty;

public class UpdatePasswordRequestDTO {

  @ApiModelProperty(position = 0, example = "abcd1234")
  private String oldPassword;

  @ApiModelProperty(position = 1, example = "abcd12345")
  private String newPassword;

  @ApiModelProperty(position = 2, example = "abcd12345")
  private String checkNewPassword;

  public String getOldPassword() {
    return oldPassword;
  }

  public void setOldPassword(String oldPassword) {
    this.oldPassword = oldPassword;
  }

  public String getNewPassword() {
    return newPassword;
  }

  public void setNewPassword(String newPassword) {
    this.newPassword = newPassword;
  }

  public String getCheckNewPassword() {
    return checkNewPassword;
  }

  public void setCheckNewPassword(String checkNewPassword) {
    this.checkNewPassword = checkNewPassword;
  }

}
