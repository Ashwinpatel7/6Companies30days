// You are given an integer array nums and an integer k. You want to find a subsequence of nums of length k that has the largest sum.

// Return any such subsequence as an integer array of length k.

// A subsequence is an array that can be derived from another array by deleting some or no elements without changing the order of the remaining elements.

class Solution {
  public int[] maxSubsequence(int[] nums, int k) {
    int[] ans = new int[k];
    int[] A = nums.clone();
    Arrays.sort(A);
    final int threshold = A[A.length - k];
    final int larger = (int) Arrays.stream(nums).filter(num -> num > threshold).count();
    int equal = k - larger;

    int i = 0;
    for (final int num : nums)
      if (num > threshold) {
        ans[i++] = num;
      } else if (num == threshold && equal > 0) {
        ans[i++] = num;
        --equal;
      }

    return ans;
  }
}