package chapter02.mc;

import chapter02.graph.Node;

import java.util.List;


public class Sample1 {
    public static void main(String[] args) {
        MCState start = new MCState(MCState.MAX_NUM , MCState.MAX_NUM ,true);
        Node<MCState> bfsSolution = chapter02.graph.GraphSearchKt.dfs(
                () -> start ,
                MCState::goalTest,
                MCState::successors
        );

        if (bfsSolution == null)
            System.out.println("sorry no solution found");
        else {
            List<MCState> path = bfsSolution.nodeToPath();
            MCState.Companion.displaySolution(path);
        }

    }
}
