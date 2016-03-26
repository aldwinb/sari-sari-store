import com.algorithms_4th_ed.sorting.*;

public class ChipArea {

  // fields
  private final double UPPER_BOUND_X = 1.0;
  private final double LOWER_BOUND_X = 0.0;
  private final double UPPER_BOUND_Y = 1.0;
  private final double LOWER_BOUND_Y = 0.0;
  private static int seed = 111;
  private static int randomLimit = 323537;
  private int R;

  public ChipArea() {
    R = 1;  
  }

  // public methods
  public double maxArea(int skip, int n) {
    double maxArea = 0, currentArea = 0;
    skipR(skip, seed);
    Coordinate[] minMax = getMinMaxCoords(n);
    Coordinate min = minMax[0], max = minMax[1]; 
    maxArea = maxArea(new double[] {  
      min.x*UPPER_BOUND_Y,
      min.y*UPPER_BOUND_X,
      (UPPER_BOUND_X-max.x)*UPPER_BOUND_Y,
      (UPPER_BOUND_Y-max.y)*UPPER_BOUND_X 
    });
    return maxArea;
  }

  // private methods
  private double maxArea(Coordinate min, Coordinate max) {
    // mini selection sort here
    int size = a.length;
    double max = a[0];
    for (int i = 1; i < size; i++) {
      if (a[i] > max) max = a[i]; 
    }

    return max;
  }

  public Coordinate[] getMinMaxCoords(int n) {
    int R1 = nextR(), R2 = nextR();
    double x = getPoint(R1), y = getPoint(R2);
    Coordinate min = new Coordinate(x, y)
      , max = new Coordinate(x, y);
    
    for (int i = 2; i < (n*2); i += 2) {
      R1 = nextR();
      R2 = nextR();
      
      x = getPoint(R1); 
      y = getPoint(R2);
      if (x < min.x) min.x = x;
      else if (x > max.x) max.x = x;
      
      if (y < min.y) min.y = y;
      else if (y > max.y) max.y = y;
    }


    System.out.println(String.format("min x, y, max x, y: %s %s %s %s", min.x, min.y, max.x, max.y));
    return new Coordinate[] { min, max };
  }

  private calcMaxArea(Coordinate min, Coordinate max) {
    
  }

  // private double[] getBadPoints(int n) {
  //   double[] badPoints = new double[n*2];
  //   int R1 = 0, R2 = 0;
  //   for (int i = 0; i < (n*2); i += 2) {
  //     
  //     R1 = nextR();
  //     R2 = nextR();
  //     
  //     double x = getPoint(R1);
  //     double y = getPoint(R2);

  //     badPoints[i] = x;
  //     badPoints[i+1] = y;
  //   }

  //   return badPoints;
  // }

  private void skipR(int skip, int R) {
    for (int i = 0; i < skip; i++) {
      nextR();
    }
  }

  private int nextR() {
    R = (seed * R) % randomLimit;
    return R;
  }

  private double getPoint(int R) {
    return R / (randomLimit * 1.0); 
  }

  // MAIN
  public static void main(String[] args) {
    if (args.length < 2)
    {
      System.out.println(String.format("Invalid number of arguments. USAGE: java ChipArea skip n"));
      System.exit(1);
    }
    
    ChipArea c = new ChipArea();
    int skip = Integer.parseInt(args[0])
      , n = Integer.parseInt(args[1]);
    double maxArea = c.maxArea(skip, n);
    System.out.println(String.format("Max area: %s", maxArea));
    //c.skipR(skip, seed);
    //double[] badPoints = c.getBadPoints(n);
    //Coordinate[] minMax = c.getMinMaxCoords(n);
    //System.out.println(String.format("Minimum: (%s, %s)", minMax[0].x, minMax[0].y));
    //System.out.println(String.format("Maximum: (%s, %s)", minMax[1].x, minMax[1].y));
    // for (int i = 0; i < (n*2); i += 2) {
    //   System.out.println(String.format("(%s, %s)", badPoints[i], badPoints[i+1]));
    // }
  }

}
