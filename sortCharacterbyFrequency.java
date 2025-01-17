
// Given a string s, sort it in decreasing order based on the frequency of the characters. The frequency of a character is the number of times it appears in the string.

// Return the sorted string. If there are multiple answers, return any of them.

 

// Example 1:

// Input: s = "tree"
// Output: "eert"
// Explanation: 'e' appears twice while 'r' and 't' both appear once.
// So 'e' must appear before both 'r' and 't'. Therefore "eetr" is also a valid answer.
class Solution {
  public String frequencySort(String s) {
    final int n = s.length();
    StringBuilder sb = new StringBuilder();
    int[] count = new int[128];
    // buckets[i] := characters that appear i times in s
    List<Character>[] buckets = new List[n + 1];

    for (final char c : s.toCharArray())
      ++count[c];

    for (int i = 0; i < 128; ++i) {
      final int freq = count[i];
      if (freq > 0) {
        if (buckets[freq] == null)
          buckets[freq] = new ArrayList<>();
        buckets[freq].add((char) i);
      }
    }

    for (int freq = n; freq > 0; --freq)
      if (buckets[freq] != null)
        for (final char c : buckets[freq])
          for (int i = 0; i < freq; ++i)
            sb.append(c);

    return sb.toString();
  }
}