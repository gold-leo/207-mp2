package edu.grinnell.csc207.main;

import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.Scanner;

import edu.grinnell.csc207.utils.BFCalculator;
import edu.grinnell.csc207.utils.BFRegisterSet;
import edu.grinnell.csc207.utils.BigFraction;

public class InteractiveCalculator {

  private static final int NONE = -1;
  private static final int NUM = 0;
  private static final int ADD_NUM = 1;
  private static final int SUB_NUM = 2;
  private static final int MUL_NUM = 3;
  private static final int DIV_NUM = 4;
  private static final int STORE_NUM = 5;

  public static void main(String[] args) {
    PrintWriter pen = new PrintWriter(System.out, true);
    Scanner eyes = new Scanner(System.in);

    //create a calculator
    BFCalculator calc = new BFCalculator();
    //create a registry
    BFRegisterSet regi = new BFRegisterSet();

    for (;;) {
      pen.printf(" > ");
      pen.flush();
      String str = eyes.nextLine();

      BFCalculator cal = new BFCalculator();

      int prev_cmd = NONE;
      if (str.length() == 0) {
        System.err.println("ERROR: Invalid expression");
        continue;
      }
      String[] cmds = str.split(" ");
      
      parse: for (String s : cmds) {

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
            break parse;
          }
        }
        else if (prev_cmd == STORE_NUM) {
          if (BFRegisterSet.valid_register(s)) {
            regi.store(s.charAt(0), calc.get());
            pen.println("STORED");
          }
          else {
            System.err.println("ERROR: STORE command received invalid register");
            break parse;
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
                break parse;
            }
            //pen.printf(f.numerator().toString() + "/" + f.denominator().toString() + " ");
          }
        }
      } // string parse
      calc.set(cal.get()); // set temp calculator to main calculator
      pen.printf(calc.get().toString());
    } // command loop
  } // main
}
