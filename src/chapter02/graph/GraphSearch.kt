package chapter02.graph

import java.util.*

import chapter02.graph.TraverseInterfaces.SuccessorsFunction
import chapter02.graph.TraverseInterfaces.HeuristicFunction
import chapter02.graph.TraverseInterfaces.IsItGoalPredication
import chapter02.graph.TraverseInterfaces.InitialSupplier

class GraphSearch

fun <T> dfs(
    initial: InitialSupplier<T>,
    goalTest: IsItGoalPredication<T>,
    successors: SuccessorsFunction<T>
): Node<T>? {

    // frontier is where we've yet to go
    val frontier = Stack<Node<T>>()
    frontier.push(Node(initial.get(), null))
    // explored is where we've been
    val explored: MutableSet<T> = HashSet()
    explored.add(initial.get())

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

fun <T> bfs(
    initial: InitialSupplier<T>,
    goalTest: IsItGoalPredication<T>,
    successors: SuccessorsFunction<T>
): Node<T>? {
    // frontier is where we've yet to go
    val frontier: Queue<Node<T>> = LinkedList()
    frontier.offer(Node(initial.get(), null))
    // explored is where we've been
    val explored: MutableSet<T> = HashSet()
    explored.add(initial.get())

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

fun <T> aStar(
    initial: InitialSupplier<T>,
    goalTest: IsItGoalPredication<T>,
    successorsFunction: SuccessorsFunction<T>,
    heuristic: HeuristicFunction<T>
): Node<T>? {

    // frontier is where we've yet to go
    val frontier = PriorityQueue<Node<T>>()
    frontier.offer(Node(initial.get(), null, 0.0, heuristic.applyAsDouble(initial.get())))
    // explored is where we've been
    val explored: MutableMap<T, Double> = HashMap()
    explored[initial.get()] = 0.0
    // keep going while there is more to explore
    while (!frontier.isEmpty()) {
        val currentNode = frontier.poll()
        val currentState = currentNode.state
        // if we found the goal, we're done
        if (goalTest.test(currentState)) {
            return currentNode
        }
        // check where we can go next and haven't explored
        for (successor in successorsFunction.apply(currentState)) {
            // 1 here assumes a grid, need a cost function for more sophisticated apps
            val newCost = currentNode.cost + 1
            if (!explored.containsKey(successor) || explored[successor]!! > newCost) {
                explored[successor] = newCost
                frontier.offer(Node(successor, currentNode, newCost, heuristic.applyAsDouble(successor)))
            }
        }
    }
    return null // went through everything and never found goal
}



