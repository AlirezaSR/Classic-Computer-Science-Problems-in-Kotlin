package chapter01

/**
 *  This won't work !!!
 *  since there is no base condition
 */

fun main() {
    println(fib1(5))
}

fun fib1(n: Int): Int = fib1(n - 1) + fib1(n - 2)
