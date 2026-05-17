package practice.trie

class ArrayVersionTrieNode {
    val children = arrayOfNulls<ArrayVersionTrieNode>(26)
    var isEnd = false
}

class ArrayVersionTrie {
    private val root = ArrayVersionTrieNode()

    fun insert(word: String) {
        var node = root
        for (c in word) {
            val index = c-'a'
            if (node.children[index] == null) {
                node.children[index] = ArrayVersionTrieNode()
            }
            node = node.children[index]!!
        }
        node.isEnd = true
    }

    fun search(word: String): Boolean {
        var node = root
        for (c in word) {
            val index = c-'a'
            node = node.children[index] ?: return false
        }
        return node.isEnd
    }

    fun startsWith(word: String): Boolean {
        var node = root
        for (c in word) {
            val index = c-'a'
            node = node.children[index] ?: return false
        }
        return true
    }
}