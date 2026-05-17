package practice.bfs

import practice.dfs.TreeNode

fun bfs(root: TreeNode?) {
    if (root == null) return
    val queue = ArrayDeque<TreeNode>()
    queue.add(root)

    while (queue.isNotEmpty()) {
        val node = queue.removeFirst()
        println(node.`val`)
        node.left?.let { queue.add(it) }
        node.right?.let { queue.add(it) }
    }
}

// BFS on Graph
fun bfs(graph: Map<Int, List<Int>>, start: Int) {
    val visited = mutableSetOf<Int>()
    val queue = ArrayDeque<Int>()

    queue.add(start)
    visited.add(start)

    while (queue.isNotEmpty()) {
        val node = queue.removeFirst()
        println(node)
        for (neighbor in graph[node] ?: emptyList()) {
            if (visited.add(neighbor)) {
                queue.add(neighbor)
            }
        }
    }
}

// Level order BFS common pattern

fun bfsLevelOrder(root: TreeNode?) {
    if (root == null) return
    val queue = ArrayDeque<TreeNode>()
    queue.add(root)

    while (queue.isNotEmpty()) {
        val levelSize = queue.size        // capture size before processing
        repeat(levelSize) {
            val node = queue.removeFirst()
            println(node.`val`)
                    node.left?.let { queue.add(it) }
                    node.right?.let { queue.add(it) }
        }
        println("--- end of level ---")
    }
}

// Grid BFS

fun bfs(grid: Array<CharArray>, startI: Int, startJ: Int) {
    val rows = grid.size
    val cols = grid[0].size
    val visited = Array(rows) { BooleanArray(cols) }
    val queue = ArrayDeque<Pair<Int, Int>>()

    queue.add(Pair(startI, startJ))
    visited[startI][startJ] = true

    val directions = arrayOf(
        intArrayOf(0, 1),
        intArrayOf(0, -1),
        intArrayOf(1, 0),
        intArrayOf(-1, 0)
    )

    while (queue.isNotEmpty()) {
        val (i, j) = queue.removeFirst()
        for (dir in directions) {
            val ni = i + dir[0]
            val nj = j + dir[1]
            if (ni !in 0..<rows || nj < 0 || nj >= cols) continue
            if (visited[ni][nj]) continue
            visited[ni][nj] = true
            queue.add(Pair(ni, nj))
        }
    }
}

// when to use BFS vs DFS
/*
Use BFS when                          Use DFS when
Shortest path needed                  Path existence check
Level order processing needed         Cycle detection
Closest neighbor needed               Topological sort
Multi source traversal needed         Backtracking problems
 */

// key BFS usecase in interviews

/*
Shortest path A to B        -> BFS guarantees shortest path in unweighted graph
Level order traversal       -> BFS natural fit
Rotting Oranges             -> Multi source BFS
Word Ladder                 -> BFS shortest transformation
01 Matrix                   -> Multi source BFS from all 0s
Number of Islands           -> Can use BFS instead of DFS
 */

// complexity
/*
Tree          Graph
Time    -> O(N)       -> O(V + E)
Space   -> O(W)       -> O(V) visited set
W = max width of tree
 */

// DFS and BFS summary
/*
DFS                  BFS
Data structure  Stack                Queue
Order           Deep first           Wide first
Shortest path   No                   Yes
Memory          O(H) height          O(W) width
Cycle detect    Yes                  Yes
Implementation  Recursive/Iterative  Always iterative
 */