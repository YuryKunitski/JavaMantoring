package com.mentoring.messenger;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Collections;
import java.util.Map;
import org.junit.jupiter.api.Test;

class TemplateGeneratorTest {

  private final TemplateGenerator generator = new TemplateGenerator();

  @Test
  void replaceSubjectHappyPath() {
    String expectedMessage = "Some text: #{ValueName}";
    Map<String, String> values = Collections.singletonMap("value", "#{ValueName}");
    String actualMessage = generator.generateTemplate(values);

    assertEquals(expectedMessage, actualMessage);
  }

  @Test
  void replaceSubjectUnHappyPath() {
    Map<String, String> values = Collections.singletonMap("value", "");

    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
        () -> generator.generateTemplate(values));
    assertEquals("Value is not provided.", exception.getMessage());
  }


}