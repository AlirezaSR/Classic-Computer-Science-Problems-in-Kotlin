package chapter02.maze;

import chapter02.graph.Node;

import java.util.List;

import static chapter02.graph.GraphSearchKt.dfs;

public class SampleDFS {
    public static void main(String[] args) {
        //        maze.maze.Maze m = new maze.maze.Maze();
        Maze m = new Maze(20);
        System.out.println(m);

        // DFS
        Node<MazeLocation> solution1 = dfs(m::getStart, m::goalTest, m::successors);
        if (solution1 == null) {
            System.out.println("No solution found using depth-first Search!");
        } else {
            List<MazeLocation> path1 = solution1.nodeToPath();
            m.mark(path1);
            System.out.println(m);
            m.clear(path1);
        }

    }
}
