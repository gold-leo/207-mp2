package edu.grinnell.csc207.main;

import java.io.PrintWriter;

import edu.grinnell.csc207.utils.BFCalculator;
import edu.grinnell.csc207.utils.BFRegisterSet;
import edu.grinnell.csc207.utils.General;

public class QuickCalculator {
    public static void main(String[] args) {
      PrintWriter pen = new PrintWriter(System.out, true);

      //create a calculator
      BFCalculator calc = new BFCalculator();
      //create a registry
      BFRegisterSet regi = new BFRegisterSet();

      for (String str : args) {
        General.process_str(str, calc, regi, pen);
      }
    }
}
