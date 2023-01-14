package chapter02.mc

import java.util.function.Predicate

data class MCState constructor(
    val wm: Int,
    val wc: Int,
    val em: Int,
    val ec: Int,
    val boat: Boolean
){

    constructor(missionaries: Int, cannibals: Int, boat: Boolean) : this(
        wm = missionaries,
        wc = cannibals,
        em = MAX_NUM - missionaries,
        ec = MAX_NUM - cannibals,
        boat = boat
    )

    override fun toString() =
        """
       On the west bank there are $wm missionaries and $wc cannibals.
       On the east bank there are $em missionaries and $ec cannibals.
       The boat is on the ${if (boat) "west" else "east"} bank.
       """.trimIndent()


    fun goalTest(): Boolean {
        return isFeasible() && em == MAX_NUM && ec == MAX_NUM
    }

    fun isFeasible(): Boolean {
        if (wm < wc && wm > 0) {
            return false
        }
        if (em < ec && em > 0) {
            return false
        }
        return true
    }


    companion object {
        const val MAX_NUM = 3

        fun displaySolution(path: List<MCState>) {
            if (path.isEmpty()) { // sanity check
                return
            }
//        var oldState = path[0]
            var oldState = path.first()
            println(oldState)
            for (currentState in path.subList(1, path.size)) {
                if (currentState.boat) {
                    System.out.printf(
                        "%d missionaries and %d cannibals moved from the east bank to the west bank.%n",
                        oldState.em - currentState.em,
                        oldState.ec - currentState.ec
                    )
                } else {
                    System.out.printf(
                        "%d missionaries and %d cannibals moved from the west bank to the east bank.%n",
                        oldState.wm - currentState.wm,
                        oldState.wc - currentState.wc
                    )
                }
                println(currentState)
                oldState = currentState
            }
        }


    }


    fun successors(): List<MCState> {
        val mcs = this
        val sucs = mutableListOf<MCState>()
        if (mcs.boat) { // boat on west bank
            if (mcs.wm > 1) {
                sucs.add(MCState(mcs.wm - 2, mcs.wc, !mcs.boat))
            }
            if (mcs.wm > 0) {
                sucs.add(MCState(mcs.wm - 1, mcs.wc, !mcs.boat))
            }
            if (mcs.wc > 1) {
                sucs.add(MCState(mcs.wm, mcs.wc - 2, !mcs.boat))
            }
            if (mcs.wc > 0) {
                sucs.add(MCState(mcs.wm, mcs.wc - 1, !mcs.boat))
            }
            if (mcs.wc > 0 && mcs.wm > 0) {
                sucs.add(MCState(mcs.wm - 1, mcs.wc - 1, !mcs.boat))
            }
        } else { // boat on east bank
            if (mcs.em > 1) {
                sucs.add(MCState(mcs.wm + 2, mcs.wc, !mcs.boat))
            }
            if (mcs.em > 0) {
                sucs.add(MCState(mcs.wm + 1, mcs.wc, !mcs.boat))
            }
            if (mcs.ec > 1) {
                sucs.add(MCState(mcs.wm, mcs.wc + 2, !mcs.boat))
            }
            if (mcs.ec > 0) {
                sucs.add(MCState(mcs.wm, mcs.wc + 1, !mcs.boat))
            }
            if (mcs.ec > 0 && mcs.em > 0) {
                sucs.add(MCState(mcs.wm + 1, mcs.wc + 1, !mcs.boat))
            }
        }
        sucs.removeIf(Predicate.not { obj: MCState -> obj.isFeasible() })
        return sucs
    }


    override fun hashCode(): Int {
        var result = wm
        result = 31 * result + wc
        result = 31 * result + em
        result = 31 * result + ec
        result = 31 * result + boat.hashCode()
        return result
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true

        if (javaClass != other?.javaClass) return false

        other as MCState

        if (this.wm != other.wm) return false
        if (this.wc != other.wc) return false
        if (this.em != other.em) return false
        if (this.ec != other.ec) return false
        if (this.boat != other.boat) return false

        return true
    }
}