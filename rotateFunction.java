
// You are given an integer array nums of length n.

// Assume arrk to be an array obtained by rotating nums by k positions clock-wise. We define the rotation function F on nums as follow:

// F(k) = 0 * arrk[0] + 1 * arrk[1] + ... + (n - 1) * arrk[n - 1].
// Return the maximum value of F(0), F(1), ..., F(n-1).

// The test cases are generated so that the answer fits in a 32-bit integer.

class Solution {
  public int maxRotateFunction(int[] nums) {
    final int sum = Arrays.stream(nums).sum();
    int f = 0;

    // Calculate F(0) first.
    for (int i = 0; i < nums.length; ++i)
      f += i * nums[i];

    int ans = f;

    for (int i = nums.length - 1; i >= 0; --i) {
      f += sum - nums.length * nums[i];
      ans = Math.max(ans, f);
    }

    return ans;
  }
}