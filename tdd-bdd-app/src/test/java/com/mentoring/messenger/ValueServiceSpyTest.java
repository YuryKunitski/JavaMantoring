package com.mentoring.messenger;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

import java.io.IOException;
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
    ValueService serviceSpy = spy(new ValueService());
    doReturn(expectedText).when(serviceSpy).getValueFromFile(fileName);

    String actual = serviceSpy.getValueFromFile(fileName);

    assertEquals(expectedText, actual);
    verify(serviceSpy).getValueFromFile(fileName);
  }
}
