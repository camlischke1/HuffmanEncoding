/** This class represents a tree structure that contains HuffmanTreeNode objects used in the Huffman Encoding Method. The tree is used to simulate
 * Huffman Encoding Methods
 * @author Cam Lischke
 * @date October 16, 2019
 */

package com.company;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;

import static java.lang.System.exit;


public class HuffmanTree extends ArrayList<HuffmanTreeNode>{
    //Data members
   HuffmanTreeNode root = null;
   int size = 0;                                //number of elements in the actual tree
    int numBits = 0;                            //number of bits after encoding the leaf nodes
   ArrayList<HuffmanTreeNode> elements;

   public HuffmanTree(){}       //constructor

   public HuffmanTree(ArrayList<HuffmanTreeNode> leafNodes){        //constructor
       root = leafNodes.get(0);
       size = leafNodes.size();
       elements = leafNodes;
   }

   public void setRoot(HuffmanTreeNode node){
       root = node;
   }
   public HuffmanTreeNode getRoot(){return root; }

   public int getSize(){
       return size;
   }

   public ArrayList<HuffmanTreeNode> getElements(){ return elements; }

    /**This method reads through a given image, reads its pixels and collects the frequencies of each possible color. The parameter is a string
     * that will become the argument for  new FileInputStream object.
     *
     * @param fileName as a String
     * @return int array
     */
    public int[] createFrequencyTable(String fileName){
        int[] frequencies = new int[256];          //frequency table. i is actual color and element at i is the frequency of color i
        try (InputStream ollie = new FileInputStream(fileName);){
            for (int y = 0; y < (800*800); y++){      //loop iterates through all bytes, checks color, matches color with "i"
                int color = ollie.read();

                for (int i = 0; i < 256; i++){
                    if (color == i) {
                        frequencies[i]++;
                        break;                                      //checks color with "i", if match adds one to that element
                    }
                }
            }
        } catch (IOException ex){
            ex.printStackTrace();
            System.exit(-1);
        }
        return frequencies;
    }

    /*This function instantiates an ArrayList for each of the frequencies given in the array. The ArrayList contains TreeNode objects
     * @return ArrayList of TreeNode objects
     */
    public void plantTree(int[] array){
        ArrayList nodeList = new ArrayList();
        for (int i = 0; i < array.length; i++){
            if (array[i] != 0){
                nodeList.add(new HuffmanTreeNode(i, array[i]));         //adds a TreeNode with color i and frequency array[i]
            }
        }
        Collections.sort(nodeList);
        elements = nodeList;
    }

    /*This function draws the connection between the two lowest-frequency tree nodes and their new parent node.
     * @return void
     */
    public void build(){
        for (int i = 1; i < elements.size(); i+=2){
            HuffmanTreeNode parent = new HuffmanTreeNode(elements.get(i-1).frequency + elements.get(i).frequency);
            parent.setLeftChild(elements.get(i-1));
            parent.setRightChild(elements.get(i));

            elements.add(parent);
            size++;
            Collections.sort(elements);
        }

        this.root = elements.get(elements.size() - 1);
    }


    /* Function uses a built HuffmanTree object to encode using bits. A 0 for each left child branch and a 1 for each right child branch
    it will assign each leaf node a binary code. It will also use recursion.
     *   @param the root of the tree
     */
    public void encode(){
        if (root == null) return;
        else {
            String temp = "";
            encode(root, temp);
        }
    }

    public void encode(HuffmanTreeNode n, String s){
        if (n.getLeftChild() != null || n.getRightChild() != null ) {
                encode(n.getLeftChild(), (s + "0"));           //adds a '0' to the end of s
                encode(n.getRightChild(), (s + "1"));
        }
        else {
            n.setCode(s);                //recursive base case call sets code, resets the string, and returns void
            s = "";
            return;
        }
    }


    /**This function will print the contents of the Huffman Tree's leaf nodes, including the grayscale value, the bit encoding, and the
     * frequency. The recursive function with parameters will keep a count of the number of bits required for each color present.
     *
     * @return void
     */
    public void printTable(){
        System.out.println(String.format("%20s %8s %10s %8s %2s", "Grayscale Value", "|", "Frequency", "|", "Bit Encoding"));
        System.out.println("==================================================================");
        printTable(elements);

        System.out.println("==================================================================");
        System.out.println("The encoded picture requires " + numBits + " bits, which would be " + ((numBits/(640000*8.0)) * 100) +
                            "% of the size of the original picture. \nWe know this because each byte has 8 bits, and each pixel has one byte." +
                            " The image is 800x800, so there \nare 640,000 pixels and, consequently, 640,000 bytes, which equates to 5,120,000 bits uncompressed.");
    }

    public void printTable(ArrayList<HuffmanTreeNode> list){
        if (list != null){
            Collections.sort(list);
            for (int i = 0; i < list.size(); i++) {
                if (!(list.get(i).getCode().equals(""))) {                 //if n is a leaf node
                    System.out.println(String.format("%20s %8s %10s %8s %15s", list.get(i).getColor(), "|", list.get(i).getFreq(), "|", list.get(i).getCode()));
                    numBits += (list.get(i).getCode().length() * list.get(i).getFreq());
                }
            }
        }
        else{
            System.out.println("Unable to print results.");
            System.exit(-1);
        }
    }
}
