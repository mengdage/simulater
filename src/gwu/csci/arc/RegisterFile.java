package gwu.csci.arc;

public class RegisterFile {
	//every register is of length LENGTH
	private static final int LENGTH = 18;
	
	//the four General Purpose Register
	private char[] R0 = new char[LENGTH];
	private char[] R1 = new char[LENGTH];
	private char[] R2 = new char[LENGTH];
	private char[] R3 = new char[LENGTH];
	
	public RegisterFile() {
		// TODO Auto-generated constructor stub
		int i;
		for(i = 0; i< LENGTH; i++) {
			R0[i] = '0';
		}
		for(i = 0; i< LENGTH; i++) {
			R1[i] = '0';
		}
		for(i = 0; i< LENGTH; i++) {
			R2[i] = '0';
		}
		for(i = 0; i< LENGTH; i++) {
			R3[i] = '0';
		}
	}
	
	//get the length of the register
	public static int getLength() {
		return LENGTH;
	}
	
	/**
	 * get the content of the register zero R0
	 * @param r0 store the content of R0
	 * @param len the length of the content to be read
	 * @return
	 */
	public int getR0(char[] r0, int len) {
		for(int i = 0; i < len && i < LENGTH; i++) {
			r0[i] = R0[i];
		}
		return 0;
	}
	/**
	 * set the content of the register zero R0
	 * if the content is too short, extend it to 18 bits
	 * if the content is too long ,only use the former 18 bits and give warnings
	 * @param r0 the content to be written
	 * @param len the length of the content
	 * @return
	 */
	public int setR0(char[] r0, int len) {
		return setR(r0, R0, len);
	}
	/**
	 * get the content of the first register R1
	 * @param r1 store the content of R1
	 * @param len the length of the content to be read
	 * @return
	 */
	public int getR1(char[] r1, int len) {
		for(int i = 0; i < len && i < LENGTH; i++) {
			r1[i] = R1[i];
		}
		return 0;
	}
	/**
	 * set the content of the first register R1
	 * if the content is too short, extend it to 18 bits
	 * if the content is too long ,only use the former 18 bits and give warnings
	 * @param r1 the content to be written
	 * @param len the length of the content
	 * @return
	 */
	public int setR1(char[] r1, int len) {
		return setR(r1, R2, len);
	}
	
	/**
	 * get the content of the register zero R2
	 * @param r2 store the content of R2
	 * @param len the length of the content to be read
	 * @return
	 */
	public int getR2(char[] r2, int len) {
		for(int i = 0; i < len && i < LENGTH; i++) {
			r2[i] = R2[i];
		}
		return 0;
	}
	/**
	 * set the content of the second register R2
	 * if the content is too short, extend it to 18 bits
	 * if the content is too long ,only use the former 18 bits and give warnings
	 * @param r2 the content to be written
	 * @param len the length of the content
	 * @return
	 */
	public int setR2(char[] r2, int len) {
		return setR(r2, R2, len);
	}
	/**
	 * get the content of the register R3
	 * @param r3 store the content of R3
	 * @param len the length of the content to be read
	 * @return
	 */
	public int getR3(char[] r3, int len) {
		for(int i = 0; i < len && i < LENGTH; i++) {
			r3[i] = R3[i];
		}
		return 0;
	}
	/**
	 * set the content of the third register R3
	 * if the content is too short, extend it to 18 bits
	 * if the content is too long ,only use the former 18 bits and give warnings
	 * @param r3 the content to be written
	 * @param len the length of the content
	 * @return
	 */
	public int setR3(char[] r3, int len) {
		return setR(r3, R3, len);
	}
	
	/**
	 * the common function for setR123
	 * @param r to be written
	 * @param R to be read
	 * @param len length
	 * @return 0
	 */
	private int setR(char[] r, char[] R, int len) {
		if (len > LENGTH) {
			System.err.println("RegisterFile(setR): the len is longer than the length of an GPR\n Set it to the length of an GPR");
			len = LENGTH;
		}
		if (len < LENGTH) {
			for (int i = 0; i < LENGTH; i++) {
				if (i<(LENGTH-len)) {
					R[i] = '0';
				}
				else {
					R[i] = r[i-(LENGTH-len)];
				}
			}
		}
		else {
			for(int i = 0; i < LENGTH; i++) {
				R[i] = r[i];
			}
		}
		return 0;
	}

	//!!!!!maybe more
	
}
