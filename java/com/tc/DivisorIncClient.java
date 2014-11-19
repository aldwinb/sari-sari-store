package com.tc;

import java.io.IOException;
import java.util.*;

public class DivisorIncClient {
  public static void main(String[] args) throws java.io.IOException {
    final int SPACE = 32;
    final int COMMA = 44;
    final int TAB = 9;
    final int LF = 10;
    final int CR = 13;
    final int DECIMAL_ZERO = 48;
    final int DECIMAL_NINE = 57;
    
    Deque<Integer> digits = new ArrayDeque<Integer>();
    List<DivisorIncAnswerKey> keys = new ArrayList<DivisorIncAnswerKey>();
    int ch, num = 0, N = 0, M = 0;

    while ((ch = System.in.read()) != -1) {
      if (ch != COMMA && 
          ch != TAB && 
          ch != LF && 
          ch != CR && 
          (ch < DECIMAL_ZERO || ch > DECIMAL_NINE)) {
        System.out.println(String.format("Invalid argument '%s'. Integers only.", ch));
        System.exit(1);
      }

      if (ch >= DECIMAL_ZERO && ch <= DECIMAL_NINE) {
          digits.add(ch);
      } else if (ch == COMMA || ch == TAB || ch == CR || ch == LF) {
        if (digits.size() > 0) {
          num = buildInt(digits);
          if (ch == COMMA)
            N = num;
          else if (ch == TAB)
            M = num;
          else {
            keys.add(new DivisorIncAnswerKey(N, M, num));
            N = M = 0;
          }
        }
      } 
    }

    for (DivisorIncAnswerKey k : keys) {
      DivisorInc cs = new DivisorInc();
      int countOps = cs.countOperations(k.N, k.M);
      if (countOps != k.countOperations)
        System.out.println(String.format("%s\t%s", k, countOps));    
    }

  }

  private static int buildInt(Deque<Integer> d) {
    final int DECIMAL_ZERO = 48;
    int num = 0, place = d.size() - 1;
    while (d.size() > 0)
      num += (d.poll() - DECIMAL_ZERO)*Math.pow(10, place--);
    return num;
  }
}
