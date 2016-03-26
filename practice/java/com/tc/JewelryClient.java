package com.tc;

import java.io.IOException;
import java.util.*;

public class JewelryClient {
  public static void main(String[] args) throws java.io.IOException {
    for (SequenceAnswerKey k : SequenceAnswerKeyScanner.scan()) {
      // System.out.println(k);
      Jewelry cs = new Jewelry();
      long howMany = cs.howMany(k.sequence);
      if (howMany != k.aggregate)
        System.out.println(String.format("%s\t%s", k, howMany));    
    }
  }
}
