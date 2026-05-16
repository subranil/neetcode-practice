package practice.dfsAndBfs

class WordSearch {
    /*
                                Brute Force          Optimal
    Visited track   visited[][]          Mutate board with #
    Space           O(M x N) + O(L)      O(L) only
    Restore         visited[i][j]=false  board[i][j]=temp
    Mutates input   No                   Yes
    Interview use   Mention first        Code this
    */
    /*
    Brute Force
        Approach:
        Use separate visited matrix to track current path
        Does not mutate original board
        Backtrack by resetting visited[i][j] = false
     */
    private lateinit var board: Array<CharArray>
    private lateinit var word: String
    private lateinit var visited: Array<BooleanArray>
    private var rows = 0
    private var columns = 0

    fun existBrute(board: Array<CharArray>, word: String): Boolean {
        this.board = board
        this.word = word
        this.rows = board.size
        this.columns = board[0].size
        this.visited = Array(rows) { BooleanArray(columns) }

        for (i in 0..<rows) {
            for (j in 0..<columns) {
                if (dfsBrute(i, j, 0))
                    return true
            }
        }
        return false
    }

    private fun dfsBrute(i: Int, j: Int, index: Int): Boolean {
        if (index == word.length)
            return true
        if (i < 0 || i >= rows || j < 0 || j >= columns) return false
        if (visited[i][j] || board[i][j] != word[index]) return false
        visited[i][j] = true

        val found = dfsBrute(i + 1, j, index + 1) ||
                dfsBrute(i - 1, j, index + 1) ||
                dfsBrute(i, j + 1, index + 1) ||
                dfsBrute(i, j - 1, index + 1)

        visited[i][j] = false
        return found
    }
    /*
    Time  -> O(M x N x 4^L)  L = word length
     Space -> O(M x N) visited matrix + O(L) recursion stack
     */
}

class WordSearchOptimal {
    /*
    Optimal
        Approach:

        Eliminate visited matrix
        Mutate board cell to '#' to mark visited
        Restore original character on backtrack
        Saves O(M x N) extra space
     */

    private lateinit var board: Array<CharArray>
    private lateinit var word: String
    private var rows = 0
    private var columns = 0

    fun exist(board: Array<CharArray>, word: String): Boolean {
        this.board = board
        this.word = word
        this.rows = board.size
        this.columns = board[0].size

        for (i in 0..<rows) {
            for (j in 0..<columns) {
                if (dfs(i, j, 0))
                    return true
            }
        }
        return false
    }

    private fun dfs(i: Int, j: Int, index: Int): Boolean {
        if (index == word.length) return true
        if (i < 0 || i >= rows || j < 0 || j >= columns) return false
        if (board[i][j] != word[index]) return false

        val temp = board[i][j]
        board[i][j] = '#'

        val found =
                dfs(i + 1, j, index + 1) ||
                dfs(i - 1, j, index + 1) ||
                dfs(i, j + 1, index + 1) ||
                dfs(i, j - 1, index + 1)
        board[i][j] = temp
        return found
    }
    /*
    Time  -> O(M x N x 4^L)
    Space -> O(L) recursion stack only
     */
}