
public class CardStraightsAnswerKey {
  int[] cards;
  int longestStraight;

  public CardStraightsAnswerKey(int[] cards, int longestStraight) {
    this.cards = cards;
    this.longestStraight = longestStraight;
  }

  public String toString() {
    StringBuilder sb = new StringBuilder(300);
    for (int c : cards)
      sb.append(String.format("%s,", c));
    sb.append(String.format("\t%s", longestStraight));
    return sb.toString();
  }
}
