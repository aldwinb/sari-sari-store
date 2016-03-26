package com.tc;

public class DivisorIncAnswerKey {
  int N;
  int M;
  int countOperations;

  public DivisorIncAnswerKey(int N, int M, int countOperations) {
    this.N = N;
    this.M = M;
    this.countOperations = countOperations;
  }

  public String toString() {
    StringBuilder sb = new StringBuilder(300);
    sb.append(String.format("%s,%s\t%s", N, M, countOperations));
    return sb.toString();
  }
}
