import java.util.*;

class Solution55 {
  public boolean canJump(int[] nums) {
    if (nums.length == 0)
      return true;

    boolean[] visited = new boolean[nums.length];
    Arrays.fill(visited, false);

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

      if (nums[current] == 0)
        visited[current] = true;
      
      //System.out.println(String.format("current = %s, visited = %s, idxStack = %s", current, visited[current], Arrays.toString(idxStack.toArray())));
      if (visited[current]) 
        current = getIndex(idxStack, visited, current);
      else
        current = getIndex(idxStack, visited, current, nums[current]);
    }

    return reached;
  }

  private int getIndex(Deque<Integer> idxStack, boolean[] visited, int current) {
    return getIndex(idxStack, visited, current, 0);
  }

  private int getIndex(Deque<Integer> idxStack, boolean[] visited, int current, int jump) {
    if (jump == 0) 
      while (jump == 0 && !idxStack.isEmpty()) {
        jump = idxStack.pop()-1;
        current--;
        if (jump == 0 && !idxStack.isEmpty())
          visited[current] = true;
      }
    else
      current += jump;

    if (jump > 0)
      idxStack.push(jump);
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
