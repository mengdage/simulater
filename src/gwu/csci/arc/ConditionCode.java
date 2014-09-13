package gwu.csci.arc;

public class ConditionCode {

	//every register is of length LENGTH
	private static final int LENGTH = 4;
	
	private char[] content = new char[LENGTH];

	//get the length of the register
	public static int getLength() {
		return LENGTH;
	}
	
	//get the condition code
	public int getCC(char[] c, int len) {
		for (int i = 0; i < len && i< LENGTH; i++) {
			c[i] = content[i];
			
		}
		return 0;
	}

	//set the program counter 
	public int setCC(char[] c, int len) {
		for (int i = 0; i < len && i< LENGTH; i++) {
			content[i] = c[i];
			
		}
		return 0;
	}
	
}
