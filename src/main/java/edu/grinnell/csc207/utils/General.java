package edu.grinnell.csc207.utils;

import java.io.PrintWriter;
import java.math.BigInteger;

public class General {

  // +-------+--------------------------------------------------------
  // | TOOLS |
  // +-------+
  public static int l2i(char letter) {
    return (int) letter - (int) 'a';
  }

  public static char i2l(int i) {
    return (char) (i + (int) 'a');
  }

  // +------------+---------------------------------------------------
  // | CALCULATOR |
  // +------------+

  private static final int NONE = -1;
  private static final int NUM = 0;
  private static final int ADD_NUM = 1;
  private static final int SUB_NUM = 2;
  private static final int MUL_NUM = 3;
  private static final int DIV_NUM = 4;
  private static final int STORE_NUM = 5;
  private static final int ERR = 6;

  public static void process_str(String str, BFCalculator calc, BFRegisterSet regi, PrintWriter pen) {
    BFCalculator cal = new BFCalculator();

    int prev_cmd = NONE;

    String[] cmds = str.split(" ");
    
    parse: for (String s : cmds) {
      if (s.length() == 0) {
        continue;
      }
      if (prev_cmd == NUM) {
        if (s.equals("+")) {
          prev_cmd = ADD_NUM;
        }
        else if (s.equals("-")) {
          prev_cmd = SUB_NUM;
        }
        else if (s.equals("*")) {
          prev_cmd = MUL_NUM;
        }
        else if (s.equals("/")) {
          prev_cmd = DIV_NUM;
        }
        else {
          System.err.println("ERROR: Lacking a mathematical constructor");
          prev_cmd = ERR;
          break parse;
        }
      }
      else if (prev_cmd == STORE_NUM) {
        if (BFRegisterSet.valid_register(s)) {
          regi.store(s.charAt(0), calc.get());
          pen.println("STORED " + s + " -> " + calc.get().toString());
        }
        else {
          System.err.println("ERROR: STORE command received invalid register: " + s + " (register ignored)");
        }
      }
      else {
        // Check if s is a STORE command.
        if (s.equals("STORE") && prev_cmd == NONE) {
          prev_cmd = STORE_NUM;
        }
        else {
          BigFraction f;
          // Check if s is a valid register.
          if (BFRegisterSet.valid_register(s)) {
            f = regi.get(s.charAt(0));
          }
          else {
            f = new BigFraction(s);
          }
          
          if (f.denominator() == BigInteger.ZERO) {
            System.err.println("ERROR: Invalid expression");
            prev_cmd = ERR;
            break parse;
          }

          f = f.simplify();
          switch (prev_cmd) {
            case NONE:
              cal.set(f);
              prev_cmd = NUM;
              break;
            case ADD_NUM:
              cal.add(f);
              prev_cmd = NUM;
              break;
            case SUB_NUM:
              cal.subtract(f);
              prev_cmd = NUM;
              break;
            case MUL_NUM:
              cal.multiply(f);
              prev_cmd = NUM;
              break;
            case DIV_NUM:
              cal.divide(f);
              prev_cmd = NUM;
              break;
            default:
              System.err.println("ERROR: This is a very bad sign");
              prev_cmd = ERR;
              break parse;
          }
          //pen.printf(f.numerator().toString() + "/" + f.denominator().toString() + " ");
        }
      }
    } // string parse
    if (prev_cmd == NUM) {
      pen.println(cal.get().toString());
      calc.set(cal.get());
    }
    else if (prev_cmd < 5 && prev_cmd > 0) {
      System.err.println("ERROR: Invalid expression");
    }
  }
}