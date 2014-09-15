package gwu.csci.arc;

public class InstructionRegister {
	//every register is of length LENGTH
	private static final int LENGTH = 18;
	
	//the content of the instruction register
	private char[] content = new char[LENGTH];

	public InstructionRegister() {
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
	
	/**
	 * get the content of the Instruction Register
	 * @param c store the content 
	 * @param len the length of the content to be read
	 * @return
	 */
	public int getIR(char[] c, int len) {
		for (int i = 0; i < len && i< LENGTH; i++) {
			c[i] = content[i];
			
		}
		return 0;
	}

	/**
	 * set the content of the Instruction Register
	 * if the content is too short, extend it to 18 bits
	 * if the content is too long ,only use the former 18 bits and give warnings
	 * @param c the content to be written
	 * @param len the length of the content
	 * @return
	 */
	public int setIR(char[] c, int len) {
		if (len > LENGTH) {
			System.err.println("RegisterFile(setR): the len is longer than the length of an GPR\n Set it to the length of an GPR");
			len = LENGTH;
		}
		if (len < LENGTH) {
			for (int i = 0; i < LENGTH; i++) {
				if (i<(LENGTH-len)) {
					content[i] = '0';
				}
				else {
					content[i] = c[i-(LENGTH-len)];
				}
			}
		}
		else {
			for(int i = 0; i < LENGTH; i++) {
				content[i] = c[i];
			}
		}
		return 0;
	}
	
	
}
