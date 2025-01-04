// In LeetCode Store, there are n items to sell. Each item has a price. However, there are some special offers, and a special offer consists of one or more different kinds of items with a sale price.

// You are given an integer array price where price[i] is the price of the ith item, and an integer array needs where needs[i] is the number of pieces of the ith item you want to buy.

// You are also given an array special where special[i] is of size n + 1 where special[i][j] is the number of pieces of the jth item in the ith offer and special[i][n] (i.e., the last integer in the array) is the price of the ith offer.

// Return the lowest price you have to pay for exactly certain items as given, where you could make optimal use of the special offers. You are not allowed to buy more items than you want, even if that would lower the overall price. You could use any of the special offers as many times as you want.

class Solution {
  public int shoppingOffers(List<Integer> price, List<List<Integer>> special, List<Integer> needs) {
    return dfs(price, special, needs, 0);
  }

  private int dfs(List<Integer> price, List<List<Integer>> special, List<Integer> needs, int s) {
    int ans = 0;
    for (int i = 0; i < needs.size(); ++i)
      ans += needs.get(i) * price.get(i);

    for (int i = s; i < special.size(); ++i) {
      List<Integer> offer = special.get(i);
      if (isValid(offer, needs)) {
        // Use the special[i].
        for (int j = 0; j < needs.size(); ++j)
          needs.set(j, needs.get(j) - offer.get(j));
        ans = Math.min(ans, offer.get(offer.size() - 1) + dfs(price, special, needs, i));
        // Unuse the special[i] (backtracking).
        for (int j = 0; j < needs.size(); ++j)
          needs.set(j, needs.get(j) + offer.get(j));
      }
    }

    return ans;
  }

  // Returns true if this special offer is a valid one.
  private boolean isValid(List<Integer> offer, List<Integer> needs) {
    for (int i = 0; i < needs.size(); ++i)
      if (offer.get(i) > needs.get(i))
        return false;
    return true;
  }
}