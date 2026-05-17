package practice.trie

class TrieNode {
    val children = HashMap<Char, TrieNode>()
    var isEnd = false
}