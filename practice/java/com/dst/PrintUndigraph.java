package com.dst;

import java.util.*;

public class PrintUndigraph {
  public static void main(String[] args) throws java.io.IOException {
    final int SPACE = 32;
    final int COMMA = 44;
    final int TAB = 9;
    final int LF = 10;
    final int CR = 13;
    final int DECIMAL_ZERO = 48;
    final int DECIMAL_NINE = 57;
   
    StringBuilder sb = new StringBuilder(10);
    List<String> list1 = new ArrayList<String>();
    List<String> list2 = new ArrayList<String>();
    int ch;
    
    while ((ch = System.in.read()) != -1) {
      if (ch == TAB || ch == SPACE) {
        list1.add(sb.toString());
        sb = new StringBuilder(10);
      } else if (ch == CR || ch == LF) {
        list2.add(sb.toString());
        sb = new StringBuilder(10);
      } else
        sb.append((char)ch);
    }

    String[] arr1 = new String[list1.size()];
    arr1 = list1.toArray(arr1);
    String[] arr2 = new String[list2.size()];
    arr2 = list2.toArray(arr2);

    Graph g = new Undigraph(arr1, arr2);
    System.out.println("Graph:");
    System.out.println(g);
  }
}
