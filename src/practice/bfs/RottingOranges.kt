package practice.bfs

class RottingOranges {
    /*
    Brute Force — Simulation
        Approach:

        Each minute scan entire grid
        Find all rotten oranges and rot their fresh neighbors
        Repeat until no change or no fresh oranges remain
        Inefficient — scans entire grid every minute
     */

    fun orangesRottenBrute(grid: Array<IntArray>): Int {
        val rows = grid.size
        val cols = grid[0].size
        var minutes = 0
        val directions = arrayOf(
            intArrayOf(0, 1),
            intArrayOf(1, 0),
            intArrayOf(0, -1),
            intArrayOf(-1, 0),
        )

        while (true) {
            val toRot = mutableListOf<Pair<Int, Int>>()
            for (i in 0..<rows) {
                for (j in 0..<cols) {
                    if (grid[i][j] == 2) {
                        for (dir in directions) {
                            val ni = i + dir[0]
                            val nj = j + dir[1]
                            if (ni !in 0..<rows || nj < 0 || ni >= cols) continue
                            if (grid[ni][nj] == 1) toRot.add(Pair(ni, nj))
                        }
                    }
                }
            }
            if (toRot.isEmpty()) break
            for ((i, j) in toRot) grid[i][j] = 2
            minutes++
        }
        return minutes
    }
    /*
    Time  -> O((M x N)²) scan entire grid every minute
    Space -> O(M x N) toRot list
     */

    fun orangeRottingOptimal(grid: Array<IntArray>): Int {
        val rows = grid.size
        val cols = grid[0].size
        var queue = ArrayDeque<Pair<Int, Int>>()
        var freshCount = 0
        val directions = arrayOf(
            intArrayOf(0, 1),
            intArrayOf(1, 0),
            intArrayOf(0, -1),
            intArrayOf(-1, 0),
        )

        for (i in 0..<rows) {
            for (j in 0..<cols) {
                when (grid[i][j]) {
                    2 -> queue.add(Pair(i, j))
                    1 -> freshCount++
                }
            }
        }
        if (freshCount == 0) return 0
        var minutes = 0
        while (queue.isNotEmpty() && freshCount > 0) {
            val levelSize = queue.size
            minutes++

            repeat(levelSize) {
                val (i, j) = queue.removeFirst()
                for (dir in directions) {
                    val ni = i + dir[0]
                    val nj = j + dir[1]
                    if (ni !in 0..<rows || nj < 0 || nj >= cols) continue
                    if (grid[ni][nj] != 1) continue
                    grid[ni][nj] = 2
                    freshCount--
                    queue.add(Pair(ni, nj))
                }
            }
        }
        return if (freshCount == 0) minutes else -1
    }
    /*
    Time  -> O(M x N) every cell visited once
    Space -> O(M x N) queue worst case all cells rotten
     */
}