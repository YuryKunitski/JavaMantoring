package com.mentoring.messenger.util;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.extension.AfterAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestWatcher;

public class OutputTestExecutionInfoToFile implements AfterAllCallback, TestWatcher {

  List<String> testResults = new ArrayList<>();

  @Override
  public void afterAll(ExtensionContext extensionContext) throws Exception {
    String filePath = "tests-result.txt";
    Files.write(Path.of(filePath), testResults);
  }

  @Override
  public void testSuccessful(ExtensionContext context) {
    String testResult = context.getDisplayName() + " is successful.\n";
    testResults.add(testResult);
  }

  @Override
  public void testFailed(ExtensionContext context, Throwable cause) {
    String testResult = context.getDisplayName() + " is failed.\n";
    testResults.add(testResult);
  }
}
