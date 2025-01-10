// You are given an m x n grid where each cell can have one of three values:

// 0 representing an empty cell,
// 1 representing a fresh orange, or
// 2 representing a rotten orange.
// Every minute, any fresh orange that is 4-directionally adjacent to a rotten orange becomes rotten.

// Return the minimum number of minutes that must elapse until no cell has a fresh orange. If this is impossible, return -1.

class Solution {
    public int orangesRotting(int[][] grid) {
        int rows = grid.length, cols = grid[0].length;
        int freshOranges = 0; // Counter for fresh oranges
        Deque<int[]> queue = new LinkedList<>(); // Queue for rotten oranges' positions
      
        // Preprocess the grid, enqueue all rotten oranges and count the fresh ones
        for (int i = 0; i < rows; ++i) {
            for (int j = 0; j < cols; ++j) {
                if (grid[i][j] == 2) { // If orange is rotten
                    queue.offer(new int[] {i, j});
                } else if (grid[i][j] == 1) { // If orange is fresh
                    freshOranges++;
                }
            }
        }
      
        int minutesElapsed = 0; // Time counter for rotting process
        int[] directions = {1, 0, -1, 0, 1}; // Directions for adjacent cells: right, down, left, up
      
        // Start BFS from initially rotten oranges
        while (!queue.isEmpty() && freshOranges > 0) {
            minutesElapsed++; // Increase time since each level of BFS represents 1 minute
            for (int i = queue.size(); i > 0; --i) { // Iterate over rotten oranges at current time
                int[] position = queue.poll();
                for (int j = 0; j < 4; ++j) { // Check all adjacent cells
                    int x = position[0] + directions[j]; 
                    int y = position[1] + directions[j + 1];
                    // If adjacent cell is within bounds and has a fresh orange
                    if (x >= 0 && x < rows && y >= 0 && y < cols && grid[x][y] == 1) {
                        grid[x][y] = 2; // Rot the fresh orange
                        freshOranges--; // Decrement the fresh orange count
                        queue.offer(new int[] {x, y}); // Enqueue the new rotten orange's position
                    }
                }
            }
        }
      
        // If there are still fresh oranges left, return -1, otherwise return elapsed time
        return freshOranges > 0 ? -1 : minutesElapsed;
    }
}
 