package chapter02.maze;

import chapter02.graph.Node;

import java.util.List;

import static chapter02.graph.GraphSearchKt.aStar;

public class SampleAStar {
    public static void main(String[] args) {
//        maze.Maze m = new maze.Maze();

        Maze m = new Maze(20);

        // A* (Manhattan version)
        Node<MazeLocation> solution3 = aStar(m::getStart, m::goalTest, m::successors, m::manhattanDistance);
        // A* (Euclidean version)
//        Node<maze.MazeLocation> solution3 = graph_traverse.GraphSearch.aStar(m.getStart(), m::goalTest, m::successors, m::euclideanDistance);
        if (solution3 == null) {
            System.out.println("No solution found using A*!");
        } else {
            List<MazeLocation> path3 = solution3.nodeToPath();
            m.mark(path3);
            System.out.println(m);
            m.clear(path3);
        }
    }
}
