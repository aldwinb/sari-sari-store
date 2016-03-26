package com.tc;

import com.aldwin.utils.IToPConverter;
import java.util.*;

public class AvoidRoadsClient {
  public static void main(String[] args) throws java.io.IOException {
    final int SPACE = 32;
    final int COMMA = 44;
    final int TAB = 9;
    final int LF = 10;
    final int CR = 13;
    final int DECIMAL_ZERO = 48;
    final int DECIMAL_NINE = 57;
    final int LEFT_BRACE = 123;
    final int RIGHT_BRACE = 125;
    final int DOUBLE_QUOTE = 34;
    
    int width = 0;
    int height = 0;
    List<Integer> badSingle = new ArrayList<Integer>();
    List<String> bad = new ArrayList<String>();
    Deque<Integer> digits = new ArrayDeque<Integer>();
    List<AvoidRoadsAnswerKey> keys = new ArrayList<AvoidRoadsAnswerKey>();
    int ch, num = 0, paramCtr = 0;

    while ((ch = System.in.read()) != -1) {
      if (ch != SPACE && 
          ch != COMMA && 
          ch != TAB && 
          ch != LF && 
          ch != CR && 
          ch != LEFT_BRACE &&
          ch != RIGHT_BRACE &&
          ch != DOUBLE_QUOTE &&
          (ch < DECIMAL_ZERO || ch > DECIMAL_NINE)) {
        System.out.println(String.format("Invalid argument '%s'. Integers only.", ch));
        System.exit(1);
      }

      if (ch >= DECIMAL_ZERO && ch <= DECIMAL_NINE) {
        digits.add(ch);
      } else if (ch == LEFT_BRACE) 
        paramCtr++;
      else if (ch == COMMA || ch == RIGHT_BRACE) {
        if (paramCtr == 0) {
          if (width == 0) {
            width = IntegerScanner.buildInt(digits);
            continue;
          }
          if (height == 0) {
            height = IntegerScanner.buildInt(digits);
            continue;
          }
        } else {
          if (badSingle.size() == 0) continue;
          StringBuilder sb = new StringBuilder();
          for (Integer b : badSingle) 
            sb.append(String.format("%s ", b));
          sb.deleteCharAt(sb.length()-1);
          bad.add(sb.toString());
          badSingle = new ArrayList<Integer>();
        }
      } else if (ch == SPACE || ch == DOUBLE_QUOTE) {
          if (digits.size() == 0 ) continue; 
          badSingle.add(IntegerScanner.buildInt(digits));
      } else if (ch == CR || ch == LF) {
        if (digits.size() == 0) continue;
        long numWays = IntegerScanner.buildLong(digits);
        keys.add(new AvoidRoadsAnswerKey(width, 
          height,
          IToPConverter.convertString(bad),
          numWays));
        width = 0;
        height = 0;
        badSingle = new ArrayList<Integer>();
        bad = new ArrayList<String>();
        paramCtr = 0;
      }
    }

    for (AvoidRoadsAnswerKey k : keys) {
      AvoidRoads w = new AvoidRoads();
      long numWays = w.numWays(k.width, k.height, k.bad); 
      if (k.numWays != numWays)
        System.out.println(String.format("%s\t%s", k, numWays));
    }
  }

}
