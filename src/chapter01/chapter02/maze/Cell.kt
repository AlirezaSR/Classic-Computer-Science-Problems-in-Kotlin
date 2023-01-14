package chapter02.maze

enum class Cell(private val code: String) {
    EMPTY(" "),
    BLOCKED("X"),
    START("S"),
    GOAL("G"),
    PATH("*");

    override fun toString() = code
}