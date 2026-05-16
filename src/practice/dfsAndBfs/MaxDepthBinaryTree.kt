package practice.dfsAndBfs

class TreeNode(var `val`: Int) {
    var left: TreeNode? = null
    var right: TreeNode? = null
}

class MaxDepthBinaryTree {
    /*
    Brute Force — Recursive DFS
        Approach:

        Recursively calculate depth of left and right subtree
        Return 1 + max of both
        Simple and clean but recursion stack risk on very deep trees
     */
    fun maxDepthBrute(node: TreeNode?): Int {
        if (node == null) return 0
        val leftDepth = maxDepthBrute(node.left)
        val rightDepth = maxDepthBrute(node.right)
        return 1 + maxOf(leftDepth, rightDepth)
    }
    /*
    Time  -> O(N) every node visited once
        Space -> O(H) recursion stack
        O(log N) balanced tree
        O(N) skewed tree worst case
     */

    /*
    Optimal — Iterative BFS Level Order
            Approach:

            Use queue to process nodes level by level
            Each level processed = depth incremented by 1
            No recursion stack risk
            Preferred production approach
     */

    fun maxDepthOptimal(node: TreeNode?): Int {
        if (node == null) return 0
        val queue = ArrayDeque<TreeNode>()
        queue.add(node)
        var depth = 0
        while (queue.isNotEmpty()) {
            val level = queue.size
            repeat(level) {
                val node = queue.removeFirst()
                node.left?.let { queue.add(it) }
                node.right?.let { queue.add(it) }
            }
            depth++
        }
        return depth
    }
    /*
    Time  -> O(N) every node visited once
    Space -> O(W) W = max width of tree
        O(N) worst case at last level of perfect tree
     */
}
