package chapter02.maze;

import chapter02.graph.Node;

import java.util.List;

import static chapter02.graph.GraphSearchKt.bfs;

public class SampleBFS{
    public static void main(String[] args) {
        //        maze.Maze m = new maze.Maze();
        Maze m = new Maze(20);
        System.out.println(m);

        // BFS
        Node<MazeLocation> solution2 = bfs(m::getStart, m::goalTest, m::successors);
        if (solution2 == null) {
            System.out.println("No solution found using breadth-first Search!");
        } else {
            List<MazeLocation> path2 = solution2.nodeToPath();
            m.mark(path2);
            System.out.println(m);
            m.clear(path2);
        }
    }

}
