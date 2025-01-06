
// There are n cities numbered from 0 to n-1. Given the array edges where edges[i] = [fromi, toi, weighti] represents a bidirectional and weighted edge between cities fromi and toi, and given the integer distanceThreshold.

// Return the city with the smallest number of cities that are reachable through some path and whose distance is at most distanceThreshold, If there are multiple such cities, return the city with the greatest number.

// Notice that the distance of a path connecting cities i and j is equal to the sum of the edges' weights along that path.

 class Solution {
  public int findTheCity(int n, int[][] edges, int distanceThreshold) {
    int ans = -1;
    int minCitiesCount = n;
    int[][] dist = floydWarshall(n, edges, distanceThreshold);

    for (int i = 0; i < n; ++i) {
      int citiesCount = 0;
      for (int j = 0; j < n; ++j)
        if (dist[i][j] <= distanceThreshold)
          ++citiesCount;
      if (citiesCount <= minCitiesCount) {
        ans = i;
        minCitiesCount = citiesCount;
      }
    }

    return ans;
  }

  private int[][] floydWarshall(int n, int[][] edges, int distanceThreshold) {
    int[][] dist = new int[n][n];
    Arrays.stream(dist).forEach(A -> Arrays.fill(A, distanceThreshold + 1));

    for (int i = 0; i < n; ++i)
      dist[i][i] = 0;

    for (int[] edge : edges) {
      final int u = edge[0];
      final int v = edge[1];
      final int w = edge[2];
      dist[u][v] = w;
      dist[v][u] = w;
    }

    for (int k = 0; k < n; ++k)
      for (int i = 0; i < n; ++i)
        for (int j = 0; j < n; ++j)
          dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);

    return dist;
  }
}