/**This class represents a tree node used in the HuffmanTree representation in order to Huffman encoding method. The tree contains a grayscle color
 * and its frequency, as well as references to a left and right child node, and its encoded path.
 *
 * @author Cam Lischke
 * @date October 16,2019
 */

package com.company;

public class HuffmanTreeNode implements Comparable<HuffmanTreeNode>{
    int color;
    String code = "";
    int frequency;
    HuffmanTreeNode leftChild = null;
    HuffmanTreeNode rightChild = null;

    public HuffmanTreeNode(int color, int frequency){               //constructor
        this.frequency = frequency;
        this.color = color;
        this.leftChild = null;
        this.rightChild = null;

    }

    public HuffmanTreeNode(int frequency){                          //constructor
        this.frequency = frequency;
    }

    public int getColor(){
        return color;
    }

    public String getCode(){
        return code;
    }

    public int getFreq(){
        return frequency;
    }

    public HuffmanTreeNode getLeftChild(){
        return leftChild;
    }

    public HuffmanTreeNode getRightChild(){
        return rightChild;
    }

    public void setLeftChild(HuffmanTreeNode node){
        leftChild = node;
    }

    public void setRightChild(HuffmanTreeNode node){
        rightChild = node;
    }

    public void setCode(String s){ code = s; }


    /*This function compares the frequency of the calling TreeNode to another TreeNode. Required to use the sort method as described in Collections
    * @param HuffmanTreeNode
    * @return integer
    */
    public int compareTo(HuffmanTreeNode node){
        if (this.frequency > node.frequency) return 1;
        else if (this.frequency < node.frequency) return -1;
        else return 0;
    }

}


