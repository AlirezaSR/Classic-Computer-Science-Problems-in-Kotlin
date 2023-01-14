package chapter02.maze

import java.util.*

data class Maze(
    private val rows: Int = 10,
    private val columns: Int = 10,
    val start: MazeLocation = MazeLocation(0, 0),
    val goal: MazeLocation = MazeLocation(9, 9),
    val sparseness: Double = 0.2
) {
    private val grid: Array<Array<Cell?>> = Array(rows) { arrayOfNulls(columns) }

    init {
        // initialize basic instance variables
        // fill the grid with empty cells
        for (row in grid) {
            Arrays.fill(row, Cell.EMPTY)
        }
        // populate the grid with blocked cells
        randomlyFill(sparseness)
        // fill the start and goal locations
        grid[start.x][start.y] = Cell.START
        grid[goal.x][goal.y] = Cell.GOAL
    }

    constructor(size: Int) :
            this(size, size, MazeLocation(0, 0), MazeLocation(size - 1, size - 1), 0.2)


    private fun randomlyFill(sparseness: Double) {
        for (row in 0 until rows) {
            for (column in 0 until columns) {
                if (Math.random() < sparseness) {
                    grid[row][column] = Cell.BLOCKED
                }
            }
        }
    }

    // return a nicely formatted version of the maze for printing
    override fun toString(): String {
        val sb = StringBuilder()
        for (row in grid) {
            for (cell in row) {
                sb.append(cell)
            }
            sb.append(System.lineSeparator())
        }
        return sb.toString()
    }

    fun goalTest(ml: MazeLocation): Boolean {
        return goal == ml
    }

    fun successors(ml: MazeLocation): List<MazeLocation> {
        val locations: MutableList<MazeLocation> = ArrayList()
        if (ml.x + 1 < rows && grid[ml.x + 1][ml.y] !== Cell.BLOCKED) {
            locations.add(MazeLocation(ml.x + 1, ml.y))
        }
        if (ml.x - 1 >= 0 && grid[ml.x - 1][ml.y] !== Cell.BLOCKED) {
            locations.add(MazeLocation(ml.x - 1, ml.y))
        }
        if (ml.y + 1 < columns && grid[ml.x][ml.y + 1] !== Cell.BLOCKED) {
            locations.add(MazeLocation(ml.x, ml.y + 1))
        }
        if (ml.y - 1 >= 0 && grid[ml.x][ml.y - 1] !== Cell.BLOCKED) {
            locations.add(MazeLocation(ml.x, ml.y - 1))
        }
        return locations
    }

    fun mark(path: List<MazeLocation>) {
        for (ml in path) {
            grid[ml.x][ml.y] = Cell.PATH
        }
        grid[start.x][start.y] = Cell.START
        grid[goal.x][goal.y] = Cell.GOAL
    }

    fun clear(path: List<MazeLocation>) {
        for (ml in path) {
            grid[ml.x][ml.y] = Cell.EMPTY
        }
        grid[start.x][start.y] = Cell.START
        grid[goal.x][goal.y] = Cell.GOAL
    }

    fun euclideanDistance(ml: MazeLocation): Double {
        val xdist = ml.y - goal.y
        val ydist = ml.x - goal.x
        return StrictMath.sqrt((xdist * xdist + ydist * ydist).toDouble())
    }

    fun manhattanDistance(ml: MazeLocation): Double {
        val xdist = StrictMath.abs(ml.y - goal.y)
        val ydist = StrictMath.abs(ml.x - goal.x)
        return (xdist + ydist).toDouble()
    }
}