package chapter01

import java.util.BitSet


data class CompressedGene(val gene: String) {
    private var bitSet: BitSet? = null
    private var length = 0

    private fun compress(gene: String) {
        length = gene.length
        // reserve enough capacity for all of the bits
        bitSet = BitSet(length * 2)
        // convert to upper case for consistency
        val upperGene = gene.toUpperCase()
        // convert String to bit representation
        for (i in 0 until length) {
            val firstLocation = 2 * i
            val secondLocation = 2 * i + 1
            when (upperGene[i]) {
                'A' -> {
                    bitSet!![firstLocation] = false
                    bitSet!![secondLocation] = false
                }
                'C' -> {
                    bitSet!![firstLocation] = false
                    bitSet!![secondLocation] = true
                }
                'G' -> {
                    bitSet!![firstLocation] = true
                    bitSet!![secondLocation] = false
                }
                'T' -> {
                    bitSet!![firstLocation] = true
                    bitSet!![secondLocation] = true
                }
                else -> throw IllegalArgumentException("The provided gene String contains characters other than ACGT")
            }
        }
    }

    fun decompress(): String {
        if (bitSet == null) {
            return ""
        }
        // create a mutable place for characters with right capacity
        val builder = StringBuilder(length)
        var i = 0
        while (i < length * 2) {
            val firstBit = if (bitSet!![i]) 1 else 0
            val secondBit = if (bitSet!![i + 1]) 1 else 0
            val lastBits = firstBit shl 1 or secondBit
            when (lastBits) {
                0 -> builder.append('A')
                1 -> builder.append('C')
                2 -> builder.append('G')
                3 -> builder.append('T')
            }
            i += 2
        }
        return builder.toString()
    }

    init {
        compress(gene)
    }
}


fun main(args: Array<String>) {
    val original = "TAGGGATTAACCGTTATATATATATAGCCATGGATCGATTATATAGGGATTAACCGTTATATATATATAGCCATGGATCGATTATA"
    val compressed = CompressedGene(original)
    val decompressed = compressed.decompress()
    println(decompressed)
    println("original is the same as decompressed: " + original.equals(decompressed, ignoreCase = true))
}