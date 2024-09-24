package edu.grinnell.csc207.utils;

import java.math.BigInteger;

/**
 * A simple implementation of arbitrary-precision Fractions.
 *
 * @author Samuel A. Rebelsky
 * @author Leo Goldman
 */
public class BigFraction {
  // +------------------+---------------------------------------------
  // | Design Decisions |
  // +------------------+

  /*
   * (1) Denominators are always positive. Therefore, negative fractions
   * are represented with a negative numerator. Similarly, if a fraction
   * has a negative numerator, it is negative.
   *
   * (2) Fractions are not necessarily stored in simplified form. To
   * obtain a fraction in simplified form, one must call the `simplify`
   * method.
   */

  // +--------+-------------------------------------------------------
  // | Fields |
  // +--------+

  /** The numerator of the fraction. Can be positive, zero or negative. */
  BigInteger num;

  /** The denominator of the fraction. Must be non-negative. */
  BigInteger denom;

  // +--------------+-------------------------------------------------
  // | Constructors |
  // +--------------+

  /**
   * Constructor for invalid BigFraction.
   */
  public BigFraction() {
    this.num = BigInteger.ZERO;
    this.denom = BigInteger.ZERO;
  } // constructor

  /**
   * Build a new fraction with numerator num and denominator denom.
   *
   * Warning! Not yet stable.
   *
   * @param numerator
   *                    The numerator of the fraction.
   * @param denominator
   *                    The denominator of the fraction.
   */
  public BigFraction(BigInteger numerator, BigInteger denominator) {
    this.num = numerator;
    this.denom = denominator;
  } // BigFraction(BigInteger, BigInteger)

  /**
   * Build a new fraction with numerator num and denominator denom.
   *
   * Warning! Not yet stable.
   *
   * @param numerator
   *                    The numerator of the fraction.
   * @param denominator
   *                    The denominator of the fraction.
   */
  public BigFraction(int numerator, int denominator) {
    this.num = BigInteger.valueOf(numerator);
    this.denom = BigInteger.valueOf(denominator);
  } // BigFraction(int, int)

  /**
   * Build a new fraction by parsing a string.
   *
   * Warning! Not yet implemented.
   *
   * @param str
   *            The fraction in string form
   */
  public BigFraction(String str) {
    String[] nums = str.split("/");

    if (nums.length == 2) {
      this.denom = BigInteger.valueOf(Integer.parseInt(nums[1]));
    } else if (nums.length == 1) {
      this.denom = BigInteger.valueOf(1);
    } else {
      this.denom = BigInteger.ZERO;
    } // if else

    try {
      this.num = BigInteger.valueOf(Integer.parseInt(nums[0]));
    } catch (NumberFormatException e) {
      this.num = BigInteger.ZERO;
      this.denom = BigInteger.ZERO;
    } // try
  } // BigFraction

  // +---------+------------------------------------------------------
  // | Methods |
  // +---------+

  /**
   * Express this fraction as a double.
   *
   * @return the fraction approxiamted as a double.
   */
  public double doubleValue() {
    return this.num.doubleValue() / this.denom.doubleValue();
  } // doubleValue()

  /**
   * Simplify this fraction.
   *
   * @return the result of the simplification.
   */
  public BigFraction simplify() {
    BigInteger resultNumerator;
    BigInteger resultDenominator;

    // Multiply the numerator by the sign of the denominator.
    this.num = this.num.multiply(BigInteger.valueOf(this.denom.signum()));

    // Remove signs from denominator.
    this.denom = this.denom.abs();

    // Find the GCD.
    BigInteger gcd = this.num.gcd(this.denom);

    // Divide the numerator and denominator by the GCD.
    resultNumerator = this.num.divide(gcd);
    resultDenominator = this.denom.divide(gcd);

    // Return the computed value
    return new BigFraction(resultNumerator, resultDenominator);
  } // simplify

  /**
   * Add another fraction to this fraction.
   *
   * @param addend
   *               The fraction to add.
   *
   * @return the result of the addition.
   */
  public BigFraction add(BigFraction addend) {
    BigInteger resultNumerator;
    BigInteger resultDenominator;

    // The denominator of the result is the product of this object's
    // denominator and addend's denominator
    resultDenominator = this.denom.multiply(addend.denom);
    // The numerator is more complicated
    resultNumerator = (this.num.multiply(addend.denom)).add(addend.num.multiply(this.denom));

    // Return the computed value
    return new BigFraction(resultNumerator, resultDenominator).simplify();
  } // add(BigFraction)

  /**
   * Subtract another fraction to this fraction.
   *
   * @param sub
   *            The fraction to subtract.
   *
   * @return the result of the subtraction.
   */
  public BigFraction subtract(BigFraction sub) {
    BigInteger resultNumerator;
    BigInteger resultDenominator;

    // The denominator of the result is the product of this object's
    // denominator and addend's denominator
    resultDenominator = this.denom.multiply(sub.denom);
    // The numerator is more complicated
    resultNumerator = (this.num.multiply(sub.denom)).subtract(sub.num.multiply(this.denom));

    // Return the computed value
    return new BigFraction(resultNumerator, resultDenominator).simplify();
  } // subtract(BigFraction)

  /**
   * Multiply another fraction to this fraction.
   *
   * @param multiplier
   *               The fraction to multiply.
   *
   * @return the result of the multiplication.
   */
  public BigFraction multiply(BigFraction multiplier) {
    BigInteger resultNumerator;
    BigInteger resultDenominator;

    // Multiply both numerator and denominator
    resultNumerator = this.num.multiply(multiplier.num);
    resultDenominator = this.denom.multiply(multiplier.denom);

    // Return the computed value
    return new BigFraction(resultNumerator, resultDenominator).simplify();
  } // multiply

  /**
   * Divide another fraction to this fraction.
   *
   * @param multiplier
   *                The fraction to divise.
   *
   * @return the result of the division.
   */
  public BigFraction divide(BigFraction multiplier) {
    BigInteger resultNumerator;
    BigInteger resultDenominator;

    if (multiplier.numerator() == BigInteger.ZERO) {
      return new BigFraction(0, 1);
    } // if

    // Flip divisors num and denom
    // Multiply both numerator and denominator
    resultNumerator = this.num.multiply(multiplier.denom);
    resultDenominator = this.denom.multiply(multiplier.num);

    // Construct new BigFraction
    BigFraction f = new BigFraction(resultNumerator, resultDenominator);
    // Return the computed value
    return f.simplify();
  } // divide

  /**
   * Get the denominator of this fraction.
   *
   * @return the denominator
   */
  public BigInteger denominator() {
    return this.simplify().denom;
  } // denominator()

  /**
   * Get the numerator of this fraction.
   *
   * @return the numerator
   */
  public BigInteger numerator() {
    return this.simplify().num;
  } // numerator()

  /**
   * Convert this fraction to a string for ease of printing.
   *
   * @return a string that represents the fraction.
   */
  public String toString() {
    BigFraction t = this.simplify();
    // Special case: It's zero
    if (t.num.equals(BigInteger.ZERO)) {
      return "0";
    } // if it's zero
    // Special case: whole number
    if (t.denom.equals(BigInteger.ONE)) {
      return t.num + "";
    } // if its a whole num

    // Lump together the string represention of the numerator,
    // a slash, and the string representation of the denominator
    // return this.num.toString().concat("/").concat(this.denom.toString());
    return t.num + "/" + t.denom;
  } // toString()
} // class BigFraction
