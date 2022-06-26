package com.mentoring.task5.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class UserAccount implements Serializable {

  private Map<Currency, BigDecimal> wallet;
  private String name;

  public UserAccount(String name) {
    this.name = name;
    this.wallet = new HashMap<>();
  }

  public Map<Currency, BigDecimal> getWallet() {
    return wallet;
  }

  public void setWallet(Map<Currency, BigDecimal> wallet) {
    this.wallet = wallet;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Override
  public String toString() {
    return "UserAccount{" +
        "wallet=" + wallet +
        ", name='" + name + '\'' +
        '}';
  }
}
