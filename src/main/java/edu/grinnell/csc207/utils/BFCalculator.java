package edu.grinnell.csc207.utils;

public class BFCalculator {

  // +--------+-------------------------------------------------------
  // | Fields |
  // +--------+

  BigFraction lastVal;

  // +--------------+-------------------------------------------------
  // | Constructors |
  // +--------------+

  public BFCalculator()
  {
    this.lastVal = new BigFraction(0, 1);
  }

  // +---------+------------------------------------------------------
  // | Methods |
  // +---------+

  //gets the last computed value (returns 0 if there is no such value).
  public BigFraction get() {
    return this.lastVal;
  }

  public void set(BigFraction val) {
    lastVal = val;
  }

  //adds val to the last computed value.
  public void add(BigFraction val) {
    lastVal = lastVal.add(val);
  }
  //subtracts val from the last computed value.
  public void subtract(BigFraction val) {
    lastVal = lastVal.subtract(val);
  }

  //multiplies the last computed value by val.
  public void multiply(BigFraction val) {
    lastVal = lastVal.multiply(val);
  }

  //divides the last computed value by val.
  public void divide(BigFraction val) {
    lastVal = lastVal.divide(val);
  }

  //resets the last computed value to 0.
  public void clear() {
    this.lastVal = new BigFraction(0, 1);
  }

    
}
