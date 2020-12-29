package chapter02

import java.util.*
import java.util.function.Function
import java.util.function.Predicate
import java.util.function.ToDoubleFunction

/**
 * A general class with a  bunch of  good methods specialized in searching
 *
 */

object GenericSearch {

    fun <T : Comparable<T>> linearContains(list: List<T>, key: T): Boolean {
        // found a match
        for (item in list) {
            if (item.compareTo(key) == 0)
                return true
        }
        return false
    }
    


    // assumes *list* is already sorted
    fun <T : Comparable<T>> binarySearch(list: List<T>, key: T): Boolean {
        var low = 0
        var high = list.size - 1
        while (low <= high) { // while there is still a search space
            val middle = (low + high) / 2
            val comparison = list[middle].compareTo(key)
            when (comparison) {
                -1 -> { // middle codon is less than key
                    low = middle + 1
                }
                1 -> { // middle codon is greater than key
                    high = middle - 1
                }
                0 -> { // middle codon is equal to key
                    return true
                }
            }
        }
        return false
    }

    /**
     *  DFS  search implementation
     */
    fun <T> dfs(
        initial: T, goalTest: Predicate<T>,
        successors: Function<T, List<T>>
    ): Node<T>? {
        // frontier is where we've yet to go
        val frontier = Stack<Node<T>>()
        frontier.push(Node(initial, null))
        // explored is where we've been
        val explored: MutableSet<T> = HashSet()
        explored.add(initial)

        // keep going while there is more to explore
        while (!frontier.isEmpty()) {
            val currentNode = frontier.pop()
            val currentState = currentNode.state
            // if we found the goal, we're done
            if (goalTest.test(currentState)) {
                return currentNode
            }
            // check where we can go next and haven't explored
            for (child in successors.apply(currentState)) {
                if (explored.contains(child)) {
                    continue  // skip children we already explored
                }
                explored.add(child)
                frontier.push(Node(child, currentNode))
            }
        }
        return null // went through everything and never found goal
    }

    fun <T> nodeToPath(node: Node<T>): List<T> {
        var node = node
        val path: MutableList<T> = ArrayList()
        path.add(node.state)
        // work backwards from end to front
        while (node.parent != null) {
            node = node.parent!!
            path.add(0, node.state) // add to front
        }
        return path
    }

    /**
     *  BFS  search implementation
     */
    fun <T> bfs(
        initial: T, goalTest: Predicate<T>,
        successors: Function<T, List<T>>
    ): Node<T>? {
        // frontier is where we've yet to go
        val frontier: Queue<Node<T>> = LinkedList()
        frontier.offer(Node(initial, null))
        // explored is where we've been
        val explored: MutableSet<T> = HashSet()
        explored.add(initial)

        // keep going while there is more to explore
        while (!frontier.isEmpty()) {
            val currentNode = frontier.poll()
            val currentState = currentNode.state
            // if we found the goal, we're done
            if (goalTest.test(currentState)) {
                return currentNode
            }
            // check where we can go next and haven't explored
            for (child in successors.apply(currentState)) {
                if (explored.contains(child)) {
                    continue  // skip children we already explored
                }
                explored.add(child)
                frontier.offer(Node(child, currentNode))
            }
        }
        return null // went through everything and never found goal
    }

    /**
     * A*  search implementation
     */
    fun <T> aStar(
        initial: T,
        goalTest: Predicate<T>,
        successors: Function<T, List<T>>, heuristic: ToDoubleFunction<T>
    ): Node<T>? {
        // frontier is where we've yet to go
        val frontier = PriorityQueue<Node<T>>()
        frontier.offer(Node(initial, null, 0.0, heuristic.applyAsDouble(initial)))
        // explored is where we've been
        val explored: MutableMap<T, Double> = HashMap()
        explored[initial] = 0.0
        // keep going while there is more to explore
        while (!frontier.isEmpty()) {
            val currentNode = frontier.poll()
            val currentState = currentNode.state
            // if we found the goal, we're done
            if (goalTest.test(currentState)) {
                return currentNode
            }
            // check where we can go next and haven't explored
            for (child in successors.apply(currentState)) {
                // 1 here assumes a grid, need a cost function for more sophisticated apps
                val newCost = currentNode.cost + 1
                if (!explored.containsKey(child) || explored[child]!! > newCost) {
                    explored[child] = newCost
                    frontier.offer(Node(child, currentNode, newCost, heuristic.applyAsDouble(child)))
                }
            }
        }
        return null // went through everything and never found goal
    }

}


data class Node<T>
    (
    var state: T,
    var parent: Node<T>?,
    var cost: Double = 0.0,// for dfs and bfs we won't use cost and heuristic
    var heuristic: Double = 0.0 // for A* we will use cost and heuristic
) : Comparable<Node<T>> {
    override fun compareTo(other: Node<T>): Int {
        val mine = cost + heuristic
        val theirs = other.cost + other.heuristic
        return mine.compareTo(theirs)
    }
}


fun main() {
    println(GenericSearch.linearContains(listOf(1, 5, 15, 15, 15, 15, 20), 5)) // true
    println(GenericSearch.binarySearch(listOf("a", "d", "e", "f", "z"), "f")) // true
    println(GenericSearch.binarySearch(listOf("john", "mark", "ronald", "sarah"), "sheila")) // false
}