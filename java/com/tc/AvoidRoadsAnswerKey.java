package com.tc;

public class AvoidRoadsAnswerKey {
  public int width;
  public int height;
  public String[] bad;
  public long numWays;

  public AvoidRoadsAnswerKey(int width, int height, String[] bad, long numWays) {
    this.width = width;
    this.height = height;
    this.bad = bad;
    this.numWays = numWays;
  }

  public String toString() {
    StringBuilder sb = new StringBuilder(300);
    sb.append(String.format("%s,%s,", width, height));
    sb.append("{");
    for (String c : bad)
      sb.append(String.format("%s, ", c));
    sb.append("}\t");
    sb.append(numWays);

    return sb.toString();
  }
}
