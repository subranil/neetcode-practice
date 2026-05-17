package practice.trie

class Trie {
    private val root = TrieNode()

    fun insert(word: String) {
        var node = root
        for (c in word) {
            node = node.children.getOrPut(c) { TrieNode() }
        }
        node.isEnd = true
    }

    fun search(word: String): Boolean {
        var node = root
        for (c in word) {
            node = node.children[c] ?: return false
        }
        return node.isEnd
    }

    fun startsWith(prefix: String): Boolean {
        var node = root
        for (c in prefix) {
            node = node.children[c] ?: return false
        }
        return true
    }
    /*
    Operation    Time        Space
insert       O(L)        O(L) new nodes
search       O(L)        O(1)
startsWith   O(L)        O(1)

L = length of word
Total space for N words = O(N x L)
     */
}