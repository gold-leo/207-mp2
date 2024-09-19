package edu.grinnell.csc207.main;

import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.Scanner;

import edu.grinnell.csc207.utils.BFCalculator;
import edu.grinnell.csc207.utils.BFRegisterSet;
import edu.grinnell.csc207.utils.BigFraction;

public class InteractiveCalculator {
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

      String[] cmds = str.split(" ");
      for (String s : cmds) {

        if (s.equals("+")) {
          pen.printf(" plus ");
        }
        else if (s.equals("-")) {
          pen.printf(" minus ");
        }
        else if (s.equals("*")) {
          pen.printf(" times ");
        }
        else if (s.equals("/")) {
          pen.printf(" divided by ");
        }
        else {
          try {
            BigFraction f = new BigFraction(s);
            pen.printf(f.numerator() + "/" + f.denominator());
          }
          catch (Exception e) {
            pen.printf(e.toString());
            pen.printf("\n");
            break;
          }
        }
      }
    }
  }
}
