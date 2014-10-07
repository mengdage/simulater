package gwu.csci.arc.utility;

import java.util.Random;

public class IOConnector {
	public void printString(String s) {
		System.out.println(s);
	}
	
	
	public int readInt() {
		Random random = new Random();
		
		return random.nextInt(100);
	}
	
	public void printInt(int i) {
		printString(i+"");
	}
	public void printErr(String s) {
		System.err.println(s);
	}
}
