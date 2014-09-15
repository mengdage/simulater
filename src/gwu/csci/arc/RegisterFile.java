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
	
	//get the content of the register zero R0
	public int getR0(char[] r0, int len) {
		for(int i = 0; i < len && i < LENGTH; i++) {
			r0[i] = R0[i];
		}
		return 0;
	}
	//set the content of the register zero R0
	public int setR0(char[] r0, int len) {
		if (len < LENGTH) {
			for (int i = 0; i < LENGTH; i++) {
				if (i<(LENGTH-len)) {
					R0[i] = '0';
				}
				else {
					R0[i] = r0[i-(LENGTH-len)];
				}
			}
		}
		return 0;
	}
	//get the content of the register one R1
	public int getR1(char[] r1, int len) {
		for(int i = 0; i < len && i < LENGTH; i++) {
			r1[i] = R1[i];
		}
		return 0;
	}
	//set the content of the register one R1
	public int setR1(char[] r1, int len) {
		if (len < LENGTH) {
			for (int i = 0; i < LENGTH; i++) {
				if (i<(LENGTH-len)) {
					R1[i] = '0';
				}
				else {
					R1[i] = r1[i-(LENGTH-len)];
				}
			}
		}
		else {
			for(int i = 0; i<len && i < LENGTH; i++) {
				R1[i] = r1[i];
			}
		}
		return 0;
	}
	//get the content of the register two R2
	public int getR2(char[] r2, int len) {
		for(int i = 0; i < len && i < LENGTH; i++) {
			r2[i] = R2[i];
		}
		return 0;
	}
	//set the content of the register two R2
	public int setR2(char[] r2, int len) {
		if (len < LENGTH) {
			for (int i = 0; i < LENGTH; i++) {
				if (i<(LENGTH-len)) {
					R2[i] = '0';
				}
				else {
					R2[i] = r2[i-(LENGTH-len)];
				}
			}
		}
		else {
			for(int i = 0; i<len && i < LENGTH; i++) {
				R2[i] = r2[i];
			}
		}
		return 0;
	}
	//get the content of the register three R3
	public int getR3(char[] r3, int len) {
		for(int i = 0; i < len && i < LENGTH; i++) {
			r3[i] = R3[i];
		}
		return 0;
	}
	//set the content of the register three R3
	public int setR3(char[] r3, int len) {
		if (len < LENGTH) {
			for (int i = 0; i < LENGTH; i++) {
				if (i<(LENGTH-len)) {
					R3[i] = '0';
				}
				else {
					R3[i] = r3[i-(LENGTH-len)];
				}
			}
		}
		else {
			for(int i = 0; i<len && i < LENGTH; i++) {
				R3[i] = r3[i];
			}
		}
		
		return 0;
	}

	//!!!!!maybe more
	
}
