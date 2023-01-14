package chapter02.dna

object Main {
    @JvmStatic
    fun main(args: Array<String>) {
        val geneStr = "ACGTGGCTCTCTAACGTACGTACGTACGGGGTTTATATATACCCTAGGACTCCCTTT"
        val myGene = Gene(geneStr)
        val acg = Codon("ACG")
        val gat = Codon("GAT")
        println(myGene.linearContains(acg)) // true
        println(myGene.linearContains(gat)) // false
        println(myGene.binaryContains(acg)) // true
        println(myGene.binaryContains(gat)) // false
    }
}