package com.github.signed.jupiter.logrecorder;

import ch.qos.logback.classic.Logger;
import org.slf4j.LoggerFactory;

import static com.github.signed.jupiter.logrecorder.ClassUtils.isPresent;

public class Logback implements LoggingSystem {

  @Override
  public boolean isRunning() {
    return isPresent("org.slf4j.impl.StaticLoggerBinder") && isPresent("ch.qos.logback.classic.Logger");
  }

  @Override
  public MyLogger loggerFor(Class<?> type) {
    Logger logger = (ch.qos.logback.classic.Logger) LoggerFactory.getLogger(String.class);
    return new MyLogger() {
      @Override
      public LogLevel level() {
        return null;
      }
    };
  }
}
