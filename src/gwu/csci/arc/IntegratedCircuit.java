package gwu.csci.arc;
enum REG_TYPE {
	GPR,
	XR,
	CC,
	PC,
	IR
}

public class IntegratedCircuit {
	private static final int LEN_WORD = 18; // the length of the word
	private static final int LEN_ADDR = 12; // the length of the address
	private static final int LEN_INSTRUCTION = 18; //the length of an instruction
	private static final int LEN_OPCODE = 6; //the length of the OPCODE in an instruct
	private static final int LEN_RFI = 2; //the length of the RFI in an instruction
	private static final int LEN_XFI = 2; //the length of the XFI in an instruction
	private static final int LEN_I = 1; //the length of the I in an instruction
	private static final int LEN_ADDR_INCODE= 7; //the length of the address in an instruction

	public static int getLenWord() {
		return LEN_WORD;
	}


	public static int getLenAddr() {
		return LEN_ADDR;
	}


	public static int getLenInstruction() {
		return LEN_INSTRUCTION;
	}
	/*used for communicating with the memory*/
	private char[] MAR = new char[LEN_ADDR];
	private char[] MBR = new char[LEN_WORD];
	/*-------------------------------------------*/
	
	/*used for decompose a instruction; 18 bits*/
	private char[] opcode = new char[LEN_OPCODE];
	private char[] rfi = new char[LEN_RFI];
	private char[] xfi = new char[LEN_XFI];
	private char[] I = new char[LEN_I];
	private char[] addr = new char[LEN_ADDR_INCODE];
	/*------------------------------------------*/
	
	//the effective address
	private char[] EA = new char[LEN_ADDR];
	
	/*used for ALU calculation*/
	//result from the ALU
	private char[] RES = new char[LEN_WORD];
	//first operand
	private char[] OP1 = new char[LEN_WORD];
	//second operand
	private char[] OP2 = new char[LEN_WORD];
	/*------------------------*/
	
	//the memory
	private Memory memory;
	
	//the CPU
	private CPU cpu;
	
	public IntegratedCircuit() {
		// TODO Auto-generated constructor stub
		memory = Memory.getInstance();
		cpu = CPU.getInstance();
		
		//initiliaztion
		for (int i = 0; i < LEN_ADDR; i++) {
			EA[i] = '0';
			MAR[i] = '0';
		}
		for (int i = 0; i < LEN_WORD; i++) {
			MBR[i] = '0';
			RES[i] = '0';
			OP1[i] = '0';
			OP2[i] = '0';
			
		}
	}
	
	
	/**
	 * 
	 * @param c: store the content
	 * @param len: the length of the content
	 * @param rt: the type of the register
	 * @return 0
	 */
	private int readReg(char[] c, int len, REG_TYPE rt) {
		switch (rt) {
		case GPR:
			cpu.readGPR(c, rfi, len);
		case XR:
			cpu.readXR(c, xfi, len);
		case CC:
			//cpu.readCC(, len)
		case PC:
			
		case IR:
			cpu.readIR(c, len);
			
			break;

		default:
			break;
		}
		return 0;
	}
	
