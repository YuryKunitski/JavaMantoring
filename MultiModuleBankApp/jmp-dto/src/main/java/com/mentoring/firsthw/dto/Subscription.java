package com.mentoring.firsthw.dto;

import java.time.LocalDate;

public class Subscription {

  private String bankcard;
  private LocalDate startDate;

  public Subscription(String bankcard, LocalDate startDate) {
    this.bankcard = bankcard;
    this.startDate = startDate;
  }

  public String getBankcard() {
    return bankcard;
  }

  public LocalDate getStartDate() {
    return startDate;
  }

  @Override
  public String toString() {
    return "Subscription{" +
        "bankcard='" + bankcard + '\'' +
        ", startDate=" + startDate +
        '}';
  }
}
