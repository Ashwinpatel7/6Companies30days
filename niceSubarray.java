// 1248. Count Number of Nice Subarrays

// Given an array of integers nums and an integer k. A continuous subarray is called nice if there are k odd numbers on it.

// Return the number of nice sub-arrays.




class Solution {
  public int numberOfSubarrays(int[] nums, int k) {
    return numberOfSubarraysAtMost(nums, k) - numberOfSubarraysAtMost(nums, k - 1);
  }

  private int numberOfSubarraysAtMost(int[] nums, int k) {
    int ans = 0;

    for (int l = 0, r = 0; r <= nums.length;)
      if (k >= 0) {
        ans += r - l;
        if (r == nums.length)
          break;
        if (nums[r] % 2 == 1)
          --k;
        ++r;
      } else {
        if (nums[l] % 2 == 1)
          ++k;
        ++l;
      }

    return ans;
  }
}