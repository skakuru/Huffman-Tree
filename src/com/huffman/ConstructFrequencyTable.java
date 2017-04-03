package com.huffman;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ConstructFrequencyTable {
	
	static Map<Integer, Integer> frequency_table = new HashMap<Integer, Integer>();
	
	public static Map<Integer, Integer> BuildFrequencyTable() throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new FileReader("sample_input_large.txt"));
	    String line;
	    while ((line = br.readLine()) != null && !line.isEmpty()) {
	    	Integer data = Integer.parseInt(line);
	    	if(frequency_table.containsKey(data)){
	    		frequency_table.put(data, frequency_table.get(data) + 1);
	    	}
	    	else{
	    		frequency_table.put(data, 1);
	    	}
	    }

	    return frequency_table;
	}

}
