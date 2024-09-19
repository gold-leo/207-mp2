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

  }

  // +---------+------------------------------------------------------
  // | Methods |
  // +---------+

  //stores the given value in the specified register.
  public void store(char register, BigFraction val) {
    registry[General.l2i(register)] = val;
  }

  //retrieves the value from the given register.
  public BigFraction get(char register) throws Exception {
    BigFraction v = registry[General.l2i(register)];

    if (v == null) {
      throw new Exception("Invalid register " + register);
    }
    else {
      return v;
    }
  }
}
