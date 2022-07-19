package com.mentoring.messenger;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

public final class ValueService {
  public  String getValueFromConsole() {
    Scanner scanner = new Scanner(System.in);
    return scanner.nextLine();
  }

  public  String getValueFromFile(String fileName) {
    Path filePath = Path.of(String.format("%s.txt", fileName));
    String result = null;

    try {
      result = Files.readString(filePath);
    } catch (IOException e) {
      throw new IllegalArgumentException(e.getMessage());
    }
    return result;
  }

  public  void saveValueIntoFile(String fileName, String textToSave) throws IOException {
    FileWriter fileWriter = new FileWriter(fileName);
    PrintWriter printWriter = new PrintWriter(fileWriter);
    printWriter.print(textToSave);
    printWriter.flush();
    printWriter.close();
  }

}
