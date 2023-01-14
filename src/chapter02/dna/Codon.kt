package chapter02.dna

data class Codon(
    val first: Nucleotide,
    val second: Nucleotide,
    val third: Nucleotide
):Comparable<Codon> {
    constructor(codonStr: String) : this(
        Nucleotide.valueOf(codonStr.substring(0, 1)),
        Nucleotide.valueOf(codonStr.substring(1, 2)),
        Nucleotide.valueOf(codonStr.substring(2, 3))
    )

    override fun compareTo(other: Codon): Int {
        // first is compared first, then second, etc.
        // IOW first takes precedence over second and second over third
        return comparator.compare(this, other)
    }

    companion object {
        private val comparator = Comparator.comparing { (first): Codon -> first }
            .thenComparing { (_, second)-> second }
            .thenComparing { (_, _, third) -> third }
    }
}