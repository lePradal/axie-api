package com.prads.axie.models;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Entity
@Table(name = "tb_user")
public class User implements UserDetails {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long userId;

  @NotNull
  @NotBlank
  private String name;

  @NotNull
  @NotBlank
  private String email;

  @NotNull
  @NotBlank
  private String password;

  private LocalDateTime creationDate = LocalDateTime.now();

  private LocalDateTime lastUpdate;

  @ManyToMany(fetch = FetchType.EAGER)
  private List<Profile> profiles = new ArrayList<>();

  @OneToMany(mappedBy = "user")
  private List<Team> teams;

  @OneToMany(mappedBy = "user")
  private List<Daily> dailies;

  public User() {
    this.lastUpdate = LocalDateTime.now();
  }

  public User(String name, String email, String password) {
    this.name = name;
    this.email = email;
    this.password = password;
    this.lastUpdate = LocalDateTime.now();
  }

  public Long getUserId() {
    return userId;
  }

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
    this.password = new BCryptPasswordEncoder().encode(password);
  }

  public LocalDateTime getCreationDate() {
    return creationDate;
  }

  public LocalDateTime getLastUpdate() {
    return lastUpdate;
  }

  public void update() {
    this.lastUpdate = LocalDateTime.now();
  }

  public List<Profile> getProfiles() {
    return profiles;
  }

  public void setProfiles(List<Profile> profiles) {
    this.profiles = profiles;
  }

  public List<Team> getTeams() {
    return teams != null ? Collections.unmodifiableList(teams)
        : Collections.unmodifiableList(new ArrayList<>());
  }

  public void setTeams(List<Team> teams) {
    this.teams = teams;
  }

  public List<Daily> getDailies() {
    return dailies != null ? Collections.unmodifiableList(dailies)
        : Collections.unmodifiableList(new ArrayList<>());
  }

  public void setDailies(List<Daily> dailies) {
    this.dailies = dailies;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((userId == null) ? 0 : userId.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    User other = (User) obj;
    if (userId == null) {
      if (other.userId != null)
        return false;
    } else if (!userId.equals(other.userId))
      return false;
    return true;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return profiles;
  }

  @Override
  public String getUsername() {
    return email;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }

}
