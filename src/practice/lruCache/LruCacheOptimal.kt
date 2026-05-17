package practice.lruCache

class Node(
    val key: Int,
    var value: Int,
    var prev: Node? = null,
    var next: Node? = null,
)

class LruCacheOptimal(private val capacity: Int) {
    private val cache = HashMap<Int, Node>()

    private val head = Node(0, 0)
    private val tail = Node(0, 0)

    init {
        head.next = head
        tail.prev = head
    }

    fun get(key: Int): Int {
        val node = cache[key] ?: return -1
        moveToHead(node)
        return node.value
    }

    fun put(key: Int, value: Int) {
        val existing = cache[key]
        if (existing != null) {
            existing.value = value
            moveToHead(existing)
        } else {
            val newNode = Node(key, value)
            cache[key] = newNode
            addToHead(newNode)
            if (cache.size > capacity) {
                val lru = removeTail()
                cache.remove(lru.key)
            }
        }
    }

    private fun addToHead(newNode: Node) {
        newNode.prev = head
        newNode.next = head.next
        head.next?.prev = newNode
        head.next = newNode
    }

    private fun moveToHead(existing: Node) {
        removeNode(existing)
        addToHead(existing)
    }

    private fun removeNode(existing: Node) {
        existing.prev?.next = existing.next
        existing.next?.prev = existing.prev
    }

    private fun removeTail(): Node {
        val lru = tail.prev!!
        removeNode(lru)
        return lru
    }

    /*
    Time  -> O(1) for both get() and put()
Space -> O(capacity) HashMap + LinkedList nodes
     */
}