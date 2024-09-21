package edu.grinnell.csc207.utils;

/**
 * Registry for BigFractions.
 */
public class BFRegisterSet {

  // +--------+-------------------------------------------------------
  // | Fields |
  // +--------+

  /**
   * Registry.
   */
  BigFraction[] registry = new BigFraction[26];

  // +--------------+-------------------------------------------------
  // | Constructors |
  // +--------------+

  /**
   * Constructor for the registry.
   */
  public BFRegisterSet() {
    for (int i = 0; i < 26; i++) {
      registry[i] = new BigFraction();
    } // for
  } // constructor

  // +---------+------------------------------------------------------
  // | Methods |
  // +---------+

  /**
   * Checks if the str is a valid register character.
   * @param str
   * @return t or f
   */
  public static boolean validRegister(String str) {
    if (str.length() > 1) {
      return false;
    } // if
    int i = General.l2i(str.charAt(0));
    if (i < 0 || i > 25) {
      return false;
    } // if
    return true;
  } // valid_register

  /**
   * stores the val in the register.
   * @param val
   * @param register
   */
  public void store(char register, BigFraction val) {
    registry[General.l2i(register)] = val;
  } //store

  /**
   * retrieves the value from register.
   * @param register
   * @return v
   **/
  public BigFraction get(char register) {
    BigFraction v = registry[General.l2i(register)];
    return v;
  } // get
} // class
