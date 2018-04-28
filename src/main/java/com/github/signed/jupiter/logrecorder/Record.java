package com.github.signed.jupiter.logrecorder;

import org.junit.jupiter.api.extension.ExtendWith;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@ExtendWith(LogRecorderExtension.class)
public @interface Record {

  Class<?>[] loggerClasses() default {};
}


