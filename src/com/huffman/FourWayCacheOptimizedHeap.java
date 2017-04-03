package com.huffman;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import com.huffman.HuffmanTrees.HuffmanTreeNode;

public class FourWayCacheOptimizedHeap {
	
	int heapsize;
	
	
    HuffmanTreeNode[] arr;

    public FourWayCacheOptimizedHeap(int heapsize) {
    	this.heapsize = heapsize;
    	arr = new HuffmanTreeNode[heapsize];
    }
    
    private int parent(int n) 
    { 
    	return (n - 1)/2; 
    }
    
    private int left(int n) {
    	return n * 2 + 1; 
    }
    
    private int right(int n) {
    	return n * 2 + 2; 
    }
    
	public void add(HuffmanTreeNode node) {
		binheap.add(node);
	    int child = binheap.size() - 1;
	    int parent = parent(child);
		//If parent node value is greater than child node value
		while( child != 0 && (binheap.get(parent).frequency > binheap.get(child).frequency)){
			swap(parent,child);
			child = parent;
			parent = parent(child);
		}
	}
	
	public HuffmanTreeNode remove(){
		 if (binheap.size() == 0) 
			 throw new NoSuchElementException();
		 HuffmanTreeNode minNode = binheap.get(0);
		 binheap.set(0, binheap.get(binheap.size() - 1));
		 binheap.remove(binheap.size() - 1);
		 minHeapify(binheap, 0);
		return minNode;
	}

	private void minHeapify(List<HuffmanTreeNode> binheap, int i) {
		// TODO Auto-generated method stub
		int l = left(i);
		int r = right(i);
		int smallest = i;
		if(l < binheap.size() && binheap.get(l).frequency < binheap.get(i).frequency)
			smallest = l;
		if(r < binheap.size() && binheap.get(r).frequency < binheap.get(smallest).frequency)
			smallest = r;
		
		if(smallest != i){
			swap(i,smallest);
			minHeapify(binheap, smallest);
		}
	}

	private void swap(int i, int j) {
		// TODO Auto-generated method stub
		HuffmanTreeNode temp = binheap.get(i);
        binheap.set(i, binheap.get(j));
        binheap.set(j, temp);
	}

}
