package chapter01

/**  Pi Approximation with Leibniz Series
 *  @see https://youtu.be/uH4trBNn540 for more information
 * */
fun calculatePi(nTerms: Int): Double {
    val numerator = 4.0
    var denominator = 1.0
    var operation = 1.0
    var pi = 0.0
    for (i in 0..nTerms) {
        pi += operation * (numerator / denominator)

//         TODO
//        shifting bits to left or right can increase performance.
//        loop can be converted int to two loops ,which one loop will adds while the other one minus

//        TODO
//        Concurrency can be  achieved by using 'Kotlin coroutines' .
//        @See Like the https://play.golang.org/p/Ryve74AOWxA an official GOLANG  sample document .


        denominator += 2.0
        operation *= -1.0
    }
    return pi
}

fun main(args: Array<String>) {
    println(calculatePi(1_000_000))
}
