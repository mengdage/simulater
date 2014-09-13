package gwu.csci.arc;

public class ProgramCounter {
	//every register is of length LENGTH
	private static final int LENGTH = 12;
	
	//the content of program counter register
	private char[] content = new char[LENGTH];
	
	public ProgramCounter() {
		// TODO Auto-generated constructor stub
		//initialization
		int i;
		for(i = 0; i< LENGTH; i++) {
			content[i] = '0';
		}
	}
	//get the length of the register
	public static int getLength() {
		return LENGTH;
	}

	//get the program counter 
	public int getPC(char[] c, int len) {
		for (int i = 0; i < len && i< LENGTH; i++) {
			c[i] = content[i];
			
		}
		return 0;
	}

	//set the program counter 
	public int setPC(char[] c, int len) {
		for (int i = 0; i < len && i< LENGTH; i++) {
			content[i] = c[i];
			
		}
		return 0;
	}
	
}
