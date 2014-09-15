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
	
	//get the content of the register one X1
	public int getX1(char[] x1, int len) {
		for(int i = 0; i < len && i < LENGTH; i++) {
			x1[i] = X1[i];
		}
		return 0;
	}
	//set the content of the register one X1
	public int setX1(char[] x1, int len) {

		if (len < LENGTH) {
			for (int i = 0; i < LENGTH; i++) {
				if (i<(LENGTH-len)) {
					X1[i] = '0';
				}
				else {
					X1[i] = x1[i-(LENGTH-len)];
				}
			}
		}
		else {
			for(int i = 0; i < LENGTH; i++) {
				X1[i] = x1[i];
			}
		}
		return 0;
	}
	//get the content of the register two X2
	public int getX2(char[] x2, int len) {
		for(int i = 0; i < len && i < LENGTH; i++) {
			x2[i] = X2[i];
		}
		return 0;
	}
	//set the content of the register two X2
	public int setX2(char[] x2, int len) {
		if (len < LENGTH) {
			for (int i = 0; i < LENGTH; i++) {
				if (i<(LENGTH-len)) {
					X2[i] = '0';
				}
				else {
					X2[i] = x2[i-(LENGTH-len)];
				}
			}
		}
		else {
			for(int i = 0; i < LENGTH; i++) {
				X2[i] = x2[i];
			}
		}
		return 0;
	}
	//get the content of the register three X3
	public int getX3(char[] x3, int len) {
		for(int i = 0; i < len && i < LENGTH; i++) {
			x3[i] = X3[i];
		}
		return 0;
	}
	//set the content of the register three X3
	public int setX3(char[] x3, int len) {
		if (len < LENGTH) {
			for (int i = 0; i < LENGTH; i++) {
				if (i<(LENGTH-len)) {
					X1[i] = '0';
				}
				else {
					X3[i] = x3[i-(LENGTH-len)];
				}
			}
		}
		else {
			for(int i = 0; i < LENGTH; i++) {
				X3[i] = x3[i];
			}
		}
		
		return 0;
	}

	
}
