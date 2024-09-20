package edu.grinnell.csc207.main;

import java.io.PrintWriter;
import java.util.Scanner;

import edu.grinnell.csc207.utils.BFCalculator;
import edu.grinnell.csc207.utils.BFRegisterSet;
import edu.grinnell.csc207.utils.General;

public class InteractiveCalculator {

  public static void main(String[] args) {
    PrintWriter pen = new PrintWriter(System.out, true);
    Scanner eyes = new Scanner(System.in);

    //create a calculator
    BFCalculator calc = new BFCalculator();
    //create a registry
    BFRegisterSet regi = new BFRegisterSet();

    for (;;) {
      pen.printf("> ");
      pen.flush();
      String str = eyes.nextLine();

      if (str.equals("QUIT")) {
        eyes.close();
        return;
      }

      General.process_str(str, calc, regi, pen);
    } // command loop
  } // main
}
