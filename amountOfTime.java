// You are given the root of a binary tree with unique values, and an integer start. At minute 0, an infection starts from the node with value start.

// Each minute, a node becomes infected if:

// The node is currently uninfected.
// The node is adjacent to an infected node.
// Return the number of minutes needed for the entire tree to be infected.

class Solution {
  public int amountOfTime(TreeNode root, int start) {
    int ans = -1;
    Map<Integer, List<Integer>> graph = getGraph(root);
    Queue<Integer> q = new ArrayDeque<>(List.of(start));
    Set<Integer> seen = new HashSet<>(Arrays.asList(start));

    for (; !q.isEmpty(); ++ans) {
      for (int sz = q.size(); sz > 0; --sz) {
        final int u = q.poll();
        if (!graph.containsKey(u))
          continue;
        for (final int v : graph.get(u)) {
          if (seen.contains(v))
            continue;
          q.offer(v);
          seen.add(v);
        }
      }
    }

    return ans;
  }

  private Map<Integer, List<Integer>> getGraph(TreeNode root) {
    Map<Integer, List<Integer>> graph = new HashMap<>();
    // (node, parent)
    Queue<Pair<TreeNode, Integer>> q = new ArrayDeque<>(List.of(new Pair<>(root, -1)));

    while (!q.isEmpty()) {
      Pair<TreeNode, Integer> pair = q.poll();
      TreeNode node = pair.getKey();
      final int parent = pair.getValue();
      if (parent != -1) {
        graph.putIfAbsent(parent, new ArrayList<>());
        graph.putIfAbsent(node.val, new ArrayList<>());
        graph.get(parent).add(node.val);
        graph.get(node.val).add(parent);
      }
      if (node.left != null)
        q.add(new Pair<>(node.left, node.val));
      if (node.right != null)
        q.add(new Pair<>(node.right, node.val));
    }

    return graph;
  }
}

 