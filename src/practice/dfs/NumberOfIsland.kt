package practice.dfs

class NumberOfIsland {
    /*
    Brute Force
    Approach:
    Use a separate visited matrix to track seen cells
    Does not mutate the original grid
    Useful when input must be preserved
     */
    private lateinit var grid: Array<CharArray>
    private lateinit var visited: Array<BooleanArray>
    private var rows = 0
    private var cols = 0

    fun numOfIslands(grid: Array<CharArray>): Int {
        this.grid = grid
        this.rows = grid.size
        this.cols = grid[0].size
        this.visited = Array(rows) { BooleanArray(cols) }
        var count = 0

        for (i in 0..<rows) {
            for (j in 0..<cols) {
                if (grid[i][j] == '1' && !visited[i][j]) {
                    count++
                    dfs(i, j)
                }
            }
        }

        return count
    }

    private fun dfs(i: Int, j: Int) {
        if (i < 0 || i >= rows || j < 0 || j >= cols) return
        if (visited[i][j] || grid[i][j] == '0') return
        visited[i][j] = true
        dfs(i + 1, j)
        dfs(i - 1, j)
        dfs(i, j + 1)
        dfs(i, j - 1)
    }
    /*
        Time  -> O(M x N)
        Space -> O(M x N) visited matrix + O(M x N) recursion stack worst case
     */
}

class NumberOfIslandOptimal {
    /*
         Optimal — DFS Grid Mutation
        Approach:

        Eliminate visited matrix entirely
        Mutate grid cell from '1' to '0' to mark visited
        Saves O(M x N) extra space
        Trade-off — mutates input, always mention this to interviewer
             */
    private lateinit var grid: Array<CharArray>
    private var rows = 0
    private var cols = 0

    fun numOfIslands(grid: Array<CharArray>): Int {
        this.grid = grid
        this.rows = grid[0].size
        this.cols = grid[0].size
        var count = 0

        for (i in 0 until rows) {
            for (j in 0 until cols) {
                if (this.grid[i][j] == '1') {
                    count++
                    dfs(i, j)
                }
            }
        }

        return count
    }

    private fun dfs(i: Int, j: Int) {
        if (i < 0 || i >= rows || j < 0 || j >= cols) return
        if (grid[i][j] != '1') return
        grid[i][j] = '0'
        dfs(i + 1, j)
        dfs(i - 1, j)
        dfs(i, j + 1)
        dfs(i, j - 1)
    }
}

class UnionFind(size: Int) {
    private val parent: IntArray = IntArray(size) { it }
    private val rank: IntArray = IntArray(size) { 0 }
    var count = 0

    fun find(x: Int): Int {
        if (parent[x] != x)
            parent[x] = find(parent[x])
        return parent[x]
    }

    fun union(x: Int, y: Int) {
        val rootX = find(x)
        val rootY = find(y)
        if (rootX == rootY)
            return

        when {
            rank[rootX] > rank[rootY] -> parent[rootY] = rootX
            rank[rootX] < rank[rootY] -> parent[rootX] = rootY
            else -> {
                parent[rootY] = rootX
                rank[rootX]++
            }
        }
        count++
    }
}

class NumberOfIslandUnionFind {
    fun numOfIslands(grid: Array<CharArray>): Int {
        val rows = grid.size
        val cols = grid[0].size
        val uf = UnionFind(rows * cols)

        for (i in 0..<rows) {
            for (j in 0..<cols) {
                if (grid[i][j] == '1') {
                    uf.count++
                    // check right
                    if (j + 1 < cols && grid[i][j + 1] == '1') {
                        uf.union(i * cols + j, i * cols + j + 1)
                    } else {
                        if (i + 1 < rows && grid[i + 1][j] == '1') {
                            uf.union(i * cols + j, (i + 1) * cols + j)
                        }
                    }
                }
            }
        }
        return uf.count
    }
}