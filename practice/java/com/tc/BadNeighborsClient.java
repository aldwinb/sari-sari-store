package com.tc;

import com.aldwin.utils.IToPConverter;
import java.io.IOException;
import java.util.*;

public class BadNeighborsClient {
  public static void main(String[] args) throws java.io.IOException {
    for (SequenceAnswerKey k : SequenceAnswerKeyScanner.scan()) {
      // System.out.println(k);
      BadNeighbors cs = new BadNeighbors();
      int longest = cs.maxDonations(k.sequence);
      if (longest != k.aggregate)
        System.out.println(String.format("%s\t%s", k, longest));    
    }
  }
}
