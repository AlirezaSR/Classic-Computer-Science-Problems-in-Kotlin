package chapter01

import java.util.stream.IntStream

data class Fib5(
    private var next: Int = 1,
    private var last: Int = 0
) {
    fun fibStream(): IntStream? = IntStream.generate {
        val tempLast = last
        last = next
        next = next + tempLast
        return@generate tempLast
    }
}

fun main(args: Array<String>) {
    val fib5 = Fib5()
    fib5.fibStream()?. // streaming a chapter01.fibonacci sequence
    limit(41)?. // because 'IntStream's are infinite sequence
    forEachOrdered { println(it) }
}


