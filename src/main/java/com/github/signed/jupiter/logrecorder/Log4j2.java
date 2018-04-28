package com.github.signed.jupiter.logrecorder;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static com.github.signed.jupiter.logrecorder.ClassUtils.isPresent;

public class Log4j2 implements LoggingSystem {

  @Override
  public boolean isRunning() {
    return isPresent("org.apache.logging.log4j.Logger");
  }

  @Override
  public MyLogger loggerFor(Class<?> type) {
    Logger logger = LogManager.getLogger(type);
    return new MyLogger() {
      @Override
      public LogLevel level() {
        Level level = logger.getLevel();
        return new LogLevel();
      }
    };
  }
}
