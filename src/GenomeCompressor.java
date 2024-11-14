/******************************************************************************
 *  Compilation:  javac GenomeCompressor.java
 *  Execution:    java GenomeCompressor - < input.txt   (compress)
 *  Execution:    java GenomeCompressor + < input.txt   (expand)
 *  Dependencies: BinaryIn.java BinaryOut.java
 *  Data files:   genomeTest.txt
 *                virus.txt
 *
 *  Compress or expand a genomic sequence using a 2-bit code.
 ******************************************************************************/

/**
 *  The {@code GenomeCompressor} class provides static methods for compressing
 *  and expanding a genomic sequence using a 2-bit code.
 *
 *  @author Robert Sedgewick
 *  @author Kevin Wayne
 *  @author Zach Blick
 */
public class GenomeCompressor {
    static final int BITS_PER_INT = 2;

    /**
     * Reads a sequence of 8-bit extended ASCII characters over the alphabet
     * { A, C, T, G } from standard input; compresses and writes the results to standard output.
     */
    public static void compress() {
        String text = BinaryStdIn.readString();
        int len = text.length();
        for (int i = 0; i < len; i++){
            // Writes each char as a 2 bit binary int, saving 6 bits per letter
            if (text.charAt(i) == 'A'){
                BinaryStdOut.write(0, BITS_PER_INT);
            }
            else if (text.charAt(i) == 'C'){
                BinaryStdOut.write(1, BITS_PER_INT);
            }
            else if (text.charAt(i) == 'T'){
                BinaryStdOut.write(2, BITS_PER_INT);
            }
            else if (text.charAt(i) == 'G'){
                BinaryStdOut.write(3, BITS_PER_INT);
            }
        }
        BinaryStdOut.close();
    }

    /**
     * Reads a binary sequence from standard input; expands and writes the results to standard output.
     */
    public static void expand() {

        while (!BinaryStdIn.isEmpty()) {
            int i = BinaryStdIn.readInt(BITS_PER_INT);
            if (i == 0){
                BinaryStdOut.write('A');
            }
            else if (i == 1){
                BinaryStdOut.write('C');
            }
            else if (i == 2){
                BinaryStdOut.write('T');
            }
            else if (i == 3){
                BinaryStdOut.write('G');
            }
        }
        BinaryStdOut.close();
    }


    /**
     * Main, when invoked at the command line, calls {@code compress()} if the command-line
     * argument is "-" an {@code expand()} if it is "+".
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {

        if      (args[0].equals("-")) compress();
        else if (args[0].equals("+")) expand();
        else throw new IllegalArgumentException("Illegal command line argument");
    }
}