package com.prads.axie.models;

import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tb_daily")
public class Daily {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long dailyId;

  private boolean questCompleted;
  private boolean adventureCompleted;
  private Double pvpGain;
  private Double totalGain;
  private LocalDateTime date;

  @ManyToOne
  @JoinColumn(name = "user_id")
  private User user;

  public Daily() {}

  public Daily(User user) {
    this.user = user;
  }

  public Long getDailyId() {
    return dailyId;
  }

  public boolean isQuestCompleted() {
    return questCompleted;
  }

  public void setQuestCompleted(boolean questCompleted) {
    this.questCompleted = questCompleted;
  }

  public boolean isAdventureCompleted() {
    return adventureCompleted;
  }

  public void setAdventureCompleted(boolean adventureCompleted) {
    this.adventureCompleted = adventureCompleted;
  }

  public Double getPvpGain() {
    return pvpGain;
  }

  public void setPvpGain(Double pvpGain) {
    this.pvpGain = pvpGain;
  }

  public Double getTotalGain() {
    return totalGain;
  }

  public void setTotalGain(Double totalGain) {
    this.totalGain = totalGain;
  }

  public LocalDateTime getDate() {
    return date;
  }

  public void setDate(LocalDateTime date) {
    this.date = date;
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
    result = prime * result + ((dailyId == null) ? 0 : dailyId.hashCode());
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
    Daily other = (Daily) obj;
    if (dailyId == null) {
      if (other.dailyId != null)
        return false;
    } else if (!dailyId.equals(other.dailyId))
      return false;
    return true;
  }

}
