/**This program is a Java program that simulates the use of Huffman encoding to compress a raw grayscale image file. A grayscale image uses
 *  one byte per pixel to represent back and white and the grays in between -- 256 possible grayscale values. The HuffmanTree and HuffmanTreeNode
 *  classes are used to simulate reading an image's pixels into a frequency table, using the frequency table to produce leaf nodes, and encoding
 *  each leaf node using the left and right child's path.
 *
 * @author Cam Lischke <lisccd18@wfu.edu>
 * @date October 16, 2019
 * CSC 221 --Burg-- Program 3
 *
 */

package com.company;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
                                                                                //creates a new HuffmanTree object
        HuffmanTree tree = new HuffmanTree();
        int[] frequencies = tree.createFrequencyTable("Ollie.raw");     //must pass the file name for this method
        tree.plantTree(frequencies);
        tree.build();
        tree.encode();
        tree.printTable();

    }
}
