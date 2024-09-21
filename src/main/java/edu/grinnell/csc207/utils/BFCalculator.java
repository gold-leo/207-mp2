package edu.grinnell.csc207.utils;

/**
 * Calculator for BigFractions.
*/
public class BFCalculator {

  // +--------+-------------------------------------------------------
  // | Fields |
  // +--------+

  /**
   * the last value calculated by the calculator.
   */
  BigFraction lastVal;

  // +--------------+-------------------------------------------------
  // | Constructors |
  // +--------------+

  /**
   * Constructs a new calculator (lastValue = 0/1).
   */
  public BFCalculator() {
    this.lastVal = new BigFraction(0, 1);
  } // constructor

  // +---------+------------------------------------------------------
  // | Methods |
  // +---------+

  /**
   * gets the last computed value (returns 0 if there is no such value).
   * @return last value
   */
  public BigFraction get() {
    return this.lastVal;
  } // get

  /**
   * sets lastValue to val.
   * @param val
   */
  public void set(BigFraction val) {
    lastVal = val;
  } // set

  /**
   * adds val to the last computed value.
   * @param val
   */
  public void add(BigFraction val) {
    lastVal = lastVal.add(val);
  } // add

  /**
   * subtracts val from the last computed value.
   * @param val
   */
  public void subtract(BigFraction val) {
    lastVal = lastVal.subtract(val);
  } // subtract

  /**
   * multiplies the last computed value by val.
   * @param val
   */
  public void multiply(BigFraction val) {
    lastVal = lastVal.multiply(val);
  }  // multiply

  /**
   * divides the last computed value by val.
   * @param val
   */
  public void divide(BigFraction val) {
    lastVal = lastVal.divide(val);
  } // divide

  /**
   * resets the last computed value to 0.
   */
  public void clear() {
    this.lastVal = new BigFraction(0, 1);
  } // clear
} // class
