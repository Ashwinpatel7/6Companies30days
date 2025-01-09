// 3154. Find Number of Ways to Reach the K-th Stair
// Hard
// Topics
// Companies
// Hint
// You are given a non-negative integer k. There exists a staircase with an infinite number of stairs, with the lowest stair numbered 0.

// Alice has an integer jump, with an initial value of 0. She starts on stair 1 and wants to reach stair k using any number of operations. If she is on stair i, in one operation she can:

// Go down to stair i - 1. This operation cannot be used consecutively or on stair 0.
// Go up to stair i + 2jump. And then, jump becomes jump + 1.
// Return the total number of ways Alice can reach stair k.

// Note that it is possible that Alice reaches the stair k, and performs some operations to reach the stair k again.

 class Solution {
  public int waysToReachStair(int k) {
    // Let's say we have `down` operation 1 and `jump` operation 2.
    // The final stair is 1 + (2^0 + 2^1 + ... + 2^(jump - 1)) - down = k.
    // => 1 + (2^jump - 1) - down = k.
    // => down = 2^jump - k.
    // Since `down` operations cannot be used consecutively, there're jump + 1
    // positions (before and after each `jump`) for  `down`. The maximum jump is
    // 29, as it satisfies the condition down = 2^jump - k <= jump + 1, with k
    // being the maximum value of 10^9.
    final int kMaxJump = 29;
    final int[][] comb = getComb(kMaxJump + 1, kMaxJump + 1);
    int ans = 0;

    for (int jump = 0; jump <= kMaxJump; ++jump) {
      final int down = (1 << jump) - k;
      if (down < 0 || down > jump + 1)
        continue;
      ans += comb[jump + 1][down];
    }

    return ans;
  }

  // C(n, k) = C(n - 1, k) + C(n - 1, k - 1)
  private int[][] getComb(int n, int k) {
    int[][] comb = new int[n + 1][k + 1];

    for (int i = 0; i <= n; ++i)
      comb[i][0] = 1;
    for (int i = 1; i <= n; ++i)
      for (int j = 1; j <= k; ++j)
        comb[i][j] = comb[i - 1][j] + comb[i - 1][j - 1];
    return comb;
  }
}