# HuffmanEncoding
Huffman encoding is a variable-length encoding method that can be used to compress a file without losing any information in the compression. This implies that when the file is decompressed, you get back exactly the original file.    A variable-length encoding method allows colors to be represented by a different number of bits -- not always one byte (which is 8 bits). Colors that are used more often should be represented with fewer bits, and vice versa.     The Huffman encoding algorithm was described in class. Please refer to your class notes for details on the algorithm. 

The basic steps are these:    ● Read through the image file and count the frequencies of the colors.  
● From the frequency table, make leaf nodes representing the colors that exist in the image. Store the frequency of the color and the color value (a number between 0 and 255) in each node. 
● Build a Huffman tree from the bottom up. Only leaf nodes (the original ones) have a color stored in them. 
● Once you have the Huffman tree built, you use it to encode the colors represented by the leaf nodes. Traverse the tree, gathering a 0 each time you move down a left edge and a 1 each time you move down a right edge. You gather the 0s and 1s as characters in a string and print out the string as the bit encoding of the color at the leaf node. This suffices as a simulation of the bit encoding to colors.
● Figure out how many bits would be required in the compressed grayscale image, and how many bits would be required for the image if each pixel was one byte. Convert this to a percentage of the original file's size.  For example, 64% would mean that the compressed file would be 64% the size of the original file's uncompressed size (not counting the size of the table that would have to be included in the compressed file for decoding.



