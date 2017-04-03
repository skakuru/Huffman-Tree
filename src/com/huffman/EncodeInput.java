package com.huffman;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.util.BitSet;
import java.util.Map;

public class EncodeInput {

	public static void encodeInputStream(Map<Integer, String> codeTable) throws IOException {
    	StringBuilder stringBuilder = new StringBuilder();
    	BitSet bitSet;
		BufferedReader br1 = new BufferedReader(new FileReader("sample_input_large.txt"));
        String line;
        Integer data;
        while((line = br1.readLine()) != null && !line.isEmpty()){
        	data = Integer.parseInt(line);
        	stringBuilder.append(codeTable.get(data));
        }
		br1.close();
		bitSet = getBitSet(stringBuilder.toString());
	    OutputStream out = new FileOutputStream("encoded.bin");
	    byte[] totalBytes = bitSet.toByteArray();
	    out.write(totalBytes);
	    if(out !=null)
	        out.close();
	}
	
	private static BitSet getBitSet(String input) {
		// TODO Auto-generated method stub
		 BitSet bitSet = new BitSet(input.length());
		 int bitcounter = 0;
	        for (Character c: input.toCharArray()) {
	            if (c.equals('1')) {
	                bitSet.set(bitcounter);
	            } 
	            bitcounter++;
	        }
	     return bitSet;
	}
	
}
