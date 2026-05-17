package practice.lruCache

class LRUCacheBrute(private val capacity: Int) {
    private val cache = object : LinkedHashMap<Int, Int>(capacity, 0.75f, true) {
        override fun removeEldestEntry(eldest: MutableMap.MutableEntry<Int, Int>?): Boolean {
            return size > capacity
        }
    }

    fun put(key: Int, value: Int) {
        cache[key] = value
    }

    fun get(key: Int): Int {
        return cache.getOrDefault(key, -1)
    }

    /*
    Time  -> O(1) average for get and put
    Space -> O(capacity)
     */
}