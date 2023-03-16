// Define a class named ShortestPath
public class ShortestPath {
    // Define a static method that takes a 2D array of integers as a parameter and returns an integer
    public static int findShortestPath(int[][] grid) {
        // Get the number of rows in the 2D array
        int n = grid.length;
        // Get the number of columns in the 2D array
        int m = grid[0].length;
        // Define a 2D array named dp to store the shortest path from the start point to each point in the grid
        int[][] dp = new int[n][m];
        // Set the value of the start point as the initial value of dp[0][0]
        dp[0][0] = grid[0][0];
        // Traverse each point in the 2D array
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                // If the current point is not the start point, set its value to the maximum integer value
                if (i > 0 || j > 0) {
                    dp[i][j] = Integer.MAX_VALUE;
                }
                // If the current point is not in the first row, update its value by comparing with the point above it
                if (i > 0) {
                    dp[i][j] = Math.min(dp[i][j], dp[i - 1][j] + grid[i][j]);
                }
                // If the current point is not in the first column, update its value by comparing with the point to its left
                if (j > 0) {
                    dp[i][j] = Math.min(dp[i][j], dp[i][j - 1] + grid[i][j]);
                }
            }
        }
        // Return the shortest path from the start point to the end point
        return dp[n - 1][m - 1];
    }
}