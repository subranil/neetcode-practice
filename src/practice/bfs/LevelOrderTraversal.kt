package practice.bfs

import practice.dfs.TreeNode

class LevelOrderTraversal {
    /*
    Brute Force — BFS with Level Tracking via Map
        Approach:

        Store (node, level) pairs in queue
        Use HashMap to group nodes by level
        Convert map to result list at end
        Extra space for map
     */
    fun levelOrderBruteForce(root: TreeNode?): List<List<Int>> {
        if (root == null) return emptyList()

        val result = mutableMapOf<Int, MutableList<Int>>()
        val queue = ArrayDeque<Pair<TreeNode, Int>>()
        queue.add(Pair(root, 0))


        while (queue.isNotEmpty()) {
            val (node, level) = queue.removeFirst()
            result.getOrPut(level) { mutableListOf() }.add(node.`val`)
            node.left?.let { queue.add(Pair(it, level + 1)) }
            node.right?.let { queue.add(Pair(it, level + 1)) }
        }
        return result.keys.sorted().map { result[it]!! }
    }
    /*
    Time  -> O(N) every node visited once
    Space -> O(N) map + queue
     */
    /*
    Optimal — BFS with levelSize
        Approach:

        Capture queue size at start of each iteration
        Process exactly that many nodes per iteration
        Each iteration = one level
        No extra map needed
     */
    fun levelOrderOptimal(root: TreeNode?): List<List<Int>> {
        if (root == null) return emptyList()
        val result = mutableListOf<List<Int>>()
        val queue = ArrayDeque<TreeNode>()
        queue.add(root)

        while (queue.isNotEmpty()) {
            val level = queue.size
            val currentLevel = mutableListOf<Int>()

            repeat(level) {
                val node = queue.removeFirst()
                currentLevel.add(node.`val`)
                node.left?.let { queue.add(it) }
                node.right?.let { queue.add(it) }
            }
            result.add(currentLevel)
        }
        return result
    }
    /*
    Time  -> O(N) every node visited once
    Space -> O(W) queue width, W = max nodes at any level
                 O(N/2) ~ O(N) worst case at last level of perfect tree
     */
}