package com.github.signed.jupiter.logrecorder;

import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolutionException;
import org.junit.jupiter.api.extension.ParameterResolver;

public class LogRecorderExtension implements ParameterResolver, BeforeEachCallback, AfterEachCallback {

  @Override
  public void beforeEach(ExtensionContext context) throws Exception {
    String uniqueId = context.getUniqueId();
    System.out.println(uniqueId);
    ExtensionContext.Namespace namespace = ExtensionContext.Namespace.create(uniqueId);
    ExtensionContext.Store store = context.getStore(namespace);

    startRecording();

    System.out.println("before each");
  }

  @Override
  public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
    System.out.println("supports parameter");
    return LogRecording.class.isAssignableFrom(parameterContext.getParameter().getType());
  }

  @Override
  public Object resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
    return new DefaultLogRecording();
  }

  @Override
  public void afterEach(ExtensionContext context) throws Exception {
    System.out.println("after each");
  }

  public void startRecording() {
    Logback logback = new Logback();
    if (logback.isRunning()) {
      MyLogger logger = logback.loggerFor(String.class);
      System.out.println("logback present :)");
    } else {
      System.out.println("no logback :(");
    }

    Log4j2 log4j2 = new Log4j2();
    if (log4j2.isRunning()) {
      MyLogger myLogger = log4j2.loggerFor(String.class);

      System.out.println("log4j2 present :)");
    } else {
      System.out.println("no log4j2 :(");
    }
  }
}
