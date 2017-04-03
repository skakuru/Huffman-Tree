package com.huffman;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.util.BitSet;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.huffman.ConstructFrequencyTable;
import com.huffman.BinaryHeap;
import com.huffman.EncodeInput;
import com.huffman.DecodeOutput;

public class HuffmanTrees {
	
	public static class HuffmanTreeNode {
        int ch;
        int frequency;
        HuffmanTreeNode left;
        HuffmanTreeNode right;

        HuffmanTreeNode(Integer integer, int frequency,  HuffmanTreeNode left,  HuffmanTreeNode right) {
            this.ch = integer;
            this.frequency = frequency;
            this.left = left;
            this.right = right;
        }
    }
	
	static Map<Integer, String> codetable = new HashMap<Integer, String>();

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		ConstructFrequencyTable ft = new ConstructFrequencyTable();
		EncodeInput e = new EncodeInput();
		DecodeOutput d = new DecodeOutput();
    	Map<Integer, Integer> freq_table = ft.BuildFrequencyTable();
    	long startTime = System.nanoTime();
    	BinaryHeap priorityQueue = createPriorityQueue(freq_table);
    	HuffmanTreeNode root = buildHuffmanTree(priorityQueue);
    	long stopTime = System.nanoTime();
		System.out.println("HuffmanTree Construction: "+(stopTime - startTime)/ 1000000000.0);
    	generateHuffmanCodes(freq_table.keySet(), root);
    	e.encodeInputStream(codetable);
    	stopTime = System.nanoTime();
    	System.out.println("Encoding : "+(stopTime - startTime)/ 1000000000.0);
    	d.decodeOutputStream();
    	stopTime = System.nanoTime();
    	System.out.println("Total Time : "+(stopTime - startTime)/ 1000000000.0);
	}

	private static BinaryHeap createPriorityQueue(Map<Integer, Integer> freq_table) {
		// TODO Auto-generated method stub
		BinaryHeap heap = new BinaryHeap();
        for (Map.Entry<Integer, Integer> entry : freq_table.entrySet()) {
            heap.add(new HuffmanTreeNode(entry.getKey(), entry.getValue(), null, null));
        }
        return heap;
	}

	private static HuffmanTreeNode buildHuffmanTree(BinaryHeap priorityQueue) {
		// TODO Auto-generated method stub
		while (priorityQueue.size() > 1) {
            final HuffmanTreeNode node1 = priorityQueue.remove();
            final HuffmanTreeNode node2 = priorityQueue.remove();
            HuffmanTreeNode node = new HuffmanTreeNode(-1, node1.frequency + node2.frequency, node1, node2);
            priorityQueue.add(node);
        }
        return priorityQueue.remove();
	}
	

	private static void generateHuffmanCodes(Set<Integer> dataset, HuffmanTreeNode node) {
       
       generateCode(node, "");
       BufferedWriter bw;
       try {
			bw = new BufferedWriter(new FileWriter("code_table.txt"));
			for(Map.Entry<Integer, String> entry : codetable.entrySet()){
				bw.write(entry.getKey()+" "+entry.getValue()+"\n");
			}
			bw.close();
       }
       catch (IOException e) {
			e.printStackTrace();
       }
    }


    private static void generateCode(HuffmanTreeNode node, String s) {
    	if(node == null)
    		return;
        if (node.left == null && node.right == null) {
        	//System.out.println(node.ch+" "+s.length());
            codetable.put(node.ch, s.toString());
            return;
        }    
        generateCode(node.left, s+"0");
        generateCode(node.right, s+"1");
    }

}
