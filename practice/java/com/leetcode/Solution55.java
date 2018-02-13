import java.util.*;

class Solution55 {
  public boolean canJump(int[] nums) {
    if (nums.length == 0)
      return true;

    int[] visited = new int[nums.length];
    Arrays.fill(visited, -1);

    int current = nums[0],
        end = nums.length-1;
    boolean reached = false;
    Deque<Integer> idxStack = new ArrayDeque<Integer>();
    idxStack.push(current); 
    while (!idxStack.isEmpty() && !reached) {
      
      if (current >= end) {
        reached = true;
        continue;
      }

      System.out.println(String.format("current = %s", current));
      if (nums[current] == 0) 
        current = getIndex(idxStack, current);
      else
        current = getIndex(idxStack, current, nums[current]);
    }

    return reached;
  }

  private int getIndex(Deque<Integer> idxStack, int current) {
    return getIndex(idxStack, 0, 0);
  }

  private int getIndex(Deque<Integer> idxStack, int current, int jump) {
    if (jump == 0) 
      while (current == 0 && !idxStack.isEmpty())
        current = idxStack.pop()-1;
    else
      current += jump;

    System.out.println(String.format("New index: %s", current));
    if (current > 0)
      idxStack.push(current);
    return current;
  }

  public static void main(String[] args) {
    List<List<Integer>> testCases = getTestCasesFromArgs(args);
    Solution55 s = new Solution55();

    for (List<Integer> test : testCases) {
      int[] testArray = test.stream().mapToInt(Integer::intValue).toArray();
      System.out.println(String.format("%s = %s", Arrays.toString(testArray), s.canJump(testArray))); 
    }
  }

  private static List<List<Integer>> getTestCasesFromArgs(String[] args) {
    List<List<Integer>> testCases = new ArrayList<List<Integer>>();

    for (String arg : args) {
      List<Integer> nums = new ArrayList<Integer>();
      for (String num : arg.split(","))
        nums.add(Integer.parseInt(num));

      testCases.add(nums);
    }

    return testCases;
  }
}
