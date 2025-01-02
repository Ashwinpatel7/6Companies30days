// An image smoother is a filter of the size 3 x 3 that can be applied to each cell of an image by rounding down the average of the cell and the eight surrounding cells (i.e., the average of the nine cells in the blue smoother). If one or more of the surrounding cells of a cell is not present, we do not consider it in the average (i.e., the average of the four cells in the red smoother).


// Given an m x n integer matrix img representing the grayscale of an image, return the image after applying the smoother on each cell of it.


class Solution {
  public int[][] imageSmoother(int[][] M) {
    final int m = M.length;
    final int n = M[0].length;
    int ans[][] = new int[m][n];

    for (int i = 0; i < m; ++i)
      for (int j = 0; j < n; ++j) {
        int ones = 0;
        int count = 0;
        for (int y = Math.max(0, i - 1); y < Math.min(m, i + 2); ++y)
          for (int x = Math.max(0, j - 1); x < Math.min(n, j + 2); ++x) {
            ones += M[y][x];
            ++count;
          }
        ans[i][j] = ones / count;
      }

    return ans;
  }
}