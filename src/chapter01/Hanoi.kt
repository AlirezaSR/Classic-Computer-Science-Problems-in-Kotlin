package chapter01

import java.util.*

class Hanoi(val numDiscs: Int) {
    val towerA = Stack<Int>()
    val towerB = Stack<Int>()
    val towerC = Stack<Int>()
    private fun move(begin: Stack<Int>, end: Stack<Int>, temp: Stack<Int>, n: Int) {
        if (n == 1) {
            end.push(begin.pop())
        } else {
            move(begin, temp, end, n - 1)
            move(begin, end, temp, 1)
            move(temp, end, begin, n - 1)
        }
    }

    fun solve() {
        move(towerA, towerC, towerB, numDiscs)
    }


    init {
        for (i in 1..numDiscs) {
            towerA.push(i)
        }
    }
}

/** Tower of Hanoi is a mathematical puzzle where we have three rods and n disks.
 * The objective of the puzzle is to move the entire stack to another rod, obeying
 * the following simple rules:
 *      1) Only one disk can be moved at a time.
 *      2) Each move consists of taking the upper disk from one of the stacks and
 *        placing it on top of another stack i.e. a disk can only be moved if it is the
 *        uppermost disk on a stack.
 *      3) No disk may be placed on top of a smaller disk.
 *
 * @see https://en.wikipedia.org/wiki/Tower_of_Hanoi
 * @see  https://www.geeksforgeeks.org/c-program-for-tower-of-hanoi/
 *
 */
fun main(args: Array<String>) {
    val hanoi = Hanoi(3)
    hanoi.solve()
    println(hanoi.towerA)
    println(hanoi.towerB)
    println(hanoi.towerC)
}