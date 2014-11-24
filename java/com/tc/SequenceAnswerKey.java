package com.tc;

public class SequenceAnswerKey {
  int[] sequence;
  int aggregate;

  public SequenceAnswerKey(int[] sequence, int aggregate) {
    this.sequence = sequence;
    this.aggregate = aggregate;
  }

  public String toString() {
    StringBuilder sb = new StringBuilder(300);
    for (int c : sequence)
      sb.append(String.format("%s,", c));
    sb.append(String.format("\t%s", aggregate));
    return sb.toString();
  }
}
