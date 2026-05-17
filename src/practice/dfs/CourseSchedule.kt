package practice.dfs

class CourseSchedule {
    /*
    Brute Force DFS      Optimal Kahn's BFS
    Approach        Cycle detection      Topological sort
    Extra tracking  recursionStack       indegree array
    Style           Recursive            Iterative
    Stack overflow  Possible             No
    Time            O(V + E)             O(V + E)
    Space           O(V + E)             O(V + E)
    Interview use   Mention first        Code this
     */
    private lateinit var graph: Array<MutableList<Int>>
    private lateinit var visited: BooleanArray
    private lateinit var recursionStack: BooleanArray

    /*
    Brute Force — DFS Cycle Detection
        Approach:
        Build adjacency list from prerequisites
        For each unvisited node run DFS
        Track current recursion path using recursionStack
        If we hit a node already in current recursion stack — cycle found — return false
        Remove node from recursion stack when backtracking
     */

    fun canFinishBrute(courses: Int, prerequisites: Array<IntArray>): Boolean {
        graph = Array(courses) { mutableListOf() }
        visited = BooleanArray(courses)
        recursionStack = BooleanArray(courses)

        for ((a, b) in prerequisites) {
            graph[b].add(a)
        }
        for (i in 0..courses) {
            if (!visited[i] && hasCycles(i)) return false
        }
        return true
    }

    private fun hasCycles(node: Int): Boolean {
        visited[node] = true
        recursionStack[node] = true

        for (neighbor in graph[node]) {
            if (!visited[neighbor]) {
                if (hasCycles(neighbor))
                    return true
            } else if (recursionStack[neighbor]) {
                return true
            }
        }
        recursionStack[node] = false
        return false
    }

    /*
    Time  -> O(V + E) V = courses, E = prerequisites
    Space -> O(V + E) adjacency list + O(V) visited + recursionStack
     */

    /*
    Optimal — Kahn's Algorithm (BFS Topological Sort)
        Approach:

        Count indegree for every node (number of prerequisites)
        Start processing nodes with indegree 0 (no prerequisites)
        For each processed node reduce indegree of its neighbors
        If neighbor indegree becomes 0 add to queue
        If all nodes processed — no cycle — return true
        If some nodes remain — cycle exists — return false
     */

    fun canFinish(numCourses: Int, prerequisites: Array<IntArray>): Boolean {
        val graph = Array(numCourses) { mutableListOf<Int>() }
        val indegree = IntArray(numCourses)

        for ((a, b) in prerequisites) {
            graph[b].add(a)
            indegree[a]++
        }

        val queue = ArrayDeque<Int>()
        for (i in 0..<numCourses) {
            if (indegree[i] == 0)
                queue.add(i)
        }
        var processCount= 0

        while (queue.isNotEmpty()) {
            val courses = queue.removeFirst()
            processCount++

            for (neighbor in graph[courses]) {
                indegree[neighbor]--
                if (indegree[neighbor] == 0)
                    queue.add(neighbor)
            }
        }
        return processCount == numCourses
    }
    /*
    Time  -> O(V + E)
    Space -> O(V + E) adjacency list + O(V) indegree array + queue
     */
}

