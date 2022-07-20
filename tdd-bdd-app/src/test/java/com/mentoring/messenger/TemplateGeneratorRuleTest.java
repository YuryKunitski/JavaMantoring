package com.mentoring.messenger;

import java.util.Collections;
import java.util.Map;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class TemplateGeneratorRuleTest {

  private final TemplateGenerator generator = new TemplateGenerator();

  @Rule
  public ExpectedException thrown = ExpectedException.none();

  @Test
  public void generateTemplateUnHappyPath() {
    Map<String, String> values = Collections.singletonMap("value", "");
    thrown.expect(IllegalArgumentException.class);
    thrown.expectMessage("Value is not provided.");
    generator.generateTemplate(values);
  }
}
