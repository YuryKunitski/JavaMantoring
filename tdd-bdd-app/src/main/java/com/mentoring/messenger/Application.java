package com.mentoring.messenger;

import com.mentoring.messenger.generator.TemplateGenerator;
import java.io.IOException;

public class Application {

  public static void main(String[] args) throws IOException {
    TemplateGenerator templateGenerator = new TemplateGenerator();

    if (args.length == 0) {
      templateGenerator.consoleMode();
    } else if (args.length == 2) {
      templateGenerator.fileMode(args[0], args[1]);
    } else {
      throw new IllegalArgumentException("Wrong application parameters.");
    }
  }
}
