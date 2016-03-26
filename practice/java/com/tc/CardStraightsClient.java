import com.aldwin.utils.IToPConverter;
import java.io.IOException;
import java.util.*;

public class CardStraightsClient {
  public static void main(String[] args) throws java.io.IOException {
    final int SPACE = 32;
    final int COMMA = 44;
    final int TAB = 9;
    final int LF = 10;
    final int CR = 13;
    final int DECIMAL_ZERO = 48;
    final int DECIMAL_NINE = 57;
    
    List<Integer> list = new ArrayList<Integer>();
    Deque<Integer> digits = new ArrayDeque<Integer>();
    List<CardStraightsAnswerKey> keys = new ArrayList<CardStraightsAnswerKey>();
    int ch, num = 0, prevDelim = 0, straight = 0;

    while ((ch = System.in.read()) != -1) {
      if (ch != SPACE && 
          ch != COMMA && 
          ch != TAB && 
          ch != LF && 
          ch != CR && 
          (ch < DECIMAL_ZERO || ch > DECIMAL_NINE)) {
        System.out.println(String.format("Invalid argument '%s'. Integers only.", ch));
        System.exit(1);
      }

      if (ch >= DECIMAL_ZERO && ch <= DECIMAL_NINE) {
          digits.add(ch);
      } else if (ch == SPACE || ch == COMMA || ch == TAB || ch == CR || ch == LF) {
        //System.out.println(String.format("ch = %s", ch));
        if (digits.size() > 0) {
          num = buildInt(digits);
          if (ch == SPACE || ch == COMMA || ch == TAB)
            list.add(num);
          else {
            //System.out.println(String.format("prevDelim = %s", prevDelim));
            if (prevDelim == TAB) straight = num;
            else list.add(num);
            keys.add(new CardStraightsAnswerKey(IToPConverter.convert(list), straight));
            list = new ArrayList<Integer>();
          }
        }
        prevDelim = ch;
      } 
    }

    for (CardStraightsAnswerKey k : keys) {
      //System.out.println(k);
      CardStraights cs = new CardStraights();
      int longest = cs.longestStraight(k.cards);
      if (longest != k.longestStraight)
        System.out.println(String.format("FAILED: %s\t%s", k, longest));    
    }

  }

  private static int buildInt(Deque<Integer> d) {
    final int DECIMAL_ZERO = 48;
    int num = 0, place = d.size() - 1;
    while (d.size() > 0)
      num += (d.poll() - DECIMAL_ZERO)*Math.pow(10, place--);
    return num;
  }
}
