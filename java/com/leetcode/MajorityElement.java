package com.leetcode;

import java.util.*;

public class MajorityElement {
  public int majorityElement(int[] num) {
    Arrays.sort(num);
    int half = num.length / 2;
    int c = 1;
    int i = 0;
    for (i = 1; i < num.length && c <= half; i++, c++) {
      if (num[i-1] != num[i]) c = 0;
    }
    return num[i-1];
  }

  public static void main(String[] args) {
    String[] strNums = args[0].split(",");
    int[] num = new int[strNums.length];
    for (int i = 0; i < strNums.length; i++)
      num[i] = Integer.parseInt(strNums[i]);

    System.out.println(String.format("%s", new MajorityElement().majorityElement(num)));
  }
}
