package chapter02.maze

data class MazeLocation(
    val x: Int,
    val y: Int) {
    override fun equals(other: Any?): Boolean {
        return if (this === other) {
            true
        } else other is MazeLocation && y == other.y && x == other.x
    }

    override fun hashCode(): Int {
        var result = x
        result = 31 * result + y
        return result
    }
}