package chapter02.graph

data class Node<T> (
    val state: T,
    val prev: Node<T>?,
    // for graph.dfs and graph.bfs we won't use cost and heuristic
    val cost:Double = 0.0,
    // for graph.dfs and graph.bfs we won't use cost and heuristic
    // for a* Search we will use cost and heuristic
    val heuristic: Double = 0.0
): Comparable<Node<T>> {

    fun nodeToPath(): List<T> {
        var node: Node<T> = this
        val path: MutableList<T> = ArrayList()
        path.add(node.state)
        // work backwards from end to front
        while (node.prev != null) {
            node = node.prev!!
            path.add(0, node.state) // add to front
        }
        return path
    }

    override fun compareTo(other: Node<T>): Int {
        val mine = cost + heuristic
        val theirs = other.cost + other.heuristic
        return mine.compareTo(theirs)
    }
}