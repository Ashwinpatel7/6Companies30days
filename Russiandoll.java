// You are given a 2D array of integers envelopes where envelopes[i] = [wi, hi] represents the width and the height of an envelope.

// One envelope can fit into another if and only if both the width and height of one envelope are greater than the other envelope's width and height.

// Return the maximum number of envelopes you can Russian doll (i.e., put one inside the other).

// Note: You cannot rotate an envelope.

class Solution {
  public int maxEnvelopes(int[][] envelopes) {
    Arrays.sort(envelopes,
                (a, b) -> a[0] == b[0] ? Integer.compare(b[1], a[1]) : Integer.compare(a[0], b[0]));

    // Same as 300. Longest Increasing Subsequence
    int ans = 0;
    int[] dp = new int[envelopes.length];

    for (int[] e : envelopes) {
      int i = Arrays.binarySearch(dp, 0, ans, e[1]);
      if (i < 0)
        i = -(i + 1);
      dp[i] = e[1];
      if (i == ans)
        ++ans;
    }

    return ans;
  }
}