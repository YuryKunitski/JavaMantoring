package com.mentoring.messenger;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

import com.mentoring.messenger.service.ValueService;
import java.io.IOException;
import java.util.Collections;
import java.util.Map;
import java.util.Scanner;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ValueServiceSpyTest {

  @InjectMocks
  ValueService service = new ValueService();

  @Spy
  Scanner scanner = new Scanner(System.in);

  @Test
  void getValueFromConsoleSpy() {
    String expected = "#{ValueName}";
    doReturn(expected).when(scanner).nextLine();

    String actual = service.getValueFromConsole();

    assertEquals(expected, actual);
  }

  @Test
  void getValueFromFileMock() throws IOException {
    String fileName = "testInputFile";
    String expectedText = "#{ValueName}";
    Map<String, String> expected = Collections.singletonMap("value", expectedText);
    ValueService serviceSpy = spy(new ValueService());
    doReturn(expected).when(serviceSpy).getValueFromFile(fileName);

    Map<String, String> actual = serviceSpy.getValueFromFile(fileName);

    assertEquals(expected, actual);
    verify(serviceSpy).getValueFromFile(fileName);
  }
}
