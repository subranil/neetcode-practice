package neetcode.stack

object GenerateParenthesis {
    fun solution(n: Int): List<String> {
        val result = mutableListOf<String>()
        backtrack(n, "", 0, 0, result)
        return result
    }

    private fun backtrack(n: Int, current: String, open: Int, close: Int, result: MutableList<String>) {
        if (current.length == n * 2) {
            result.add(current)
            return
        }
        if (open < n) {
            backtrack(n, "$current(", open + 1, close, result)
        }
        if (close < open) {
            backtrack(n, "$current)", open, close + 1, result)
        }
    }


}