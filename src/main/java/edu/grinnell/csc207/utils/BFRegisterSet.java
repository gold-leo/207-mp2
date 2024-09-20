package edu.grinnell.csc207.utils;

public class BFRegisterSet {

  // +--------+-------------------------------------------------------
  // | Fields |
  // +--------+

  BigFraction[] registry = new BigFraction[26];

  // +--------------+-------------------------------------------------
  // | Constructors |
  // +--------------+

  public BFRegisterSet() {
    for (int i = 0; i < 26; i++) {
      registry[i] = new BigFraction();
    }
  }

  // +---------+------------------------------------------------------
  // | Methods |
  // +---------+

  public static boolean valid_register(String str) {
    if (str.length() > 1) {
      return false;
    }
    int i = General.l2i(str.charAt(0));
    if (i < 0 || i > 25) {
      return false;
    }
    return true;
  }

  //stores the given value in the specified register.
  public void store(char register, BigFraction val) {
    registry[General.l2i(register)] = val;
  }

  //retrieves the value from the given register.
  public BigFraction get(char register) {
    BigFraction v = registry[General.l2i(register)];
    return v;
  }
}
