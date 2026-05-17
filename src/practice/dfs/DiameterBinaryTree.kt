package practice.dfs

class DiameterBinaryTree {
    /*
    Brute Force
        Approach:

        For every node calculate height of left and right subtree separately
        Diameter at that node = leftHeight + rightHeight
        Track maximum diameter seen so far
        Redundant height calculations make this O(N²)
     */

    fun diameterOfBinaryTreeBruteForce(root: TreeNode?): Int {
        if (root == null) return 0

        val leftHeight = height(root.left)
        val rightHeight = height(root.right)
        val currentDiameter = leftHeight + rightHeight

        val leftDiameter = diameterOfBinaryTreeBruteForce(root.left)
        val rightDiameter = diameterOfBinaryTreeBruteForce(root.right)
        return maxOf(currentDiameter, leftDiameter, rightDiameter)
    }

    private fun height(c: TreeNode?): Int {
        if (c == null) return 0
        return 1 + maxOf(height(c.left), height(c.right))
    }

    /*
    Time  -> O(N²) height calculated repeatedly for each node
     Space -> O(H) recursion stack
     */
}

class DiameterBinaryTreeOptimal {
    /*
    Optimal — Single Pass DFS
        Approach:
        Calculate height and diameter in single DFS pass
        At each node update global max diameter
        Return height to parent for its calculation
        Eliminates redundant height calculations
     */
    private var maxDiameter: Int = 0
    fun diameterOfBinaryTreeOptimal(root: TreeNode?): Int {
        maxDiameter =0
        calculateHeight(root)
        return maxDiameter
    }
    private fun calculateHeight(node: TreeNode?): Int {
        if (node == null) return 0
        val leftHeight = calculateHeight(node.left)
        val rightHeight = calculateHeight(node.right)

        maxDiameter = maxOf(maxDiameter, leftHeight + rightHeight)
        return 1 + maxOf(leftHeight, rightHeight)

        /*
        Time  -> O(N) single pass, every node visited once
        Space -> O(H) recursion stack
                O(log N) balanced tree
                O(N) skewed tree worst case
         */
    }
}

/*
Brute Force          Optimal
Time            O(N²)                O(N)
Space           O(H)                 O(H)
Height calc     Repeated per node    Single pass
Global max      Passed via return    Class level variable
Interview use   Mention first        Code this
 */

/*
1. Diameter at any node = leftHeight + rightHeight
2. Does not have to pass through root — check every node
3. Brute force -> separate height function called per node -> O(N²)
4. Optimal -> single DFS pass, update global max at each node -> O(N)
5. Return height to parent, update diameter as side effect
6. Reset maxDiameter = 0 at start in case method called multiple times
 */