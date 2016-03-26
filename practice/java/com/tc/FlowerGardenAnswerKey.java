package com.tc;

public class FlowerGardenAnswerKey {
  public int[] height;
  public int[] bloom;
  public int[] wilt;
  public int[] order;

  public FlowerGardenAnswerKey(int[] height, int[] bloom, int[] wilt, int[] order) {
    this.height = height;
    this.bloom = bloom;
    this.wilt = wilt;
    this.order = order;
  }

  public String toString() {
    StringBuilder sb = new StringBuilder(300);
    sb.append("{");
    for (int w : height)
      sb.append(String.format("%s,", w));
    sb.append("},");
    sb.append("{");
    for (int c : bloom)
      sb.append(String.format("%s,", c));
    sb.append("},");
    sb.append("{");
    for (int c : wilt)
      sb.append(String.format("%s,", c));
    sb.append("}\t");
    sb.append("{");
    for (int c : order)
      sb.append(String.format("%s,", c));
    sb.append("}");

    return sb.toString();
  }
}
