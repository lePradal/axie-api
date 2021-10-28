package com.prads.axie.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.prads.axie.config.security.TokenService;
import com.prads.axie.dto.CreateUserRequestDTO;
import com.prads.axie.dto.UpdatePasswordRequestDTO;
import com.prads.axie.dto.UpdateUserRequestDTO;
import com.prads.axie.dto.UserResponseDTO;
import com.prads.axie.models.User;
import com.prads.axie.repository.UserRepository;
import com.prads.axie.service.UserService;

@RestController
@RequestMapping(path = "/users")
public class UserController {

  @Autowired
  private UserService service;

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private TokenService tokenService;

  @PostMapping
  public ResponseEntity create(@RequestBody CreateUserRequestDTO user) {

    User createdUser = this.service.createUser(user);

    return new ResponseEntity<UserResponseDTO>(UserResponseDTO.from(createdUser),
        HttpStatus.CREATED);
  }

  @GetMapping("/{id}")
  public ResponseEntity getUser(
      @RequestHeader(required = true, value = "Authorization") String bearerToken,
      @PathVariable(required = true) Long id) {

    this.tokenService.validateUserIdMatchByTokenAndByPathParam(bearerToken, id);

    User user = this.service.getUser(id);

    return new ResponseEntity<UserResponseDTO>(UserResponseDTO.from(user), HttpStatus.OK);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity deleteUser(
      @RequestHeader(required = true, value = "Authorization") String bearerToken,
      @PathVariable(required = true) Long id) {

    this.tokenService.validateUserIdMatchByTokenAndByPathParam(bearerToken, id);

    this.service.deleteUser(id);

    return ResponseEntity.ok().build();
  }

  @PutMapping("/{id}")
  public ResponseEntity updateUser(@RequestBody UpdateUserRequestDTO updateUserRequest,
      @PathVariable(required = true) Long id) {
    User updatedUser = this.service.updateUser(updateUserRequest, id);

    // throw new ResourceNotFoundException("Usuário não encontrado.");
    //
    // Optional<User> optional = this.userRepository.findById(id);
    // if (optional.isEmpty()) {
    // throw new ResourceNotFoundException("Usuário não encontrado.");
    // }
    //
    // User updatedUser = this.userRepository.findById(id).map(user -> {
    // BeanUtils.copyProperties(userForm, user, ModelUtils.getNullPropertyNames(userForm));
    //
    // return this.userRepository.save(user);
    // }).get();
    //
    return new ResponseEntity<UserResponseDTO>(UserResponseDTO.from(updatedUser), HttpStatus.OK);
  }

  @PutMapping("/pass/{id}")
  public ResponseEntity updateUserPassword(
      @RequestBody UpdatePasswordRequestDTO updatePasswordRequest,
      @PathVariable(required = true) Long id) {
    this.service.updateUserPassword(updatePasswordRequest, id);

    return ResponseEntity.ok().build();
  }

  // @ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Usuário não encontrado.")
  // @ExceptionHandler(ResourceNotFoundException.class)
  // public ResponseEntity<ExceptionDetails> handleResourceNotFoundException(
  // ResourceNotFoundException exception) {
  // ExceptionDetails details = new ExceptionDetails.builder().status(HttpStatus.NOT_FOUND.value())
  // .timestamp(new Date().getTime()).message(exception.getMessage())
  // .title("Usuário não encontrado.").build();
  //
  // return new ResponseEntity<ExceptionDetails>(details, HttpStatus.NOT_FOUND);
  // }

}
