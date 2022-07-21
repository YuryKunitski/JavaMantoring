package com.mentoring.messenger;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertLinesMatch;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.mentoring.messenger.service.ValueService;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collections;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

public class ValueServiceRuleTest {

  private final ValueService valueService = new ValueService();

  @Rule
  public TemporaryFolder tmpFolder = TemporaryFolder.builder().assureDeletion().build();

  @Test
  public void saveValueIntoFileTempDirByRule() throws IOException {
    String fileName = "testInputFile";
    File testFile = tmpFolder.newFile(fileName + ".txt");

    String expectedText = "Some value: #{ValueName}";

    valueService.saveValueIntoFile(String.format("%s/%s", testFile.getParent(), fileName),
        expectedText);

    assertAll(
        () -> assertTrue(Files.exists(testFile.toPath()), "File should exist"),
        () -> assertLinesMatch(Collections.singletonList(expectedText),
            Files.readAllLines(Path.of(testFile.getPath()))));
  }
}
