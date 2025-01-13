// There are n persons on a social media website. You are given an integer array ages where ages[i] is the age of the ith person.

// A Person x will not send a friend request to a person y (x != y) if any of the following conditions is true:

// age[y] <= 0.5 * age[x] + 7
// age[y] > age[x]
// age[y] > 100 && age[x] < 100
// Otherwise, x will send a friend request to y.

// Note that if x sends a request to y, y will not necessarily send a request to x. Also, a person will not send a friend request to themself.

// Return the total number of friend requests made.

class Solution {
  public int numFriendRequests(int[] ages) {
    int ans = 0;
    int[] count = new int[121];

    for (final int age : ages)
      ++count[age];

    for (int ageA = 1; ageA <= 120; ++ageA)
      for (int ageB = 1; ageB <= 120; ++ageB) {
        final int countA = count[ageA];
        final int countB = count[ageB];
        if (countA > 0 && countB > 0 && request(ageA, ageB))
          if (ageA == ageB)
            ans += countA * (countB - 1);
          else
            ans += countA * countB;
      }

    return ans;
  }

  private boolean request(int ageA, int ageB) {
    return !(ageB <= 0.5 * ageA + 7 || ageB > ageA || ageB > 100 && ageA < 100);
  }
}