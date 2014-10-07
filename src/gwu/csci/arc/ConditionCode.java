package gwu.csci.arc;

public class ConditionCode {

	//every register is of length LENGTH
	private static final int LENGTH = 4;
	
	private char[] content = new char[LENGTH];

	//get the length of the register
	public static int getLength() {
		return LENGTH;
	}
	
	/**
	 * get the content of the Condition Code Register
	 * @param c store the content 
	 * @param len the length of the content to be read
	 * @return
	 */
	public int getCC(char[] c, int id) {
		c[0] = content[id];
		return 0;
	}

	/**
	 * set the content of the Condition Code Register
	 * if the content is too short, extend it to 4 bits
	 * if the content is too long ,only use the former 4 bits and give warnings
	 * @param c the content to be written
	 * @param len the length of the content
	 * @return
	 */
	public int setCC(char[] c, int len) {
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
