package com.mentoring.messenger.cucumber;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.mentoring.messenger.generator.TemplateGenerator;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class TemplateGeneratorTestStepDefinition {

  Map<String, String> clientData = new HashMap<>();

  String actual;

  Exception exception;


  @Given("client provide variable {string} with value {string}")
  public void clientProvideVariableValueWithValueClientValue(String variable, String value) {
    clientData.put(variable, value);
  }

  @When("run generator")
  public void runGenerator() {
    TemplateGenerator templateGenerator = new TemplateGenerator();
    actual = templateGenerator.generateTemplate(clientData);
  }

  @Then("the generated message is {string}")
  public void theGeneratedMessageIsSomeTextClientValue(String expected) {
    assertEquals(expected, actual);
  }

  @When("run generator and throw IllegalArgumentException")
  public void runGeneratorAndThrowIllegalArgumentException() {
    TemplateGenerator templateGenerator = new TemplateGenerator();
    exception = assertThrows(IllegalArgumentException.class,
        () -> templateGenerator.generateTemplate(clientData));
  }

  @Then("exception message is {string}")
  public void exceptionMessageIsValueIsNotProvided(String expectedExceptionMsg) {
    assertEquals(expectedExceptionMsg, exception.getMessage());
  }
}
