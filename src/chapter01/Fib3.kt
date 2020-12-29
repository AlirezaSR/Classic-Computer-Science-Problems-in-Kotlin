package chapter01

// a mutable Map which stores previous outputs
// This creates a map with 0->0 and 1->1
// Which represent our base cases
private var memo = mutableMapOf(0 to 0, 1 to 1)

private fun fib3(n: Int): Int {
    if (!memo.containsKey(n)) {
        // memoization step
        memo[n] = fib3(n - 1) + fib3(n - 2)
    }
    return memo[n]!!
}

/**
 * The Memorizing Fibonacci sequence
 *  description : memorizing  fib3() output wil improve the execution speed(in an other world now big o has became linear).
 * @author Alireza
 */
fun main() {
    println(fib3(5))
    println(fib3(40))
}