package com.mentoring.messenger;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

import java.io.IOException;
import org.junit.jupiter.api.Test;

class ValueServiceTest {

  private final ValueService valuesSupplier = new ValueService();

  @Test
  void getValueFromConsoleHappyPath() {
    String expected = "#{ValueName}";
    String actual = valuesSupplier.getValueFromConsole();

    assertEquals(expected, actual);
  }

  @Test
  void getValueFromFileHappyPath() {
    String expected = "#{ValueName}";
    String actual = valuesSupplier.getValueFromFile("valueFile");

    assertEquals(expected, actual);
  }

  @Test
  void saveValueIntoFileHappyPath() throws IOException {
    String textToSave = "Some value: #{ValueName}";
    valuesSupplier.saveValueIntoFile("testOutputFile", textToSave);

  }
}