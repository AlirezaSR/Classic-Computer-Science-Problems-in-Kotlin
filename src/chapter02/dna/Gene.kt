package chapter02.dna

import java.util.*

class Gene(geneStr: String) {
    private val codons = ArrayList<Codon>()

    init {
        var i = 0
        while (i < geneStr.length - 3) {

            // Take every 3 characters in the String and form a Codon
            codons.add(Codon(geneStr.substring(i, i + 3)))
            i += 3
        }
    }

    fun linearContains(key: Codon?): Boolean {
        for (codon in codons) {
            if (codon.compareTo(key!!) == 0) {
                return true // found a match
            }
        }
        return false
    }

    fun binaryContains(key: Codon?): Boolean {
        // binary Search only works on sorted collections
        val sortedCodons = ArrayList(codons)
        sortedCodons.sort()

        var low = 0
        var high = sortedCodons.size - 1

        while (low <= high) { // while there is still a Search space
            val middle = (low + high) / 2
            val comparison = sortedCodons[middle].compareTo(key!!)
            if (comparison < 0) { // middle codon is less than key
                low = middle + 1
            } else if (comparison > 0) { // middle codon is greater than key
                high = middle - 1
            } else { // middle codon is equal to key
                return true
            }
        }
        return false
    }
}