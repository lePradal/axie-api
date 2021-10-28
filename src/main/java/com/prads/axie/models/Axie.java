package com.prads.axie.models;

import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import com.prads.axie.enums.AxieClass;

@Entity
@Table(name = "tb_axie")
public class Axie {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long axieId;

  private String name;
  private Integer health;
  private Integer speed;
  private Integer skill;
  private Integer morale;

  @Enumerated(EnumType.ORDINAL)
  private AxieClass axieClass;

  private Double price;
  private Integer breedCount;

  private LocalDateTime creationDate = LocalDateTime.now();

  @ManyToOne
  @JoinColumn(name = "team_id")
  private Team team;

  public Axie() {}

  public Axie(Team team, AxieClass axieClass) {
    this.team = team;
    this.axieClass = axieClass;
  }

  public Long getAxieId() {
    return axieId;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Integer getHealth() {
    return health;
  }

  public void setHealth(Integer health) {
    this.health = health;
  }

  public Integer getSpeed() {
    return speed;
  }

  public void setSpeed(Integer speed) {
    this.speed = speed;
  }

  public Integer getSkill() {
    return skill;
  }

  public void setSkill(Integer skill) {
    this.skill = skill;
  }

  public Integer getMorale() {
    return morale;
  }

  public void setMorale(Integer morale) {
    this.morale = morale;
  }

  public AxieClass getAxieClass() {
    return axieClass;
  }

  public void setAxieClass(AxieClass axieClass) {
    this.axieClass = axieClass;
  }

  public Double getPrice() {
    return price;
  }

  public void setPrice(Double price) {
    this.price = price;
  }

  public Integer getBreedCount() {
    return breedCount;
  }

  public void setBreedCount(Integer breedCount) {
    this.breedCount = breedCount;
  }

  public LocalDateTime getCreationDate() {
    return creationDate;
  }

  public Team getTeam() {
    return team;
  }

  public void setTeam(Team team) {
    this.team = team;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((axieId == null) ? 0 : axieId.hashCode());
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
    Axie other = (Axie) obj;
    if (axieId == null) {
      if (other.axieId != null)
        return false;
    } else if (!axieId.equals(other.axieId))
      return false;
    return true;
  }

}
