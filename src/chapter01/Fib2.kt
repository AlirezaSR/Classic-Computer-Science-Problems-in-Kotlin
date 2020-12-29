package chapter01

/**
 * The recursive Fibonacci sequence (fixed)
 *  description : Infinite loop issue hase been fixed by adding some base cases
 * @author Alireza
 */


fun fib2(n: Int): Int =
    when (n) {
        0 -> 0 // base case
        1 -> 1 // base case
        else -> fib2(n - 1) + fib2(n - 2)
    }

fun main() {
    println(fib2(5));
    println(fib2(10));
}


