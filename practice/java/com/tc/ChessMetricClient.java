package com.tc;

public class ChessMetricClient {
  public static void main(String[] args) {
    int size = Integer.parseInt(args[0]);
    
    String[] startStr = args[1].split(",");
    int[] start = new int[startStr.length];
    int i = 0;
    for (String s : startStr)
      start[i++] = Integer.parseInt(s);
    
    String[] endStr = args[2].split(",");
    int[] end = new int[endStr.length];
    i = 0;
    for (String s : endStr)
      end[i++] = Integer.parseInt(s);

    int numWays = Integer.parseInt(args[3]);

    long howMany = new ChessMetric().howMany(size, start, end, numWays);
    System.out.println(howMany);
  }
}
