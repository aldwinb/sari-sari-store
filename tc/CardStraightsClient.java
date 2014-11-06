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
    Deque<Integer> stack = new ArrayDeque<Integer>();
    int ch, num = 0, place = 0;

    while ((ch = System.in.read()) != -1) {
      if (ch != SPACE && ch != COMMA && ch != TAB && ch != LF && ch != CR && (ch < DECIMAL_ZERO || ch > DECIMAL_NINE)) {
        System.out.println(String.format("Invalid argument '%s'. Integers only.", ch));
        System.exit(1);
      }

      //System.out.println(ch);
      if (ch >= DECIMAL_ZERO && ch <= DECIMAL_NINE) {
        stack.add(ch);
      } else {
        if (stack.size() == 0) continue;
        place = stack.size() - 1;
        while (stack.size() > 0)
          num += (stack.poll() - DECIMAL_ZERO)*Math.pow(10, place--);
        list.add(num);
        num = 0;
        place = 0;
      }
    }

    // place = stack.size() - 1;
    // while (stack.size() > 0)
    //   num += (stack.poll() - DECIMAL_ZERO)*Math.pow(10, place--);
    // list.add(num);

    // System.out.print("Hand:");
    // int[] arr = IToPConverter.convert(list);
    // for (int i : arr)
    //   System.out.print(String.format(" %s",i));

    // CardStraights cs = new CardStraights();
    // System.out.println("");
    // System.out.println(String.format("Longest straight: %s", cs.longestStraight(arr)));
  }
}
