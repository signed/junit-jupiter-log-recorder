package com.github.signed.jupiter.logrecorder;

public interface LoggingSystem {

  boolean isRunning();

  MyLogger loggerFor(Class<?> type);

}
