
package com.tc;

import java.util.*;

public class IntegerScanner {

  public static int buildInt(Deque<Integer> d) {
    final int DECIMAL_ZERO = 48;
    StringBuilder sb = new StringBuilder(d.size());
    while (d.size() > 0)
      sb.append(d.poll()-DECIMAL_ZERO);
    return Integer.parseInt(sb.toString());
  }
  
  public static long buildLong(Deque<Integer> d) {
    final int DECIMAL_ZERO = 48;
    StringBuilder sb = new StringBuilder(d.size());
    while (d.size() > 0)
      sb.append(d.poll()-DECIMAL_ZERO);
    return Long.parseLong(sb.toString());
  }

}
