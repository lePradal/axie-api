package com.prads.axie.models;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "tb_team")
public class Team {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long teamId;

  private String name;

  private LocalDateTime creationDate = LocalDateTime.now();

  @OneToMany(mappedBy = "team")
  private List<Axie> axies;

  @ManyToOne
  @JoinColumn(name = "user_id")
  private User user;

  public Team() {}

  public Team(String name, List<Axie> axies, User user) {
    this.name = name;
    this.axies = axies;
    this.user = user;
  }

  public Long getTeamId() {
    return teamId;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public LocalDateTime getCreationDate() {
    return creationDate;
  }

  public List<Axie> getAxies() {
    return Collections.unmodifiableList(axies);
  }

  public void setAxies(List<Axie> axies) {
    this.axies = axies;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((teamId == null) ? 0 : teamId.hashCode());
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
    Team other = (Team) obj;
    if (teamId == null) {
      if (other.teamId != null)
        return false;
    } else if (!teamId.equals(other.teamId))
      return false;
    return true;
  }

}
