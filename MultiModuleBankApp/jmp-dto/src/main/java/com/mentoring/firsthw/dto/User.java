package com.mentoring.firsthw.dto;

import java.time.LocalDate;

public class User {

  private String name;
  private String surname;
  private LocalDate birthday;

  public User(String name, String surname, LocalDate birthday) {
    this.name = name;
    this.surname = surname;
    this.birthday = birthday;
  }

  public String getName() {
    return name;
  }

  public String getSurname() {
    return surname;
  }

  public LocalDate getBirthday() {
    return birthday;
  }

  @Override
  public String toString() {
    return "User{" +
        "name='" + name + '\'' +
        ", surname='" + surname + '\'' +
        ", birthday=" + birthday +
        '}';
  }
}
