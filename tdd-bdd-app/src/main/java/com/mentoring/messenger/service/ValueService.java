package com.mentoring.messenger.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collections;
import java.util.Map;
import java.util.Scanner;

public class ValueService {

  private Scanner scanner = new Scanner(System.in);

  public String getValueFromConsole() {
    return scanner.nextLine();
  }

  public Map<String, String> getValueFromFile(String fileName) throws IOException {
    Path filePath = Path.of(String.format("%s.txt", fileName));
    String fileText = Files.readString(filePath);
    int separator = fileText.indexOf(":");
    String placeholder = fileText.substring(0, separator);
    String value = fileText.substring(separator + 1);
    return Collections.singletonMap(placeholder, value);
  }

  public void saveValueIntoFile(String fileName, String textToSave) throws IOException {
    String filePath = String.format("%s.txt", fileName);
    Files.writeString(Path.of(filePath), textToSave);
  }

}
