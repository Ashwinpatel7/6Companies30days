// You may recall that an array arr is a mountain array if and only if:

// arr.length >= 3
// There exists some index i (0-indexed) with 0 < i < arr.length - 1 such that:
// arr[0] < arr[1] < ... < arr[i - 1] < arr[i]
// arr[i] > arr[i + 1] > ... > arr[arr.length - 1]
// Given an integer array arr, return the length of the longest subarray, which is a mountain. Return 0 if there is no mountain subarray.

 class Solution {
  public int longestMountain(int[] arr) {
    int ans = 0;

    for (int i = 0; i + 1 < arr.length;) {
      while (i + 1 < arr.length && arr[i] == arr[i + 1])
        ++i;

      int increasing = 0;
      int decreasing = 0;

      while (i + 1 < arr.length && arr[i] < arr[i + 1]) {
        ++increasing;
        ++i;
      }

      while (i + 1 < arr.length && arr[i] > arr[i + 1]) {
        ++decreasing;
        ++i;
      }

      if (increasing > 0 && decreasing > 0)
        ans = Math.max(ans, increasing + decreasing + 1);
    }

    return ans;
  }
}