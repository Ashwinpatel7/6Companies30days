// You are given a 0-indexed array of positive integers nums.

// A subarray of nums is called incremovable if nums becomes strictly increasing on removing the subarray. For example, the subarray [3, 4] is an incremovable subarray of [5, 3, 4, 6, 7] because removing this subarray changes the array [5, 3, 4, 6, 7] to [5, 6, 7] which is strictly increasing.

// Return the total number of incremovable subarrays of nums.

// Note that an empty array is considered strictly increasing.

// A subarray is a contiguous non-empty sequence of elements within an array.

class Solution {
  public int incremovableSubarrayCount(int[] nums) {
    final int n = nums.length;
    final int startIndex = getStartIndexOfSuffix(nums);
    // If the complete array is strictly increasing, the total number of ways we
    // can remove elements equals the total number of possible subarrays.
    if (startIndex == 0)
      return n * (n + 1) / 2;

    // The valid removals starting from nums[0] include nums[0..startIndex - 1],
    // nums[0..startIndex], ..., nums[0..n).
    int ans = n - startIndex + 1;

    // Enumerate each prefix subarray that is strictly increasing.
    for (int i = 0; i < startIndex; ++i) {
      if (i > 0 && nums[i] <= nums[i - 1])
        break;
      // Since nums[0..i] is strictly increasing, find the first index j in
      // nums[startIndex..n) such that nums[j] > nums[i]. The valid removals
      // will then be nums[i + 1..j - 1], nums[i + 1..j], ..., nums[i + 1..n).
      ans += n - firstGreater(nums, startIndex, nums[i]) + 1;
    }

    return ans;
  }

  // Returns the start index i of the suffix subarray such that nums[i..n) is
  // strictly increasing.
  private int getStartIndexOfSuffix(int[] nums) {
    for (int i = nums.length - 2; i >= 0; --i)
      if (nums[i] >= nums[i + 1])
        return i + 1;
    return 0;
  }

  private int firstGreater(int[] A, int startIndex, int target) {
    final int i = Arrays.binarySearch(A, startIndex, A.length, target + 1);
    return i < 0 ? -i - 1 : i;
  }
}