	/**
	 * 
	 * @param c: the content to be written
	 * @param len: the length of the content
	 * @param rt: the type of the register
	 * @return 0
	 */
	private int writeReg(char[] c, int len, REG_TYPE rt) {
		switch (rt) {
		case GPR:
			
		case XR:
			
		case CC:
			//cpu.readCC(, len)
		case PC:
			
		case IR:
			cpu.writeIR(c, len);
			
			break;

		default:
			break;
		}
		return 0;
		
	}
	/**
	 * 
	 * @param c: store the content
	 * @param len: the length of content
	 * @return 0
	 */
	private int readMem(char[] c, int len) {
		memory.read(c, len, EA);
		return 0;
	}
	private int writeMem() {
		return 0;
	}
	/**
	 * Perform the decode stage
	 * MAR <- R(RFI)
	 * MBR <- M(MAR)
	 * IR <- R(MBR)
	 * OPCODE <- IR0-5
	 * RFI <- IR6-7
	 * XFI <- IR8-9
	 * I <- IR10
	 * ADDR <- IR11-17
	 * @return 0
	 */
	public int decode() {
		char[] ir = new char[18];
		int n;
		readReg(MAR, LEN_ADDR, REG_TYPE.PC); // MAR <- R(RFI)
		readMem(MBR, LEN_WORD); //MBR <- M(MAR)
		writeReg(MBR, LEN_WORD, REG_TYPE.IR); // IR <- R(MBR)
		readReg(ir, LEN_INSTRUCTION, REG_TYPE.IR);
		for(n = 0; n < LEN_INSTRUCTION; n++) {
			if(n<LEN_OPCODE) { //OPCODE <- IR0-5
				opcode[n] = ir[n];
			}
			else if(n<LEN_OPCODE+LEN_RFI) {
				//RFI <- IR6-7
				rfi[n-LEN_OPCODE] = ir[n];
			}
			else if(n<LEN_OPCODE + LEN_RFI + LEN_XFI) {
				//XFI <- IR8-9
				xfi[n - LEN_OPCODE - LEN_RFI] = ir[n];
			}
			else if(n<LEN_OPCODE + LEN_RFI + LEN_XFI + LEN_I) {
				//I <- IR10
				I[n - LEN_OPCODE - LEN_RFI- LEN_XFI] = ir[n];
			}
			else if(n<LEN_OPCODE + LEN_RFI + LEN_XFI + LEN_I + LEN_ADDR_INCODE) {
				//ADDR <- IR11-17
				addr[n - LEN_OPCODE - LEN_RFI- LEN_XFI - LEN_I] = ir[n];
			}
		}
		
		return 0;
		
	}
	/**
	 * 
	 * IF I=0
	 * 		IF XFI =0
	 * 			EA <- ADDR
	 * 		IF XFI = 1, 2, 3
	 * 			EA <- R(XFI) + ADDR
	 * IF I = 1
	 * 		IF XFI = 0
	 * 			EA <- M(ADDR)
	 * 		IF XFI = 1, 2, 3
	 * 			EA <- M(R(XFI) + ADDR)
	 * @return 0
	 */
	public int calcEA() {
		if (I[0] == '0') {                   //IF I=0
			if(xfi[0] == '0' && xfi[1] == '0') { //IF XFI =0
				//EA <- ADDR
				for (int i = 0; i < LEN_ADDR; i++) {
					if(i < LEN_ADDR - LEN_ADDR_INCODE) {
						EA[i] = '0';
					}
					else { 
						EA[i] =addr[i-(LEN_ADDR-LEN_ADDR_INCODE)]; 
					}
					
				}
			}
			else { //IF XFI = 1, 2, 3
				//EA <- R(XFI) + ADDR
				char[] ad = new char[LEN_ADDR];
				cpu.readXR(ad, xfi, LEN_ADDR);
				//add ad to addr
				//!!!!!!need revise!!!!!!!!!!
				for(int i = 0; i < LEN_ADDR; i++) {
					EA[i] = ad[i];
				}
			}
		}
		else { //IF I = 1
			if(xfi[0] == '0' && xfi[1] == '0') { //IF XFI = 0
				//EA <- M(ADDR)
				char[] ad = new char[LEN_ADDR];
				memory.read(ad, LEN_ADDR, addr);
				for(int i = 0; i < LEN_ADDR; i++) {
					EA[i] = ad[i];
				}
				
			}
			else { //IF XFI = 1, 2, 3
				//EA <- M(R(XFI) + ADDR)
				char[] ad = new char[LEN_ADDR];
				char[] ad2 = new char[LEN_ADDR];
				cpu.readXR(ad, xfi, LEN_ADDR);
				//add ad to ad2
				//!!!!!!need revise!!!!!!!!!!
				memory.read(ad2, LEN_ADDR, ad);
				for(int i = 0; i < LEN_ADDR; i++) {
					EA[i] = ad2[i];
				}
				
			}
		}
		System.out.println(EA);
		return 0;
	}
	
}
