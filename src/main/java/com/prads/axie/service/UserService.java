package com.prads.axie.service;

import com.prads.axie.dto.CreateUserRequestDTO;
import com.prads.axie.dto.UpdatePasswordRequestDTO;
import com.prads.axie.dto.UpdateUserRequestDTO;
import com.prads.axie.models.User;

public interface UserService {
  User createUser(CreateUserRequestDTO user);

  User getUser(Long id);

  void deleteUser(Long id);

  User updateUser(UpdateUserRequestDTO userForm, Long id);

  void updateUserPassword(UpdatePasswordRequestDTO updateUserRequest, Long id);
}
