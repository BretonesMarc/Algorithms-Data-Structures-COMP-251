public class MainPart2 {
    public static void main(String[] args) {
        // General case
        int[][] grid1 = {
                {1, 2, 3},    // row 1
                {4, 5, 6},    // row 2
                {7, 8, 9}     // row 3
        };
        int result1 = ShortestPath.findShortestPath(grid1);
        System.out.println("General case result: " + result1); // Output: 21

        // Edge case 1: Single row
        int[][] grid2 = {
                {1, 2, 3}     // row 1
        };
        int result2 = ShortestPath.findShortestPath(grid2);
        System.out.println("Edge case 1 result: " + result2); // Output: 6

        // Edge case 2: Single column
        int[][] grid3 = {
                {1},          // row 1
                {2},          // row 2
                {3}           // row 3
        };
        int result3 = ShortestPath.findShortestPath(grid3);
        System.out.println("Edge case 2 result: " + result3); // Output: 6

        // Edge case 3: Negative values
        int[][] grid4 = {
                {1, -2, 3},   // row 1
                {4, -5, 6},   // row 2
                {7,  8, 9}    // row 3
        };
        int result4 = ShortestPath.findShortestPath(grid4);
        System.out.println("Edge case 3 result: " + result4); // Output: 9
    }
}
