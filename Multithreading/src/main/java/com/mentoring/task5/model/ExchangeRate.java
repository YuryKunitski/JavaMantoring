package com.mentoring.task5.model;

import java.math.BigDecimal;

public class ExchangeRate {

  private Currency from;
  private Currency to;
  private double rate;

  public ExchangeRate(Currency from, Currency to, double rate) {
    this.from = from;
    this.to = to;
    this.rate = rate;
  }

//  public BigDecimal calculateRate (Currency from, Currency to) {  }


  public double getRate() {
    return rate;
  }

  public void setRate(double rate) {
    this.rate = rate;
  }

  public Currency getFrom() {
    return from;
  }

  public Currency getTo() {
    return to;
  }
}
