package practice.dfsAndBfs

class PathSum {
    /*
    Brute Force — Recursive DFS
        Approach:

        Recursively subtract node value from targetSum
        At leaf node check if remaining sum is 0
        Explore both left and right subtrees
     */
    fun hasPathSumBruteForce(node: TreeNode?, targetSum: Int): Boolean {
        if (node == null) return false
        // left node check
        if (node.left == null && node.right == null)
            return targetSum == node.`val`

        val remaining = targetSum - node.`val`
        return hasPathSumBruteForce(node.left, remaining) ||
                hasPathSumBruteForce(node.right, remaining)
    }
    /*
    Time  -> O(N) every node visited once
    Space -> O(H) recursion stack
                  O(log N) balanced tree
                  O(N) skewed tree worst case
     */

    /*
    Optimal — Iterative DFS Explicit Stack
        Approach:

        Use explicit stack storing (node, remainingSum) pairs
        No recursion stack risk
        Process nodes iteratively
     */

    fun hasPathSumOptimal(root: TreeNode?, targetSum: Int): Boolean {
        if (root == null) return false

        val stack = ArrayDeque<Pair<TreeNode, Int>>()
        stack.addLast(Pair(root, targetSum))

        while (stack.isNotEmpty()) {
            val (node, remaining) = stack.removeLast()
            val current = remaining - node.`val`
            // left node check
            if (node.left == null && node.right == null && current==0)
                return true

            node.left?.let { stack.addLast(Pair(it, current)) }
            node.right?.let { stack.addLast(Pair(it, current)) }
        }
        return false
    }

    /*
    Time  -> O(N) every node visited once
Space -> O(H) stack height
        O(log N) balanced tree
        O(N) skewed tree worst case
     */
}

/*
                    Recursive DFS        Iterative DFS
Approach     Recursive subtract   Explicit stack pairs
Time                O(N)                 O(N)
Space               O(H) call stack      O(H) explicit stack
Stack overflow  Yes on deep tree     No
Interview use   Mention first        Code this
 */

/*
1. Problem = root to leaf path sum check
2. Key insight -> subtract node value at each step
3. At leaf -> check if remaining == 0
4. Brute force -> recursive DFS, clean but stack overflow risk
5. Optimal -> iterative DFS with explicit (node, remainingSum) stack
6. Critical -> leaf check must be node.left == null && node.right == null
               not just node == null
 */