package gwu.csci.arc.test;

import gwu.csci.arc.CPU;
import gwu.csci.arc.utility.Converter;

public class WriteStringIntoMemory {
	public static void main(String[] args) {
		//the address where sentence is written in memory
		String addSt = "001000000000";
		String addWord = "010000000000";
		String addLenW = "010001011000";
		String addr = "001000000011";
		String sentence = "La. My Hello name is. I am a student. I am from GWU. Nice to meet you. Hello Hello.";
		String sentence2 = "ABCXYZabcxyz,. ";
		String word = "Hello";
		String binWord = "";
		
		//the binary sequence of the sentence
		//its length is 6 times of the length of the sentence
		String binSentence = "";
		String content="";
		CPU cpu = CPU.getInstance();
		int lenSt = sentence.length();
		int c = 0;
		char[] ch = new char[18];
		
		for(int i = 0; i< lenSt; i++) {
			c = sentence.charAt(i);
			Converter.converterI2S(c, ch);
			binSentence += new String(ch);
			//content+=new String(ch)+" ";
			//System.out.println(c+ " " + new String(ch));
		}
		for(int i = 0; i < word.length(); i++) {
			c = word.charAt(i);
			Converter.converterI2S(c, ch);
			binWord += new String(ch);
		}
		Converter.converterI2S(word.length(), ch);
		
		//System.out.println(content);
		cpu.writeMem(binSentence.toCharArray(), binSentence.length(), addSt.toCharArray());
		cpu.writeMem(binWord.toCharArray(), binWord.length(), addWord.toCharArray());
		cpu.writeMem(ch, ch.length, addLenW.toCharArray());
		
		//System.out.println("the length of the binary sequence of the sentence is "+binSentence.length());
		//System.out.println("the length of the binary sequence of the word is "+binWord.length());

		//cpu.readMem(ch, ch.length, addr.toCharArray());
		//System.out.println(ch);
//		char[] chSentence = new char[sentence.length()*18];
//		cpu.readMem(chSentence, chSentence.length, addSt.toCharArray());
//		if(binSentence.compareTo(new String(chSentence)) == 0)
//			System.out.println("equal");
	}
}
