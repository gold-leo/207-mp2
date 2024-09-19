package edu.grinnell.csc207.utils;

public class General {
  public static int l2i(char letter) {
    return (int) letter - (int) 'a';
  }

  public static char i2l(int i) {
    return (char) (i + (int) 'a');
  }
}
