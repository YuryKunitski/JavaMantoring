package com.mentoring.messenger;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

class TemplateGeneratorTest {

  private final TemplateGenerator generator = new TemplateGenerator();

  @ParameterizedTest
  @MethodSource("getValidValues")
  void generateTemplateHappyPath(String value) {
    String expectedMessage = "Some text: " + value;
    Map<String, String> values = Collections.singletonMap("value", value);
    String actualMessage = generator.generateTemplate(values);

    assertEquals(expectedMessage, actualMessage);
  }

  @ParameterizedTest
  @ValueSource(strings = {"", " "})
  void generateTemplateUnHappyPath(String value) {
    Map<String, String> values = Collections.singletonMap("value", value);

    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
        () -> generator.generateTemplate(values));
    assertEquals("Value is not provided.", exception.getMessage());
  }

  @TestFactory
  Collection<DynamicTest> generateTemplateDynamicTest() {
    String expectedMessage = "Some text: valueName";
    String key = "value";

    Executable executableByInvalidValue = () -> generator.generateTemplate(
        Collections.singletonMap(key, ""));

    return List.of(
        DynamicTest.dynamicTest("Generate template by valid value",
            () -> assertEquals(expectedMessage,
                generator.generateTemplate(Collections.singletonMap(key, "valueName")))),
        DynamicTest.dynamicTest("Generate template by invalid value",
            () -> assertThrows(IllegalArgumentException.class, executableByInvalidValue)));
  }

  @Test
  @Tag("include")
  void generateTemplateHappyPathFilteredIncluded() {
    String expectedMessage = "Some text: #{valueName}";
    Map<String, String> values = Collections.singletonMap("value", "#{valueName}");
    String actualMessage = generator.generateTemplate(values);

    assertEquals(expectedMessage, actualMessage);
  }

  @Exclude
  void generateTemplateHappyPathFilteredExcluded() {
    String expectedMessage = "Some text: #{valueName}";
    Map<String, String> values = Collections.singletonMap("value", "#{valueName}");
    String actualMessage = generator.generateTemplate(values);

    assertEquals(expectedMessage, actualMessage);
  }

  @Include
  void generateTemplateUnHappyPathFilteredIncluded() {
    Map<String, String> values = Collections.singletonMap("value", "");

    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
        () -> generator.generateTemplate(values));

    assertEquals("Value is not provided.", exception.getMessage());
  }

  @Test
  @Tag("exclude")
  void generateTemplateUnHappyPathFilteredExcluded() {
    Map<String, String> values = Collections.singletonMap("value", "");

    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
        () -> generator.generateTemplate(values));

    assertEquals("Value is not provided.", exception.getMessage());
  }

  private static List<String> getValidValues() {
    return List.of("#{ValueName}", "valueName", "0");
  }

}