package com.prads.axie.service;

import java.util.Optional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.prads.axie.config.security.AuthenticationService;
import com.prads.axie.dto.CreateUserRequestDTO;
import com.prads.axie.dto.UpdatePasswordRequestDTO;
import com.prads.axie.dto.UpdateUserRequestDTO;
import com.prads.axie.exception.PasswordDoesNotMatchException;
import com.prads.axie.exception.ResourceAlreadyExistsException;
import com.prads.axie.exception.ResourceNotFoundException;
import com.prads.axie.models.User;
import com.prads.axie.repository.UserRepository;
import com.prads.axie.utils.ModelUtils;

@Service
public class UserServiceImpl implements UserService {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private AuthenticationService authenticationService;

  @Override
  public User createUser(CreateUserRequestDTO userDTO) {

    verifyIfUserAlreadyExists(userDTO.getEmail());

    User user = userDTO.toUser();

    return this.userRepository.save(user);
  }

  @Override
  public User getUser(Long id) {
    verifyIfUserExists(id);

    Optional<User> user = userRepository.findById(id);

    return user.get();
  }

  @Override
  public void deleteUser(Long id) {
    verifyIfUserExists(id);

    userRepository.deleteById(id);
  }

  @Override
  public User updateUser(UpdateUserRequestDTO userForm, Long id) {
    verifyIfUserExists(id);

    return this.userRepository.findById(id).map(user -> {
      BeanUtils.copyProperties(userForm, user, ModelUtils.getNullPropertyNames(userForm));

      user.update();

      return this.userRepository.save(user);
    }).get();
  }

  @Override
  public void updateUserPassword(UpdatePasswordRequestDTO updatePasswordRequest, Long id) {
    verifyIfUserExists(id);

    Optional<User> optional = this.userRepository.findById(id);

    User user = optional.get();

    boolean authenticated = this.authenticationService
        .checkPassword(updatePasswordRequest.getOldPassword(), user.getPassword());

    if (!authenticated) {
      throw new PasswordDoesNotMatchException("O password informado está incorreto.");
    }

    boolean passwordsMatches =
        updatePasswordRequest.getNewPassword().equals(updatePasswordRequest.getCheckNewPassword());

    if (!passwordsMatches) {
      throw new PasswordDoesNotMatchException("O password e a confirmação não coincidem.");
    }

    user.setPassword(updatePasswordRequest.getNewPassword());

    this.userRepository.save(user);

  }

  private void verifyIfUserExists(Long id) {
    Optional<User> optional = this.userRepository.findById(id);
    if (optional.isEmpty()) {
      throw new ResourceNotFoundException("Usuário não encontrado.");
    }
  }

  private void verifyIfUserAlreadyExists(String email) {
    Optional<User> optional = this.userRepository.findByEmail(email);
    if (optional.isPresent()) {
      throw new ResourceAlreadyExistsException("Usuário já existe.");
    }
  }
}
