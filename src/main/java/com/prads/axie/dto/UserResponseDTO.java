package com.prads.axie.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.springframework.beans.BeanUtils;
import com.prads.axie.models.Daily;
import com.prads.axie.models.Team;
import com.prads.axie.models.User;
import com.prads.axie.utils.ModelUtils;

public class UserResponseDTO {

  private Long userId;
  private String name;
  private String email;
  private LocalDateTime creationDate;
  private LocalDateTime lastUpdate;
  private List<Team> teams;
  private List<Daily> dailies;

  public Long getUserId() {
    return userId;
  }

  public void setUserId(Long userId) {
    this.userId = userId;
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

  public LocalDateTime getCreationDate() {
    return creationDate;
  }

  public void setCreationDate(LocalDateTime creationDate) {
    this.creationDate = creationDate;
  }

  public LocalDateTime getLastUpdate() {
    return lastUpdate;
  }

  public void setLastUpdate(LocalDateTime lastUpdate) {
    this.lastUpdate = lastUpdate;
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

  public static UserResponseDTO from(User user) {
    UserResponseDTO dto = new UserResponseDTO();

    BeanUtils.copyProperties(user, dto, ModelUtils.getNullPropertyNames(user));

    return dto;
  }

}
