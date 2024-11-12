package edu.grinnell.csc207.utils;

/**
 * Registry for BigFractions. Stores up to 26 values (a-z). 
 */
public class BFRegisterSet {

  // +--------+-------------------------------------------------------
  // | Fields |
  // +--------+

  /**
   * The array which stores the data.
   */
  BigFraction[] registry = new BigFraction[26];

  // +--------------+-------------------------------------------------
  // | Constructors |
  // +--------------+

  /**
   * Constructor for the registry.
   * Sets all registries to 0/0 BigFractions.
   */
  public BFRegisterSet() {
    for (int i = 0; i < 26; i++) {
      registry[i] = new BigFraction();
    } // for
  } // BFRegisterSet()

  // +---------+------------------------------------------------------
  // | Methods |
  // +---------+

  /**
   * Checks if the str is a valid register character.
   * @param str
   * @return true or false
   */
  public static boolean validRegister(String str) {

    // Safety check: str must have a length of 1.
    if (str.length() > 1 || str.length() < 1) {
      return false;
    } // if

    // Check if the character in str is a valid register char.
    int i = General.l2i(str.charAt(0));
    if (i < 0 || i > 25) {
      return false;
    } // if
    return true;
  } // validRegister(str)

  /**
   * Stores the val in the register.
   * Prereq: register must be a valid register character. 
   * @param val
   * @param register
   */
  public void store(char register, BigFraction val) {
    registry[General.l2i(register)] = val;
  } // store(register, val)

  /**
   * Retrieves the value from register.
   * Prereq: register must be a valid register character. 
   * @param register
   * @return BigFraction v
   **/
  public BigFraction get(char register) {
    BigFraction v = registry[General.l2i(register)];
    return v;
  } // get(register)
} // BFRegisterSet
