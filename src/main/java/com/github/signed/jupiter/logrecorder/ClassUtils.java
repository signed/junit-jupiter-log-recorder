package com.github.signed.jupiter.logrecorder;

abstract class ClassUtils {

  static boolean isPresent(String className) {
    try {
      forName(className);
      return true;
    } catch (ClassNotFoundException ex) {
      // Class or one of its dependencies is not present...
      return false;
    }
  }

  private static void forName(String name) throws ClassNotFoundException {
    ClassLoader classLoader = getDefaultClassLoader();
    if (classLoader != null) {
      classLoader.loadClass(name);
    } else {
      Class.forName(name);
    }
  }

  private static ClassLoader getDefaultClassLoader() {
    ClassLoader cl = null;
    try {
      cl = Thread.currentThread().getContextClassLoader();
    } catch (Throwable ex) {
      // Cannot access thread context ClassLoader - falling back...
    }
    if (cl == null) {
      // No thread context class loader -> use class loader of this class.
      cl = ClassUtils.class.getClassLoader();
      if (cl == null) {
        // getClassLoader() returning null indicates the bootstrap ClassLoader
        try {
          cl = ClassLoader.getSystemClassLoader();
        } catch (Throwable ex) {
          // Cannot access system ClassLoader - oh well, maybe the caller can live with null...
        }
      }
    }
    return cl;
  }

}
