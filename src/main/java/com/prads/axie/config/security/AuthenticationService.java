package com.prads.axie.config.security;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.prads.axie.models.User;
import com.prads.axie.repository.UserRepository;

@Service
public class AuthenticationService implements UserDetailsService {

  @Autowired
  private UserRepository userRepository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    Optional<User> user = userRepository.findByEmail(username);
    if (user.isPresent()) {
      return user.get();
    }

    throw new UsernameNotFoundException("Invalid Username!");
  }

  public boolean checkPassword(String passwordOne, String passwordTwo) {
    return new BCryptPasswordEncoder().matches(passwordOne, passwordTwo);
  }

}
