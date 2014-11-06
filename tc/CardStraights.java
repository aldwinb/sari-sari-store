import java.util.TreeSet;

public class CardStraights {
  public int longestStraight(int[] cards) {
    TreeSet<Integer> set = new TreeSet<Integer>();
    int jokers = 0;

    for (int i : cards) {
      if (i == 0) jokers++;
      else set.add(i);
    }

    int[] cardArray = new int[set.size()];
    int i = 0;
    for (Integer s : set)
      cardArray[i++] = s;

    int lo = 0
      , hi = 0
      , j = jokers
      , longest = 0
      , diff = 0;

    if (cardArray.length > 0 )
      hi = 1;

    while (hi < cardArray.length) {
      diff = cardArray[hi] - (cardArray[hi-1]+1);
      //System.out.println(String.format("lo = %s, hi = %s, j = %s, cardArray[hi] = %s, cardArray[hi-1] = %s,  diff = %s", lo,  hi, j, cardArray[hi], cardArray[hi-1],  diff));
      if (diff > 0) {
        if (diff <= j)
          j -= diff;
        else {
          longest = Math.max(longest, hi-lo);
          lo = hi;
          j = jokers;
        }
      }
      hi++;
    }

    return Math.max(longest, hi-lo) + jokers;
  }
}
