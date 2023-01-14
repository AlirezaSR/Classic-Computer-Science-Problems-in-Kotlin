package chapter02.mc

import chapter02.graph.TraverseInterfaces.*
import chapter02.graph.bfs
import chapter02.mc.MCState.Companion.displaySolution


object Sample2 {

    @JvmStatic
    fun main(vararg args: String) {
        val start = MCState(MCState.MAX_NUM, MCState.MAX_NUM, true)
        val solution = bfs(
            initial = object : InitialSupplier<MCState> {
                override fun get() = start
            },
            goalTest = object : IsItGoalPredication<MCState> {
                override fun test(t: MCState) = t.goalTest()
            },
            successors = object : SuccessorsFunction<MCState> {
                override fun apply(t: MCState) = t.successors()
            }
        )

        if (solution == null) {
            println("No solution found!")
        } else {
            val path = solution.nodeToPath()
            displaySolution(path)
        }

    }
}