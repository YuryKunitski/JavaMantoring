package com.mentoring.messenger;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collections;
import java.util.Scanner;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import org.mockito.InjectMocks;
import org.mockito.Mock;

class ValueServiceTest {

  private final ValueService valueService = new ValueService();

  @InjectMocks
  ValueService service = new ValueService();

  @Mock
  Scanner scanner;

  @Test
  void getValueFromConsoleMock() {
    String expected = "#{ValueName}";
    openMocks(this);
    when(scanner.nextLine()).thenReturn(expected);

    String actual = service.getValueFromConsole();

    assertEquals(expected, actual);
  }

  @Test
  void getValueFromFileTempDir(@TempDir Path tempDir) throws IOException {
    String fileName = "testInputFile";
    Path tempPath = tempDir.resolve(String.format("%s.txt", fileName));
    String expectedText = "#{ValueName}";
    Files.writeString(tempPath, expectedText);

    String actual = valueService.getValueFromFile(
        String.format("%s/%s", tempPath.getParent(), fileName));

    assertAll(
        () -> assertEquals(expectedText, actual),
        () -> assertTrue(Files.exists(tempPath), "File should exist"),
        () -> assertLinesMatch(Collections.singletonList(expectedText),
            Files.readAllLines(tempPath)));
  }

  @Test
  void getValueFromFileMock() throws IOException {
    String fileName = "testInputFile";
    String expectedText = "#{ValueName}";
    ValueService service = mock(ValueService.class);
    when(service.getValueFromFile(fileName)).thenReturn(expectedText);

    String actual = service.getValueFromFile(fileName);

    assertEquals(expectedText, actual);
  }

  @Test
  void saveValueIntoFileTempDir(@TempDir Path tempDir) throws IOException {
    String fileName = "testOutputFile";
    Path tempPath = tempDir.resolve(String.format("%s.txt", fileName));
    String expectedText = "Some value: #{ValueName}";

    valueService.saveValueIntoFile(String.format("%s/%s", tempPath.getParent(), fileName),
        expectedText);

    assertAll(
        () -> assertTrue(Files.exists(tempPath), "File should exist"),
        () -> assertLinesMatch(Collections.singletonList(expectedText),
            Files.readAllLines(tempPath)));
  }
}