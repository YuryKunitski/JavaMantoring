package com.mentoring.messenger.generator;

import com.mentoring.messenger.service.ValueService;
import java.io.IOException;
import java.util.Collections;
import java.util.Map;
import java.util.Objects;

public class TemplateGenerator {

  private static final String TEMPLATE_VALUE = "#{value}";
  private static final String TEMPLATE = String.format("Some text: %s", TEMPLATE_VALUE);
  private final ValueService valueService = new ValueService();

  public String generateTemplate(Map<String, String> values) {
    for (String value : values.values()) {
      if (value.isBlank()) {
        throw new IllegalArgumentException("Value is not provided.");
      }
    }

    String keyTemplate = TEMPLATE.substring(TEMPLATE.indexOf("{") + 1, TEMPLATE.indexOf("}"));

    return values.containsKey(keyTemplate)
        ? TEMPLATE.replace(TEMPLATE_VALUE, values.get(keyTemplate))
        : null;
  }

  public void consoleMode() {
    System.out.println(
        "Application works in console mode.\nPlease provide placeholder and value.\nplaceholder = ");
    String placeholder = valueService.getValueFromConsole();
    System.out.println("value =  ");
    String value = valueService.getValueFromConsole();
    String generatedMessage = generateTemplate(Collections.singletonMap(placeholder, value));
    if (Objects.isNull(generatedMessage)) {
      generatedMessage = String.format(
          "Generating message was ignored since there is no such '%s' placeholder in template.",
          placeholder);
    }
    System.out.printf("Generated message: %s%n", generatedMessage);
  }

  public void fileMode(String inputFileName, String outputFileName) throws IOException {
    System.out.printf("Application works in file mode.%ninputFileName: '%s'%n", inputFileName);
    Map<String, String> dataFromFile = valueService.getValueFromFile(inputFileName);
    String generatedMessage = generateTemplate(dataFromFile);
    if (Objects.isNull(generatedMessage)) {
      System.out.printf(
          "Generating message was ignored since there is no such placeholder as '%s' in template.",
          dataFromFile.keySet().stream().findFirst().orElseThrow());
    } else {
      valueService.saveValueIntoFile(outputFileName, generatedMessage);
      System.out.printf("Please check the outputFileName with name '%s'", outputFileName);
    }
  }
}
