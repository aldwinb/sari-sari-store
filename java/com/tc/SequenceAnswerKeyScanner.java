package com.tc;

import com.aldwin.utils.IToPConverter;
import java.io.IOException;
import java.util.*;

public class SequenceAnswerKeyScanner {

  public static Iterable<SequenceAnswerKey> scan() throws java.io.IOException {
    final int COMMA = 44;
    final int TAB = 9;
    final int LF = 10;
    final int CR = 13;
    final int DECIMAL_ZERO = 48;
    final int DECIMAL_NINE = 57;
    
    List<Integer> list = new ArrayList<Integer>();
    Deque<Integer> digits = new ArrayDeque<Integer>();
    List<SequenceAnswerKey> keys = new ArrayList<SequenceAnswerKey>();
    int ch, num = 0;

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
      } else if (ch == COMMA || ch == TAB) { 
        num = buildInt(digits);
        list.add(num);
      } else {
        num = buildInt(digits);
        keys.add(new SequenceAnswerKey(IToPConverter.convert(list), num));
        list = new ArrayList<Integer>();
      } 
    }

    return keys;
  }

  private static int buildInt(Deque<Integer> d) {
    final int DECIMAL_ZERO = 48;
    int num = 0, place = d.size() - 1;
    while (d.size() > 0)
      num += (d.poll() - DECIMAL_ZERO)*Math.pow(10, place--);
    return num;
  }
}
