package com.tc;

import com.aldwin.utils.IToPConverter;
import java.util.*;

public class AddElectricalWiresClient {
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
    
    List<Integer> list_i = new ArrayList<Integer>();
    List<String> list_s = new ArrayList<String>();
    Deque<Integer> digits = new ArrayDeque<Integer>();
    List<AddElectricalWiresAnswerKey> keys = new ArrayList<AddElectricalWiresAnswerKey>();
    StringBuilder sb = new StringBuilder(100);
    int ch
      , num = 0
      , prevChar = 0
      , paramCtr = 0;

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
        if (paramCtr == 1) sb.append(ch-DECIMAL_ZERO);
        else digits.add(ch);
      } else if (ch == LEFT_BRACE) 
        paramCtr++;
      else if (ch == COMMA || ch == RIGHT_BRACE) {
        if (ch == COMMA && prevChar == RIGHT_BRACE) continue;
        if (paramCtr == 1) { 
          list_s.add(sb.toString());
          sb = new StringBuilder(100);
        } else {
          num = buildInt(digits);
          list_i.add(num); 
        }
      } else if (ch == CR || ch == LF) {
        if (digits.size() > 0) {
          num = buildInt(digits);
          String[] array_s = new String[list_s.size()];
          array_s = list_s.toArray(array_s);
          keys.add(new AddElectricalWiresAnswerKey(array_s, IToPConverter.convert(list_i), num));
          list_s = new ArrayList<String>();
          list_i = new ArrayList<Integer>();
          paramCtr = 0;
          sb = new StringBuilder(100);
        }
      }
      prevChar = ch;
    }

    for (AddElectricalWiresAnswerKey k : keys) {
      AddElectricalWires w = new AddElectricalWires();
      int max = w.maxNewWires(k.wires, k.gridConnections);
      if (k.maxNewWires != max) {
        System.out.println(String.format("%s\t%s", k, max));
        break;
      }
    }
      //System.out.println(k);

    // String[] wires = args[0].split(",");
    // String[] gridStrArr = args[1].split(",");
    // int[] gridIntArr = new int[gridStrArr.length];
    // for (int i = 0; i < gridStrArr.length; i++)
    //   gridIntArr[i] = Integer.parseInt(gridStrArr[i]);

    // System.out.println(String.format("Wires: %s", args[0]));
    // System.out.println(String.format("Grid: %s", args[1]));
    // AddElectricalWires w = new AddElectricalWires();
    // System.out.println(String.format("max: %s", w.maxNewWires(wires, gridIntArr)));
  }

  private static int buildInt(Deque<Integer> d) {
    final int DECIMAL_ZERO = 48;
    int num = 0, place = d.size() - 1;
    while (d.size() > 0)
      num += (d.poll() - DECIMAL_ZERO)*Math.pow(10, place--);
    return num;
  }
}
