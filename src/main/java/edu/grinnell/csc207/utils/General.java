package edu.grinnell.csc207.utils;

import java.io.PrintWriter;
import java.math.BigInteger;

/**
 * Class for general utils.
 * 
 * @author leo goldman
 */
public class General {

  // +-------+--------------------------------------------------------
  // | TOOLS |
  // +-------+

  /**
   * Turns a char into an integer.
   * @param letter
   * @return int
   */
  public static int l2i(char letter) {
    return (int) letter - (int) 'a';
  } // l2i

  /**
   * Turns an int into a letter.
   * @param i
   * @return letter
   */
  public static char i2l(int i) {
    return (char) (i + (int) 'a');
  } // i2l

  // +------------+---------------------------------------------------
  // | CALCULATOR |
  // +------------+

  /** */
  private static final int NONE = -1;
  /** */
  private static final int NUM = 0;
  /** */
  private static final int ADD_NUM = 1;
  /** */
  private static final int SUB_NUM = 2;
  /** */
  private static final int MUL_NUM = 3;
  /** */
  private static final int DIV_NUM = 4;
  /** */
  private static final int STORE_NUM = 5;
  /** */
  private static final int ERR = 6;

  /**
   * Process the string command given.
   * @param str  - command given
   * @param calc - the claculator to use
   * @param regi - the register to use
   * @param p  - the pen for printing
   */
  public static void processStr(String str, BFCalculator calc, BFRegisterSet regi, PrintWriter p) {
    BFCalculator cal = new BFCalculator();

    int prevCmd = NONE;

    String[] cmds = str.split(" ");

    parse: for (String s : cmds) {
      if (s.length() == 0) {
        continue;
      } // if
      if (prevCmd == NUM) {
        if (s.equals("+")) {
          prevCmd = ADD_NUM;
        } else if (s.equals("-")) {
          prevCmd = SUB_NUM;
        } else if (s.equals("*")) {
          prevCmd = MUL_NUM;
        } else if (s.equals("/")) {
          prevCmd = DIV_NUM;
        } else {
          System.err.println("ERROR: Lacking a mathematical constructor");
          prevCmd = ERR;
          break parse;
        } // checks what math constructor cmd is
      } else if (prevCmd == STORE_NUM) {
        if (BFRegisterSet.validRegister(s)) {
          regi.store(s.charAt(0), calc.get());
          p.println("STORED " + s + " -> " + calc.get().toString());
        } else {
          System.err.printf("ERROR: STORE command received invalid register: ");
          System.err.printf(s + " (register ignored)\n");
        } // else
      } else {
        // Check if s is a STORE command.
        if (s.equals("STORE") && prevCmd == NONE) {
          prevCmd = STORE_NUM;
        } else {
          BigFraction f;
          // Check if s is a valid register.
          if (BFRegisterSet.validRegister(s)) {
            f = regi.get(s.charAt(0));
          } else {
            f = new BigFraction(s);
          } // else

          if (f.denominator() == BigInteger.ZERO) {
            System.err.println("ERROR: Invalid expression");
            prevCmd = ERR;
            break parse;
          } // if

          switch (prevCmd) {
            case NONE:
              cal.set(f);
              prevCmd = NUM;
              break;
            case ADD_NUM:
              cal.add(f);
              prevCmd = NUM;
              break;
            case SUB_NUM:
              cal.subtract(f);
              prevCmd = NUM;
              break;
            case MUL_NUM:
              cal.multiply(f);
              prevCmd = NUM;
              break;
            case DIV_NUM:
              cal.divide(f);
              prevCmd = NUM;
              break;
            default:
              System.err.println("ERROR: This is a very bad sign");
              prevCmd = ERR;
              break parse;
          } // switch
        } // else
      } // else
    } // string parse
    if (prevCmd == NUM) {
      p.println(cal.get().toString());
      calc.set(cal.get());
    } else if (prevCmd < 5 && prevCmd > 0 || prevCmd == NONE) {
      System.err.println("ERROR: Invalid expression");
    } // else if
  } // processStr
} // class
