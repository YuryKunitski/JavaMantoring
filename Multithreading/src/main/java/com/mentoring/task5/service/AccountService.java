package com.mentoring.task5.service;

import com.mentoring.task5.model.UserAccount;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class AccountService {

  public UserAccount createUserAccount(String userName) {
    return new UserAccount(userName);
  }

  public void saveToFile(UserAccount userAccount) {
    String fileName = userAccount.getName();
    try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
      File existingAccountFile = new File(fileName);

      synchronized (userAccount) {
        if (existingAccountFile.exists()) {
          existingAccountFile.delete();
          oos.writeObject(userAccount);
        } else {
          oos.writeObject(userAccount);
        }
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

}
