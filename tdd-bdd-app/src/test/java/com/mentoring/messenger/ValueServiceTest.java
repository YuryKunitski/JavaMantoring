package com.mentoring.messenger;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collections;
import java.util.Scanner;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

class ValueServiceTest {

  private final ValueService valuesSupplier = new ValueService();

  @Test
  void getValueFromConsole() {
    String expected = "#{ValueName}";
    String actual = valuesSupplier.getValueFromConsole();

    assertEquals(expected, actual);
  }

  @Test
  void getValueFromFileTempDir(@TempDir Path tempDir) throws IOException {
    String fileName = "testInputFile";
    Path tempPath = tempDir.resolve(String.format("%s.txt", fileName));
    String expectedText = "#{ValueName}";
    Files.writeString(tempPath, expectedText);

    String actual = valuesSupplier.getValueFromFile(String.format("%s/%s",tempPath.getParent(), fileName));

    assertAll(
        () -> assertEquals(expectedText, actual),
        () -> assertTrue(Files.exists(tempPath), "File should exist"),
        () -> assertLinesMatch(Collections.singletonList(expectedText),
            Files.readAllLines(tempPath)));
  }

  @Test
  void saveValueIntoFileTempDir(@TempDir Path tempDir) throws IOException {
    String fileName = "testOutputFile";
    Path tempPath = tempDir.resolve(String.format("%s.txt", fileName));
    String expectedText = "Some value: #{ValueName}";

    valuesSupplier.saveValueIntoFile(String.format("%s/%s",tempPath.getParent(), fileName), expectedText);

    assertAll(
        () -> assertTrue(Files.exists(tempPath), "File should exist"),
        () -> assertLinesMatch(Collections.singletonList(expectedText),
            Files.readAllLines(tempPath)));
  }
}