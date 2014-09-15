package gwu.csci.arc;

public class IndexRegister {
	//every register is of length LENGTH
	private static final int LENGTH = 12;
	
	//the three index registers
	private char[] X1 = new char[LENGTH];
	private char[] X2 = new char[LENGTH];
	private char[] X3 = new char[LENGTH];
	
	public IndexRegister() {
		// TODO Auto-generated constructor stub
		int i;
		for(i = 0; i < LENGTH; i++) {
			X1[i] = '0';
		}
		for(i = 0; i < LENGTH; i++) {
			X2[i] = '0';
		}
		for(i = 0; i < LENGTH; i++) {
			X3[i] = '0';
		}
	}
	//get the length of the register
	public static int getLength() {
		return LENGTH;
	}
	
	/**
	 * get the content of the first index register X1 
	 * @param x1 store the content of X1
	 * @param len the length of the content to be read
	 * @return
	 */
	public int getX1(char[] x1, int len) {
		for(int i = 0; i < len && i < LENGTH; i++) {
			x1[i] = X1[i];
		}
		return 0;
	}
	/**
	 * set the content of the first index register X1
	 * if the content is too short, extend it to 12 bits
	 * if the content is too long ,only use the former 12 bits and give warnings
	 * @param x1 the content to be written
	 * @param len the length of the content
	 * @return
	 */
	public int setX1(char[] x1, int len) {

		return setX(x1, X1, len);
	}
	/**
	 * get the content of the second index register X2 
	 * @param x2 store the content of X2
	 * @param len the length of the content to be read
	 * @return
	 */
	public int getX2(char[] x2, int len) {
		for(int i = 0; i < len && i < LENGTH; i++) {
			x2[i] = X2[i];
		}
		return 0;
	}
	/**
	 * set the content of the second index register X2
	 * if the content is too short, extend it to 12 bits
	 * if the content is too long ,only use the former 12 bits and give warnings
	 * @param x2 the content to be written
	 * @param len the length of the content
	 * @return
	 */
	public int setX2(char[] x2, int len) {
		return setX(x2, X2, len);
	}
	/**
	 * get the content of the first index register X3 
	 * @param x3 store the content of X3
	 * @param len the length of the content to be read
	 * @return
	 */
	public int getX3(char[] x3, int len) {
		for(int i = 0; i < len && i < LENGTH; i++) {
			x3[i] = X3[i];
		}
		return 0;
	}
	/**
	 * set the content of the third index register X3
	 * if the content is too short, extend it to 12 bits
	 * if the content is too long ,only use the former 12 bits and give warnings
	 * @param x3 the content to be written
	 * @param len the length of the content
	 * @return
	 */
	public int setX3(char[] x3, int len) {
		return setX(x3, X3, len);
	}
	
	private int setX(char[] x,char[] X, int len) {
		if (len>LENGTH) {
			len = LENGTH;
		}
		if (len < LENGTH) {
			for (int i = 0; i < LENGTH; i++) {
				if (i<(LENGTH-len)) {
					X[i] = '0';
				}
				else {
					X[i] = x[i-(LENGTH-len)];
				}
			}
		}
		else {
			for(int i = 0; i < LENGTH; i++) {
				X[i] = x[i];
			}
		}
		return 0;
	}

	
}
