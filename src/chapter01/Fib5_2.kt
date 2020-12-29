package chapter01

/**
 * @author code is not mine! source is below.
 * @see https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.sequences/-sequence-scope/yield.html
 */
fun main() {
    println("first 10 fibonacci sequence :")
    println(fibonacci().take(10).toList()) // [0, 1, 1, 2, 3, 5, 8, 13, 21, 34]
}

fun fibonacci() = sequence {
    var terms = Pair(0, 1)


    // this sequence is infinite
    while (true) {
        yield(terms.first)
        terms = Pair(terms.second, terms.first + terms.second)
    }
}

