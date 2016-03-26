package com.tc;

public class AddElectricalWiresAnswerKey {
  String[] wires;
  int[] gridConnections;
  int maxNewWires;

  public AddElectricalWiresAnswerKey(String[] wires, int[] gridConnections, int maxNewWires) {
    this.wires = wires;
    this.gridConnections = gridConnections;
    this.maxNewWires = maxNewWires;
  }

  public String toString() {
    StringBuilder sb = new StringBuilder(300);
    sb.append("{");
    for (String w : wires)
      sb.append(String.format("%s,", w));
    sb.append("}\t");
    sb.append("{");
    for (int c : gridConnections)
      sb.append(String.format("%s,", c));
    sb.append("}");
    sb.append(String.format("\t%s", maxNewWires));
    return sb.toString();
  }
}
