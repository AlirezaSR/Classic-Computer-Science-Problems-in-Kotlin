package chapter01

private fun fib4(n: Int): Int {
    var last = 0
    var next = 1 // fib(0), fib(1)

    for (i in 0..n) {
        val oldLast = last
        last = next
        next += oldLast
    }

    return last
}

/**
 * It is like Fib3 (I mean being dynamic instead of recursive ).
 * Big O is Still linear!! Just auxiliary memory has been reduce.
 * More specifically linear memory consumption has been reduced to 1 ;
 */

fun main() {
    println(fib4(5))
    println(fib4(40))
}
