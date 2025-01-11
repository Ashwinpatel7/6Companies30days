// You are given the root of a binary tree with unique values, and an integer start. At minute 0, an infection starts from the node with value start.

// Each minute, a node becomes infected if:

// The node is currently uninfected.
// The node is adjacent to an infected node.
// Return the number of minutes needed for the entire tree to be infected.

class Solution {
    // Adjacency list to represent the graph
    private Map<Integer, List<Integer>> adjacencyList;

    public int amountOfTime(TreeNode root, int start) {
        // Initialize adjacency list
        adjacencyList = new HashMap<>();
        // Convert the binary tree to a graph
        buildGraph(root, null);
        // Perform BFS to find the maximum time
        return bfs(start);
    }

    // BFS to calculate the time needed to infect all nodes
    private int bfs(int start) {
        Deque<Integer> queue = new ArrayDeque<>();
        Set<Integer> visited = new HashSet<>();
        int time = 0;

        // Initialize the BFS queue with the starting node
        queue.offer(start);
        visited.add(start);

        while (!queue.isEmpty()) {
            int size = queue.size();
            boolean foundUnvisited = false;

            // Process all nodes at the current level
            for (int i = 0; i < size; i++) {
                int current = queue.poll();

                // Add unvisited neighbors to the queue
                for (int neighbor : adjacencyList.getOrDefault(current, new ArrayList<>())) {
                    if (visited.add(neighbor)) { // Add returns true if the neighbor is newly visited
                        queue.offer(neighbor);
                        foundUnvisited = true;
                    }
                }
            }

            // Increment time if there are more nodes to visit
            if (foundUnvisited) {
                time++;
            }
        }

        return time;
    }

    // Build the graph representation of the tree using adjacency list
    private void buildGraph(TreeNode node, TreeNode parent) {
        if (node == null) return;

        // Add the edge between the current node and its parent
        if (parent != null) {
            adjacencyList.computeIfAbsent(node.val, k -> new ArrayList<>()).add(parent.val);
            adjacencyList.computeIfAbsent(parent.val, k -> new ArrayList<>()).add(node.val);
        }

        // Recurse for left and right children
        buildGraph(node.left, node);
        buildGraph(node.right, node);
    }
}
