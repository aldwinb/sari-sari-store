package com.tc;

import com.aldwin.utils.IToPConverter;
import java.util.*;

public class FlowerGardenClient {
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
    
    List<Integer> height = new ArrayList<Integer>();
    List<Integer> bloom = new ArrayList<Integer>();
    List<Integer> wilt = new ArrayList<Integer>();
    List<Integer> order = new ArrayList<Integer>();
    Deque<Integer> digits = new ArrayDeque<Integer>();
    List<FlowerGardenAnswerKey> keys = new ArrayList<FlowerGardenAnswerKey>();
    StringBuilder sb = new StringBuilder(100);
    int ch, num = 0, prevChar = 0, paramCtr = 0;

    while ((ch = System.in.read()) != -1) {
      if (ch != SPACE && 
          ch != COMMA && 
          ch != TAB && 
          ch != LF && 
          ch != CR && 
          ch != LEFT_BRACE &&
          ch != RIGHT_BRACE &&
          (ch < DECIMAL_ZERO || ch > DECIMAL_NINE)) {
        System.out.println(String.format("Invalid argument '%s'. Integers only.", ch));
        System.exit(1);
      }

      if (ch >= DECIMAL_ZERO && ch <= DECIMAL_NINE) {
        digits.add(ch);
      } else if (ch == LEFT_BRACE) 
        paramCtr++;
      else if (ch == COMMA || ch == RIGHT_BRACE) {
        if (ch == COMMA && prevChar == RIGHT_BRACE) continue;
        if (paramCtr == 1) height.add(buildInt(digits)); 
        else if (paramCtr == 2) bloom.add(buildInt(digits));
        else if (paramCtr == 3) wilt.add(buildInt(digits));
        else order.add(buildInt(digits));
      } else if (ch == CR || ch == LF) {
        if (paramCtr > 0) {
          keys.add(new FlowerGardenAnswerKey(IToPConverter.convert(height),
            IToPConverter.convert(bloom),
            IToPConverter.convert(wilt),
            IToPConverter.convert(order)));
          height = new ArrayList<Integer>();
          bloom = new ArrayList<Integer>();
          wilt = new ArrayList<Integer>();
          order = new ArrayList<Integer>();
          paramCtr = 0;
        }
      }
      prevChar = ch;
    }

    for (FlowerGardenAnswerKey k : keys) {
      FlowerGarden w = new FlowerGarden();
      int[] a = w.getOrdering(k.height, k.bloom, k.wilt);
      for (int i = 0; i < a.length; i++) {
        if (k.order[i] != a[i]) {
          System.out.println(String.format("%s\t%s", k, stringify(a)));
          break;
        }
      }
    }
  }

  private static String stringify(int[] a) {
    StringBuilder sb = new StringBuilder(a.length+a.length+1);
    sb.append("{");
    for (int i = 0; i < a.length; i++)
      sb.append(String.format("%s,", a[i]));
    sb.append("}");
    return sb.toString();
  }

  private static int buildInt(Deque<Integer> d) {
    final int DECIMAL_ZERO = 48;
    int num = 0, place = d.size() - 1;
    while (d.size() > 0)
      num += (d.poll() - DECIMAL_ZERO)*Math.pow(10, place--);
    return num;
  }
}
