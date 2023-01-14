package chapter02.dna;

public class Sample {
    public static void main(String[] args) {
        String geneStr = "ACGTGGCTCTCTAACGTACGTACGTACGGGGTTTATATATACCCTAGGACTCCCTTT";
        Gene myGene = new Gene(geneStr);
        Codon acg = new Codon("ACG");
        Codon gat = new Codon("GAT");
        System.out.println(myGene.linearContains(acg)); // true
        System.out.println(myGene.linearContains(gat));// false
        System.out.println(myGene.binaryContains(acg)); // true
        System.out.println(myGene.binaryContains(gat));// false
    }

}
