package chapter02.search

object Sample2 {
    @JvmStatic
    fun main(args: Array<String>) {
        println(LinearSearch.search(listOf(1, 5, 15, 15, 15, 15, 20), 5)) // 1
        println(BinarySearch.search(listOf("a", "d", "e", "f", "z"), "f")) // 3
        println(BinarySearch.search(listOf("john", "mark", "ronald", "sarah"), "sheila")) // -1
    }
}