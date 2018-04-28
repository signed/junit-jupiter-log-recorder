package com.github.signed.jupiter.logrecorder;

import org.junit.jupiter.api.Test;
import org.slf4j.LoggerFactory;

class BlubTest {

  @Test
  @Record(loggerClasses = BlubTest.class)
  void name(LogRecording logRecording) {
    System.out.println(logRecording);
  }

  @Test
  void name() {
    LoggerFactory.getLogger(String.class);
  }
}
